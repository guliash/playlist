package com.github.guliash.playlist.utils;

import android.content.Intent;
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
     * @param name package name
     * @return intent for launch if package installed, {@code null} otherwise
     */
    Intent getLaunchIntentIfPackageInstalled(String name);

    /**
     * Checks whether package installed
     * @param name package name
     * @return {@code true} if installed
     */
    boolean isPackageInstalled(String name);

}
