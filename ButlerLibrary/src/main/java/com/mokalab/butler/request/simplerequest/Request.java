package com.mokalab.butler.request.simplerequest;

import android.content.Context;
import android.os.AsyncTask;

import com.mokalab.butler.util.MrLogger;
import com.mokalab.butler.util.NetworkUtils;

/**
 * This class provides a generic flow for executing and parsing a request. It can be extended for any situation that can be considered a request.
 * <br><br>ie. {@link RestRequest}
 *
 * @author Pirdad Sakhizada on 29/11/13.
 */
public abstract class Request<T, P> {

    protected static final String TAG = "Request";

    protected IRequestID mID;
    protected RequestState mRequestState = RequestState.PENDING;
    protected ERequestStatus STATUS = ERequestStatus.NONE;

    protected IReqSuccess mSuccessListener;
    protected IReqFailure mFailureListener;

    private Executor mExecutor;
    protected boolean mCancelled = false;
    protected boolean mLogEnabled = false;

    public enum RequestState {
        PENDING,
        EXECUTE,
        COMPLETE
    }

    public Request(Context context, IRequestID ID, IReqSuccess successListener, IReqFailure failureListener) {

        mID = ID;
        mSuccessListener = successListener;
        mFailureListener = failureListener;

        if (!isNetworkAvailable(context)) { // No Network

            STATUS = ERequestStatus.NO_NETWORK;
        }
    }

    /**
     * Enables or disables logging.
     */
    public void setShouldLog(boolean shouldLog) {
        mLogEnabled = shouldLog;
    }

    /**
     * Use this to initiate the execution process of this request.
     */
    public void execute() {

        mRequestState = RequestState.EXECUTE;

        mExecutor = new Executor();
        mExecutor.execute();
    }

    /**
     * Executor AsyncTask which has two responsibilities. <br><br>
     * 1) Execute the request and fetch response<br>
     * 2) Parse response and return to listener
     */
    private class Executor extends AsyncTask<Void, Void, P> {

        @Override
        protected P doInBackground(Void... voids) {

            if (STATUS == ERequestStatus.NO_NETWORK) {
                return null;
            }

            T response = onExecute();

            // IS CANCELLED BEFORE PARSE
            if (isCancelled()) {
                cancelled();
                return null;
            }

            if (STATUS != ERequestStatus.NONE) {
                return null;
            }

            P parsedData;
            if (response != null) {
                parsedData = onParse(response);
            } else {
                STATUS = ERequestStatus.RESPONSE_NULL;
                parsedData = null;
            }

            // IS CANCELLED AFTER PARSE
            if (isCancelled()) {
                cancelled();
                return null;
            }

            return parsedData;
        }

        @Override
        protected void onPostExecute(P parsedData) {

            if (parsedData != null) {
                notifySuccess(mID, parsedData);
            } else {
                notifyFailure();
            }
        }
    }

    /**
     * Override to define the logic for execution process.
     *
     * @return
     */
    protected abstract T onExecute();

    /**
     * Override to define the logic for parsing process.
     *
     * @param response
     * @return
     */
    protected abstract P onParse(T response);

    /**
     * Use this to cancel the current request.
     */
    public void cancel() {

        debugLog("Cancelled!");
        mCancelled = true;
        mSuccessListener = null;
        mFailureListener = null;
    }

    /**
     * Use this utility method from AsyncTask to set ERequestStatus.CANCELLED and set the cancelled state.
     */
    protected void cancelled() {

        STATUS = ERequestStatus.CANCELLED;

        mSuccessListener = null;
        mFailureListener = null;
    }

    /**
     * Reset the success listener.
     *
     * @param successListener
     */
    public void resetSuccessListener(IReqSuccess successListener) {

        mSuccessListener = successListener;
    }

    /**
     * Reset the failure listener.
     *
     * @param failureListener
     */
    public void resetFailListener(IReqFailure failureListener) {

        mFailureListener = failureListener;
    }

    /**
     * Get this request's ID.
     *
     * @return
     */
    public IRequestID getID() {

        return mID;
    }

    /**
     * Checks for network connection. Good to use before execution of request.
     *
     * @return
     * @param context
     */
    private boolean isNetworkAvailable(Context context) {

        return NetworkUtils.isConnectedToNetwork(context);
    }

    /**
     * Call to notify the failure listener.
     */
    protected void notifyFailure() {

        if (mFailureListener != null) {
            mFailureListener.onRequestFailed(mID, STATUS);
        }
        mRequestState = RequestState.COMPLETE;
    }

    /**
     * Call to notify the success listener.
     *
     * @param ID
     * @param parsedData
     */
    protected void notifySuccess(IRequestID ID, P parsedData) {

        if (mSuccessListener != null) {
            mSuccessListener.onRequestSuccess(ID, parsedData);
        }
        mRequestState = RequestState.COMPLETE;
    }

    /**
     * Check if the request is complete.
     *
     * @return
     */
    public boolean isComplete() {

        return mRequestState == RequestState.COMPLETE;
    }

    /**
     * Check if the request is cancelled.
     *
     * @return
     */
    public boolean isCancelled() {

        return mCancelled;
    }

    public abstract String getUrl();

    /**
     * Use to log out a debug message.
     *
     * @param message
     */
    protected void debugLog(String message) {

        String id = "";
        if (mID != null) {
            id = mID.getStringId();
        }
        MrLogger.debug(TAG, id + "-> " + message, shouldLog());
    }

    /**
     * Use to log out a error message.
     *
     * @param message
     */
    protected void errorLog(String message) {

        String id = "";
        if (mID != null) {
            id = mID.toString();
        }
        MrLogger.error(TAG, id + "-> " + message, shouldLog());
    }

    public RequestState getRequestState() {

        return mRequestState;
    }

    protected boolean shouldLog() {
        return mLogEnabled;
    }
}
