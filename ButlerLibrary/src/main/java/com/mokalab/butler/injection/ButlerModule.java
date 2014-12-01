package com.mokalab.butler.injection;

import android.content.Context;

import com.mokalab.butler.error.BuildInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joshallen on 14-11-28
 */
@Module (
        injects = BuildInfo.class
)
public class ButlerModule {

}
