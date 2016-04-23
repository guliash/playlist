package com.github.guliash.playlist.interactors;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.executors.PostExecutor;
import com.github.guliash.playlist.executors.ThreadExecutor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class GetSingersInteractorTest {

    @Mock
    private Storage mockStorage;

    @Mock
    private ThreadExecutor mockExecutor;

    @Mock
    private PostExecutor mockPostExecutor;

    @Mock
    private GetSingersInteractor.Callbacks mockCallbacks;

    private GetSingersInteractor getSingersInteractor;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        getSingersInteractor = new GetSingersInteractorImpl(mockStorage, mockExecutor, mockPostExecutor);

    }

    @Test
    public void execution() {
        getSingersInteractor.execute(mockCallbacks);
        verify(mockExecutor).execute(any(Runnable.class));
    }

    @Test
    public void runWithoutError() throws Throwable {
        getSingersInteractor.execute(mockCallbacks);
        getSingersInteractor.run();

        verify(mockExecutor).execute(any(Runnable.class));
        verify(mockStorage).getSingers();
        verify(mockPostExecutor).execute(any(Runnable.class));

        verifyNoMoreInteractions(mockStorage);
        verifyNoMoreInteractions(mockExecutor);
        verifyNoMoreInteractions(mockPostExecutor);
    }

    @Test
    public void runWithError() throws Throwable {
        doThrow(Throwable.class).when(mockStorage).getSingers();

        getSingersInteractor.execute(mockCallbacks);
        getSingersInteractor.run();

        verify(mockExecutor).execute(any(Runnable.class));
        verify(mockStorage).getSingers();
        verify(mockPostExecutor).execute(any(Runnable.class));

        verifyNoMoreInteractions(mockStorage);
        verifyNoMoreInteractions(mockExecutor);
        verifyNoMoreInteractions(mockPostExecutor);
    }



}
