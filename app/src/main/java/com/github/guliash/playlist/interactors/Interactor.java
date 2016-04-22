package com.github.guliash.playlist.interactors;

/**
 * Base interface for interactors
 */
public interface Interactor extends Runnable {

    /**
     * Runs the interactor on the separate thread
     */
    void run();

}
