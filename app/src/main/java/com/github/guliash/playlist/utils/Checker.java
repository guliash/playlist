package com.github.guliash.playlist.utils;

/**
 * Helper class. Contains methods for checking conditions.
 */
public class Checker {

    /**
     * Checks whether the object is null.
     * @param object the object to check
     * @throws NullPointerException if {@code object == null}
     */
    public static void notNull(Object object) throws NullPointerException {
        if(object == null) {
            throw new NullPointerException();
        }
    }

}
