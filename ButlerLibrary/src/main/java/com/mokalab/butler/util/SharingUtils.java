package com.mokalab.butler.util;

import android.app.Activity;
import android.content.Intent;
import android.text.Html;

/**
 * Created by Pirdad S on 2014-09-16.
 */
public class SharingUtils {

    private SharingUtils() {}

    /**
     * Starts a Sharing Intent with the provided subject and url.
     */
    public static void startSharingUrl(Activity activity, String subject, String url, String body) {

        if (activity == null || activity.isFinishing()) return;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        body = Html.fromHtml(body).toString();
        String text = body + "\n\n" + url;
        if (text.length() > 140) {
            if (url != null || url != "") {
                String shortenedBody = body;
                if (body.length() > 114) {
                    shortenedBody = body.substring(0, 113) + "...";
                }
                text = shortenedBody + "\n\n" + url;
            } else {
                text = text.substring(0, 137) + "...";
            }
        }

        intent.putExtra(Intent.EXTRA_TEXT, text);
        activity.startActivity(intent);
    }
}
