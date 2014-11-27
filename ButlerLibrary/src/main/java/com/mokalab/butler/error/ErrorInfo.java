package com.mokalab.butler.error;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * An error that occurred that has to be reported to a listener or on another thread.
 *
 * Created by joshallen on 14-11-27
 */
public class ErrorInfo implements Parcelable {

    private int mErrorCode = 0;
    private String mDebugMessage;
    private String mUserMessage;
    private Bundle mContextData;
    private Throwable mException;

    public ErrorInfo() {
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getDebugMessage() {
        return mDebugMessage;
    }

    public String getUserMessage() {
        return mUserMessage;
    }

    public Bundle getContextData() {
        return mContextData;
    }

    public Throwable getException() {
        return mException;
    }

    /**
     * Parcelable constructor.
     */
    private ErrorInfo(Parcel in) {
        mErrorCode = in.readInt();
        mDebugMessage = in.readString();
        mUserMessage = in.readString();
        mContextData = in.readBundle();
        mException = (Throwable) in.readSerializable();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mErrorCode);
        out.writeString(mDebugMessage);
        out.writeString(mUserMessage);
        out.writeBundle(mContextData);
        out.writeSerializable(mException);
    }

    public static final Creator<ErrorInfo> CREATOR = new Creator<ErrorInfo>() {

        @Override
        public ErrorInfo createFromParcel(Parcel in) {
            return new ErrorInfo(in);
        }

        @Override
        public ErrorInfo[] newArray(int size) {
            return new ErrorInfo[size];
        }
    };

    public static class Builder {

        private ErrorInfo mError = new ErrorInfo();

        public Builder setErrorCode(int errorCode) {
            mError.mErrorCode = errorCode;
            return this;
        }

        public Builder setDebugMessage(String debugMessage) {
            mError.mDebugMessage = debugMessage;
            return this;
        }

        public Builder setUserMessage(String userMessage) {
            mError.mUserMessage = userMessage;
            return this;
        }

        public Builder setContextData(Bundle contextData) {
            mError.mContextData = contextData;
            return this;
        }

        /**
         * Convienience method for creating a bundle and adding a serializable to it.
         */
        public Builder setContextData(Serializable contextData) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", contextData);
            mError.mContextData = bundle;
            return this;
        }

        public Builder setException(Throwable exception) {
            mError.mException = exception;
            return this;
        }

        public ErrorInfo build() {
            if (mError.mException == null) {
                mError.mException = new Exception("ErrorInfo built here");
            }
            return mError;
        }
    }
}
