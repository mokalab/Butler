package com.mokalab.butler.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Base Class for Fragment State Pager Adapter.
 * <br>
 * Defines a SparseArray that holds weak references to the page fragments. You may
 * get the registered fragment by calling {@link #getRegisteredFragment(int)}.
 * <br><br>
 * Created by pirdad on 1/26/2014.
 */
public abstract class BaseFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private SparseArray<WeakReference<Fragment>> registeredFragments;

    /**
     * Construct new Fragment Pager Adapter.
     */
    public BaseFragmentStatePagerAdapter(FragmentManager fm) {

        super(fm);
        registeredFragments = new SparseArray<WeakReference<Fragment>>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        if (registeredFragments != null) {
            registeredFragments.put(position, new WeakReference<Fragment>(fragment));
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        if (registeredFragments != null) {
            registeredFragments.remove(position);
        }
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {

        WeakReference<Fragment> fr = registeredFragments.get(position);
        return (fr != null) ? fr.get() : null;
    }
}