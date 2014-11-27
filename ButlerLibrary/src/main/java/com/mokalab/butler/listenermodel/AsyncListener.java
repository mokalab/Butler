package com.mokalab.butler.listenermodel;

import com.mokalab.butler.error.ErrorInfo;

/**
 * Created by joshallen on 14-11-26.
 */
public interface AsyncListener<T> extends PostableContainer {

    void onSuccess(Object request, T result);

    void onError(Object request, ErrorInfo error);

    void onCancelled();

}
