package com.mokalab.butler.request.simplerequest;

import android.content.Context;

import com.mokalab.butler.util.SoapUtils;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by Pirdad Sakhizada on 29/11/13.
 */
public abstract class SoapRequest<P> extends Request<Object, P> {

    private static final String TAG = "SOAP_REQUEST";
    private int mRequestTimeout;


    public SoapRequest(Context context, IRequestID ID, IReqSuccess successListener, IReqFailure failureListener) {
        super(context, ID, successListener, failureListener);
        mRequestTimeout = 5000;
    }

    public void setRequestTimeout(int requestTimeout) {
        mRequestTimeout = requestTimeout;
    }

    @Override
    protected Object onExecute() {

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(getVersion());
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.bodyOut = getBody();
        envelope.headerOut = getHeaders();
        envelope.env = "http://www.w3.org/2003/05/soap-envelope";
        envelope.setAddAdornments(false);

        HttpTransportSE ht = new HttpTransportSE(getUrl(), mRequestTimeout);
        if (shouldLog()) {
            ht.debug = true;
        }

        try {

            ht.call(getSoapAction(), envelope);
            debugLog("Soap Request: " + ht.requestDump);
            debugLog("Soap Response: " + ht.responseDump);
            return envelope.getResponse();

        } catch (IOException e) {

            errorLog(e.toString());
            if (e instanceof SocketTimeoutException) {
                STATUS = ERequestStatus.TIMEDOUT;
            } else {
                STATUS = ERequestStatus.IO_ERROR; //IO Exception
            }
            return null;

        } catch (XmlPullParserException e) {

            errorLog(e.toString());
            STATUS = ERequestStatus.XML_PULL_PARSER_ERROR; //XML Pull Parser Exception
            return null;

        } catch (Exception e) {

            errorLog(e.toString());
            STATUS = ERequestStatus.CATCH_ALL_ERROR;
            return null;
        }
    }

    protected abstract int getVersion();

    protected abstract String getSoapAction();

    protected abstract SoapObject getBody();

    protected abstract Element[] getHeaders();

    protected <T> T parsePropertyFromSoapObject(SoapObject object, String keyToParse, Class<T> type) {

        return SoapUtils.parsePropertyFromSoapObject(object, keyToParse, type, TAG, shouldLog());
    }

    protected <T> T parsePropertyFromSoapObject(SoapObject object, int index, Class<T> type) {

        return SoapUtils.parsePropertyFromSoapObject(object, index, type, TAG, shouldLog());
    }

    protected <T> T parseAttributeFromSoapObject(SoapObject object, String keyToParse, Class<T> type) {

        return SoapUtils.parseAttributeFromSoapObject(object, keyToParse, type, TAG, shouldLog());
    }

    protected <T> T parseAttributeFromSoapObject(SoapPrimitive object, String keyToParse, Class<T> type) {

        return SoapUtils.parseAttributeFromSoapObject(object, keyToParse, type, TAG, shouldLog());
    }
}
