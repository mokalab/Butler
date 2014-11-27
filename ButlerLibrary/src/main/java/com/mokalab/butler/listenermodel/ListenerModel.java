package com.mokalab.butler.listenermodel;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by joshallen on 14-11-26.
 */
public class ListenerModel<T extends PostableContainer> implements PostableContainer {

    private Postable mPostable;
    private Set<T> mListeners = Collections.synchronizedSet(new HashSet<T>());

    public ListenerModel(Postable postable) {
        mPostable = postable;
    }

    public ListenerModel() {
    }

    /**
     * @param listener Listener to add. No-op if listener has already been added.
     * @throws NullPointerException If listener is null.
     */
    public void addListener(T listener) {
        Validate.notNull(listener, "listener");
        mListeners.add(listener);
    }

    /**
     * @param listener Listener to remove. No-op if listener is null or has already been removed.
     */
    public void removeListener(T listener) {
        mListeners.remove(listener);
    }

    /**
     * @return Listeners that have been added so far.
     * List is an immutable copy so calling addListener() will not change the list once it has been returned.
     */
    protected List<T> getListeners() {
        return Collections.unmodifiableList(new ArrayList<T>(mListeners));
    }

    protected void post(final ListenerModelCallback callback) {
        for (final PostableContainer listener : getListeners()) {
            Postable postTo = ObjectUtils.firstNonNull(listener.getPostable(), mPostable);
            if (postTo == null) {
                callback.callback(listener);
            } else {
                postTo.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.callback(listener);
                    }
                });
            }
        }
    }

    @Override
    public Postable getPostable() {
        return mPostable;
    }
}


