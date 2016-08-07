package com.github.guliash.playlist.utils;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.support.annotation.Nullable;

/**
 * Helper for catching device state
 */
public interface DeviceStateResolver {

    /**
     * Checks whether headphones are plugged in
     * @return {@code true} if plugged
     */
    boolean areHeadphonesPluggedIn();

    /**
     * Returns launch intent for package
     * @param packageName package name
     * @return intent for launch if package installed, {@code null} otherwise
     */
    Intent getLaunchIntentIfPackageInstalled(String packageName);

    /**
     * Checks whether package installed
     * @param packageName package name
     * @return {@code true} if installed
     */
    boolean isPackageInstalled(String packageName);

    /**
     * Returns {@link ApplicationInfo} for the given package.
     * @param packageName package name
     * @return app info if package installed, {@code null} otherwise
     */
    ApplicationInfo getApplicationInfo(String packageName);

}
