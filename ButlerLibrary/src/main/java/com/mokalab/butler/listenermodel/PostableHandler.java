package com.mokalab.butler.listenermodel;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by joshallen on 14-11-26.
 */
public class PostableHandler extends Handler implements Postable {

    public static PostableHandler UI_THREAD = new PostableHandler(Looper.getMainLooper());

    public PostableHandler(Looper looper) {
        super(looper);
    }

}
