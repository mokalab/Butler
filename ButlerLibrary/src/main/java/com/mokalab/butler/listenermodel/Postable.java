package com.mokalab.butler.listenermodel;

/**
 * Created by joshallen on 14-11-26.
 */
public interface Postable {

    boolean post(Runnable task);
}
