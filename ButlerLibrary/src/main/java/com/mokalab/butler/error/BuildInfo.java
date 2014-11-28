package com.mokalab.butler.error;

import javax.inject.Inject;

/**
 * Created by joshallen on 14-11-28
 */
public class BuildInfo {

    /** Modified by text replacement during build process. */
    private static boolean IS_DEBUG = true;

    @Inject
    public BuildInfo() {
    }

    public boolean isDebug() {
        return IS_DEBUG;
    }
}
