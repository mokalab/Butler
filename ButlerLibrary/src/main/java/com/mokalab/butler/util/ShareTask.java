package com.mokalab.butler.util;

import android.app.Activity;

import com.mokalab.butler.request.simplerequest.ERequestStatus;
import com.mokalab.butler.request.simplerequest.IReqFailure;
import com.mokalab.butler.request.simplerequest.IReqSuccess;
import com.mokalab.butler.request.simplerequest.IRequestID;
import com.mokalab.butler.request.simplerequest.bitdourlshortener.UrlShortner;

import java.lang.ref.WeakReference;

/**
 * Use this class to generate a Sharing Intent which also features
 * auto link shortening as well the max allowable characters (defined by Twitter).
 * <br><br>
 * Created by Pirdad S on 14-11-15.
 */
public class ShareTask implements IRequestID, IReqSuccess<String>, IReqFailure {

    private static final int MAX_BODY_LENGTH = 140;
    private static final int ELLIPSIS_LENGTH = 3;
    private static final String URL_PREPEND_CHARS = "\n\n";

    private WeakReference<Activity> mActivityRef;
    private String mSubject;
    private String mBody;

    private ShareTask(Activity activity) {

        mActivityRef = new WeakReference<Activity>(activity);
    }

    /**
     * Get new instance of the Share Task.
     */
    public static ShareTask newInstance(Activity activity) {

        return new ShareTask(activity);
    }

    /**
     * Trigger the start of this task.
     */
    public void execute(String subject, String url, String body) {

        mSubject = subject;
        mBody = body;

        if (TextUtils.isLengthGreaterThan(url, 20)) {

            /* URL LENGTH IS GREATER THAN 20 CHARS */
            /* SHORTEN THE URL */

            if (!ActivityUtils.isContextValid(mActivityRef.get())) return;
            new UrlShortner(mActivityRef.get(), url, this, this, this).execute();

        } else {

            /* URL IS EMPTY OR LESS THAN 20 CHARS */
            onUrlReady(url);
        }
    }

    @Override
    public void onRequestSuccess(IRequestID ID, String data) {

        onUrlReady(data);
    }

    @Override
    public void onRequestFailed(IRequestID ID, ERequestStatus STATUS) {

        onUrlReady(null);
    }

    @Override
    public int getIntId() {
        return 0;
    }

    @Override
    public String getStringId() {
        return "urlShortened";
    }

    /**
     * Removes the extra characters from the end of the body if the
     * entire text is longer than the max required length.
     */
    private void onUrlReady(String url) {

        if (!ActivityUtils.isContextValid(mActivityRef.get())) return;

        String text = mBody;
        if (!TextUtils.isEmpty(url)) {
            text += URL_PREPEND_CHARS + url;
        }

        if (text.length() > MAX_BODY_LENGTH) {

            text = getShortenedBody(url) + URL_PREPEND_CHARS + url;
        }

        SharingUtils.startSharingIntent(mActivityRef.get(), mSubject, text);
    }

    /**
     * Ex.
     *
     * Url = 20 chars
     * NewLines = 2 chars
     * Body = 150 chars
     * All = 172 chars
     * MaxAllLength = 140 chars
     * Ellipsis = 3 chars
     *
     * Algorithm:
     * RequiredBody = 118 = 140 - (20 + 2)
     * CharsToRemove = 29 = 150 - 118 - 3
     *
     */
    private String getShortenedBody(String url) {

        int requiredBodyLength = MAX_BODY_LENGTH;
        if (!TextUtils.isEmpty(url)) {
            requiredBodyLength = MAX_BODY_LENGTH - (URL_PREPEND_CHARS + url).length();
        }

        int charsToRemove = mBody.length() - requiredBodyLength - ELLIPSIS_LENGTH;

        int bodySubStrStart = 0;
        int bodySubStrEnd = mBody.length() - charsToRemove - 1;

        return mBody.substring(bodySubStrStart, bodySubStrEnd) + "...";
    }
}
