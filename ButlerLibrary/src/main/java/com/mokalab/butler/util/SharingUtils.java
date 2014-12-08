package com.mokalab.butler.util;

import android.app.Activity;
import android.content.Intent;

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

        //Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setType("text/plain");
        //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //body = Html.fromHtml(body).toString();
        //
        //intent.putExtra(Intent.EXTRA_TEXT, text);
        //activity.startActivity(intent);

        ShareTask.newInstance(activity).execute(subject, url, body);
    }

    /**
     * Starts a Sharing Intent with the provided parameters.
     */
    public static void startSharingIntent(Activity activity, String subject, String text) {

        if (activity == null || activity.isFinishing()) return;

        Intent intent = getBasicActionSendIntent(subject, text);

        activity.startActivity(Intent.createChooser(intent, "Choose..."));
    }

    /**
     * Starts an intent to send an email. Note: this will display a chooser dialog for the user to select their favorite
     * Email App.
     */
    public static void startEmailIntent(Activity activity, String[] emailAddresses, String subject, String body) {

        if (activity == null || activity.isFinishing()) return;

        Intent emailIntent = getBasicActionSendIntent(subject, body);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses);

        activity.startActivity(Intent.createChooser(emailIntent, "Choose Email Client..."));
    }

    /**
     * Returns a basic intent that is used to sharing (ACTION_SEND). It will have the subject and body set.
     */
    public static Intent getBasicActionSendIntent(String subject, String text) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);

        return intent;
    }
}
