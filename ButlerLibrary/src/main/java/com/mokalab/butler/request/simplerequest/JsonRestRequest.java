package com.mokalab.butler.request.simplerequest;

import android.content.Context;

/**
 * Created by Pirdad Sakhizada on 29/11/13.
 */
public abstract class JsonRestRequest<P> extends RestRequest<P> {

    public JsonRestRequest(Context context, IRequestID ID, IReqSuccess successListener, IReqFailure failureListener, String url,
                           Method method) {

        super(context, ID, successListener, failureListener, url, method);
    }
}
