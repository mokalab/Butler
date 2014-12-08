package com.mokalab.butler.request.simplerequest.bitdourlshortener;

import android.content.Context;

import com.mokalab.butler.request.simplerequest.IReqFailure;
import com.mokalab.butler.request.simplerequest.IReqSuccess;
import com.mokalab.butler.request.simplerequest.IRequestID;
import com.mokalab.butler.request.simplerequest.JsonRestRequest;
import com.mokalab.butler.util.JsonUtils;
import com.mokalab.butler.util.TextUtils;

import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by Pirdad S on 14-11-15.
 */
public class UrlShortner extends JsonRestRequest<String> {

    private static final String CONTTYPE_JSON = "application/json";
    private static final String URL = "http://bit.do/mod_perl/url-shortener.pl";

    private String mOriginalUrl = null;
    private String mResponseContentType = null;

    public UrlShortner(Context context, String urlToShorten, IRequestID ID, IReqSuccess successListener,
                       IReqFailure failureListener) {

        super(context, ID, successListener, failureListener, URL, Method.POST);
        addPostParam("action", "shorten");
        addPostParam("url", urlToShorten);
        addPostParam("url2", "site2");

        setShouldLog(true);
        mOriginalUrl = urlToShorten;
    }

    @Override
    protected void onConnectionMade(int response_code, HttpURLConnection con) {

        if (response_code == HttpURLConnection.HTTP_OK) {

            mResponseContentType = con.getContentType();
        }
    }

    @Override
    protected String onParse(String response) {

        if (TextUtils.isEmpty(mResponseContentType) || !mResponseContentType.equalsIgnoreCase(CONTTYPE_JSON)) {
            /* EITHER EMPTY CONTENT TYPE OR CONTENT TYPE NOT JSON */
            return mOriginalUrl;
        }

        JSONObject jsonRoot = JsonUtils.convertToJSONObject(response);

        if (jsonRoot == null) return mOriginalUrl;

        String urlBase = JsonUtils.parseString(jsonRoot, "url_base", null);
        String urlHash = JsonUtils.parseString(jsonRoot, "url_hash", null);
        String urlId = JsonUtils.parseString(jsonRoot, "url_id", null);

        return urlBase + urlHash;
    }
}
