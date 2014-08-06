package com.mokalab.butler.request.simplerequest;

import android.content.Context;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Pirdad Sakhizada on 29/11/13.
 */
public abstract class RestRequest<P> extends Request<String, P> {

    private static final String GZIP_ENCODING = "gzip";
    private int requestTimeout;


    /**
     * Use it with RestRequest to define the type of request it is.
     */
    public enum Method {
        GET, POST, PUT, DELETE;
    }

    protected Method METHOD;
    protected String baseUrl;
    protected ArrayList<BasicNameValuePair> mapGetParams;
    protected ArrayList<BasicNameValuePair> mapPostParams;
    protected ArrayList<BasicNameValuePair> mapHeaderParams;

    public RestRequest(Context context, IRequestID ID, IReqSuccess successListener, IReqFailure failureListener, String url, Method method) {

        super(context, ID, successListener, failureListener);
        this.baseUrl = url;
        this.METHOD = method;

        mapGetParams = new ArrayList<BasicNameValuePair>();
        mapPostParams = new ArrayList<BasicNameValuePair>();
        mapHeaderParams = new ArrayList<BasicNameValuePair>();

        requestTimeout = 5000;
    }

    @Override
    protected String onExecute() {

        String fullUrl = constructUrl();
        debugLog("URL: " + fullUrl);
        debugLog("Method: " + METHOD.toString());
        debugParamsList("Post Params", mapPostParams);
        debugParamsList("Header Params", mapHeaderParams);

        // IS CANCELLED BEFORE CONNECTION
        if (isCancelled()) {
            cancelled();
            return null;
        }

        HttpURLConnection con = getConnection(fullUrl);

        // IS CANCELLED AFTER CONNECTION
        if (isCancelled()) {
            cancelled();
            return null;
        }

        if (con != null) {

            con.setConnectTimeout(requestTimeout);
            con.setReadTimeout(requestTimeout);
            setConnectionMethod(con);
            setupHeader(con);
            setDoOutPut(con);
            if (con.getDoOutput()) {
                writeToOutputStream(con, getPostBodyContent());
            }
            //con.setDoInput(true); // PROBABLY NOT REQUIRED...
            int response_code = 0;

            try {

                // IS CANCELLED BEFORE CONNECT
                if (isCancelled()) {
                    cancelled();
                    return null;
                }

                con.connect();

                // IS CANCELLED AFTER CONNECT
                if (isCancelled()) {
                    cancelled();
                    return null;
                }

                response_code = con.getResponseCode();
                InputStream in = con.getInputStream();

                if (GZIP_ENCODING.equals(con.getContentEncoding())) {
                    in = new GZIPInputStream(in);
                }

                // IS CANCELLED AFTER GET INPUT STREAM
                if (isCancelled()) {
                    cancelled();
                    return null;
                }

                String responseContent = readInputStream(in);

                // IS CANCELLED AFTER READ INPUT STREAM
                if (isCancelled()) {
                    cancelled();
                    return null;
                }

                return responseContent;

            } catch (UnsupportedEncodingException e) {

                errorLog(e.toString());
                STATUS = ERequestStatus.UNSUPPORTED_ENCODING_ERROR;//Unsupported Encoding Exception
                return null;

            } catch (ClientProtocolException e) {

                errorLog(e.toString());
                STATUS = ERequestStatus.CLIENT_PROTOCOL_ERROR;//Client Protocol Exception
                return null;

            } catch (SocketTimeoutException e) {

                errorLog(e.toString());
                STATUS = ERequestStatus.TIMEDOUT;//IO Exception
                return null;

            } catch (IOException e) {

                errorLog(e.toString());
                STATUS = ERequestStatus.IO_ERROR;//IO Exception
                return null;

            } catch (Exception e) {

                errorLog(e.toString());
                STATUS = ERequestStatus.CATCH_ALL_ERROR;
                return null;
            }

        } else {

            errorLog("Unable to establish HttpURLConnection");
            STATUS = ERequestStatus.CATCH_ALL_ERROR;
        }

        return null;
    }

    /**
     * Logs out the params list...
     *
     * @param paramsKey
     * @param mapParams
     */
    private void debugParamsList(String paramsKey, ArrayList<BasicNameValuePair> mapParams) {

        if (mapParams != null) {
            for (int i = 0; i < mapParams.size(); i++) {
                BasicNameValuePair pair = mapParams.get(i);
                debugLog(paramsKey + "-> " + pair.getName() + " : " + pair.getValue());
            }
        }
    }

    /**
     * Reads the Input Stream to String response.
     *
     * @param stream
     * @return
     */
    private String readInputStream(InputStream stream) {

        StringBuilder total = new StringBuilder();
        try {

            BufferedReader r = new BufferedReader(new InputStreamReader(stream));
            try {

                String line;

                while ((line = r.readLine()) != null) {
                    total.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (stream != null) stream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return total.toString();
    }

    /**
     * If the Method is PUT OR POST, this method gets called for writing content to the post body.
     * <br>By default it writes the Form Type Post Parameters. ex. name=value&name=value
     * <br>Override this if the body body needs to be in any other format.
     *
     * @return
     */
    protected String getPostBodyContent() {

        return getFormTypePostParams();
    }

    /**
     * Gets Connection based on the full url.
     *
     * @param fullUrl
     * @return
     */
    protected HttpURLConnection getConnection(String fullUrl) {

        boolean is_https = isHttps(fullUrl);

        try {

            if (is_https) {
                return (HttpsURLConnection) new URL(fullUrl).openConnection();
            } else {
                return (HttpURLConnection) new URL(fullUrl).openConnection();
            }

        } catch (MalformedURLException e) {
            errorLog("Url is Malformed");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Checks if the url is https.
     *
     * @param url
     * @return
     */
    public static boolean isHttps(String url) {

        if (url.toLowerCase(Locale.getDefault()).indexOf("https") != -1) {
            return true;
        }
        return false;
    }

    /**
     * Constructs the url - Generally used for GET request
     *
     * @return
     */
    protected String constructUrl() {

        String fullUrl = baseUrl;

        // GET PARAMS
        StringBuilder getParams = new StringBuilder();
        boolean first_get_param = true;
        for (int i = 0; i < mapGetParams.size(); i++) {

            BasicNameValuePair param = mapGetParams.get(i);
            char seperator = '&';
            if (first_get_param) {
                seperator = '?';
                first_get_param = false;
            }
            getParams.append(seperator + param.getName() + "=" + param.getValue());
        }
        return fullUrl + getParams.toString();
    }

    /**
     * Sets the method to the request connection object.
     *
     * @param connection
     */
    protected void setConnectionMethod(HttpURLConnection connection) {

        if (connection != null) {
            try {
                switch (METHOD) {

                    case GET:
                        connection.setRequestMethod("GET");
                        return;
                    case POST:
                        connection.setRequestMethod("POST");
                        return;
                    case PUT:
                        connection.setRequestMethod("PUT");
                        return;
                    case DELETE:
                        connection.setRequestMethod("DELETE");
                        return;

                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the heads to the request connection object.
     *
     * @param connection
     */
    protected void setupHeader(HttpURLConnection connection) {

        if (connection != null) {
            for (int i = 0; i < mapHeaderParams.size(); i++) {

                BasicNameValuePair header = mapGetParams.get(i);
                connection.addRequestProperty(header.getName(), header.getValue());
            }
        }
    }

    /**
     * Sets DoOutPut to true if post or put. Not sure if delete requires it.
     *
     * @param connection
     */
    protected void setDoOutPut(HttpURLConnection connection) {

        if (connection != null) {

            // TODO: IF DELETE REQUEST DOOUTPUT, set it here
            switch (METHOD) {
                case POST:
                case PUT:
                    connection.setDoOutput(true);
                    break;
                default:
                    connection.setDoOutput(false);
            }
        }
    }

    /**
     * Gets a String representation of the post params.
     *
     * @return
     */
    protected String getFormTypePostParams() {

        // POST PARAMS
        StringBuilder postParams = new StringBuilder();
        for (int i = 0; i < mapPostParams.size(); i++) {

            BasicNameValuePair param = mapPostParams.get(i);
            String seperator = "&";
            if (i == mapPostParams.size() - 1) {
                seperator = "";
            }
            postParams.append(param.getName() + "=" + param.getValue() + seperator);
        }

        return postParams.toString();
    }

    /**
     * Writes content to the outputstream of the request connection.
     *
     * @param conn
     * @param content
     */
    protected void writeToOutputStream(HttpURLConnection conn, String content) {

        try {

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(content);
            out.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add new Get Parameter
     *
     * @param key
     * @param value
     * @return
     */
    public RestRequest addGetParam(String key, String value) {

        mapGetParams.add(new BasicNameValuePair(key, value));
        return this;
    }

    /**
     * Add new Post Parameter
     *
     * @param key
     * @param value
     * @return
     */
    public RestRequest addPostParam(String key, String value) {

        mapPostParams.add(new BasicNameValuePair(key, value));
        return this;
    }

    /**
     * Add new Header Parameter
     *
     * @param key
     * @param value
     * @return
     */
    public RestRequest addHeaderParam(String key, String value) {

        mapHeaderParams.add(new BasicNameValuePair(key, value));
        return this;
    }

    /**
     * Set Request Timeout. Default is 15 seconds.
     *
     * @param timeoutInMillis
     * @return
     */
    public RestRequest setRequestTimeout(int timeoutInMillis) {

        this.requestTimeout = timeoutInMillis;
        return this;
    }

    public void setBaseUrl(String baseUrl) {

        this.baseUrl = baseUrl;
    }

    @Override
    public String getUrl() {

        return constructUrl();
    }
}
