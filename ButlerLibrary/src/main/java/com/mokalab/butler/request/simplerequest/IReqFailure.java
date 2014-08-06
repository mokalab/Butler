package com.mokalab.butler.request.simplerequest;

/**
 * Created by Pirdad Sakhizada on 29/11/13.
 */
public interface IReqFailure {

    /**
     * This method is called when the request using {@link com.mokalab.butler.request.simplerequest.Request} fails for any supported reasons.
     *
     * @param ID Id of the request that was being made
     * @param STATUS Failure status of the request
     */
    public void onRequestFailed(IRequestID ID, ERequestStatus STATUS);
}
