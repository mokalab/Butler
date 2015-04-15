package com.mokalab.butler.request.async;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * This is a simple Asynchronous task which can be use anonymously and inline.
 */
public class SimpleAsync<I, O> extends AsyncTask<Void, Void, O> {

    private IBackroundExecutor<I, O> mTaskExecutor;
    private ICompletionListener<O> mCompletionListener;
    private I mInput;
    private WeakReference<Context> mContextRef;

    public SimpleAsync(I input, IBackroundExecutor<I, O> taskExecutor, ICompletionListener<O> completionListener) {

        mInput = input;
        mTaskExecutor = taskExecutor;
        mCompletionListener = completionListener;
    }

    public SimpleAsync(Context context, I input, IBackroundExecutor<I, O> taskExecutor, ICompletionListener<O> completionListener) {

        this(input, taskExecutor, completionListener);
        mContextRef = new WeakReference<>(context);
    }

    @Override
    protected O doInBackground(Void... params) {

        if (mTaskExecutor != null) {
            Context context = (mContextRef != null) ? mContextRef.get() : null;
            return mTaskExecutor.doInBackground(context, mInput);
        }

        return null;
    }

    @Override
    protected void onPostExecute(O output) {

        if (mCompletionListener != null) {
            mCompletionListener.onComplete(output);
        }
    }

    public interface IBackroundExecutor<I, O> {
        public O doInBackground(@Nullable Context context, I input);
    }

    public interface ICompletionListener<O> {
        public void onComplete(@Nullable O output);
    }
}
