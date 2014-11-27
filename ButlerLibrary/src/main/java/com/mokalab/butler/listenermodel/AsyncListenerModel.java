package com.mokalab.butler.listenermodel;

import com.mokalab.butler.error.ErrorInfo;

/**
 * Created by joshallen on 14-11-26.
 */
public class AsyncListenerModel<T> extends ListenerModel<AsyncListener> implements AsyncListener<T> {

    public AsyncListenerModel() {
    }

    public AsyncListenerModel(Postable postable) {
        super(postable);
    }

    @Override
    public void onSuccess(final Object request, final T result) {
        post(new ListenerModelCallback<AsyncListener<T>>() {
           public void callback(AsyncListener<T> t) {
               t.onSuccess(request, result);
           }
        });
    }

    @Override
    public void onError(final Object request, final ErrorInfo error) {
        post(new ListenerModelCallback<AsyncListener<T>>() {
            public void callback(AsyncListener<T> t) {
                t.onError(request, error);
            }
        });
    }

    @Override
    public void onCancelled() {
        post(new ListenerModelCallback<AsyncListener<T>>() {
            public void callback(AsyncListener<T> t) {
                t.onCancelled();
            }
        });
    }
}
