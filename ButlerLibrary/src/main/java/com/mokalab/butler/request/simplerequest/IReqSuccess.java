package com.mokalab.butler.request.simplerequest;

/**
 * Created by Pirdad Sakhizada on 29/11/13.
 */
public interface IReqSuccess<T> {

    /**
     * This method is called when the request using {@link com.mokalab.butler.request.simplerequest.Request} completes (may include the parsed data).
     *
     * @param ID Id of the request that was being made
     * @param data the parsed response
     */
    public void onRequestSuccess(IRequestID ID, T data);
}
