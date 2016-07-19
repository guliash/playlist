package com.github.guliash.playlist.interactors;

import android.content.pm.ApplicationInfo;

import java.util.List;

/**
 * Gets apps installed on phone
 */
public interface GetAppsInteractor extends Interactor {

    /**
     * Callback which returns the result
     */
    interface Callbacks {
        /**
         * Returns the retrieved apps
         * @param apps the apps
         */
        void onApps(List<ApplicationInfo> apps);
    }

    /**
     * Executes the interactor
     * @param callbacks the callback
     * @param apps the apps which info we need
     */
    void execute(Callbacks callbacks, List<String> apps);

}
