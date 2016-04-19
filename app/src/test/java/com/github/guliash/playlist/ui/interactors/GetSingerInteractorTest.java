package com.github.guliash.playlist.ui.interactors;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.interactors.GetSingerInteractor;
import com.github.guliash.playlist.interactors.GetSingerInteractorImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by gulash on 18.04.16.
 */
public class GetSingerInteractorTest {

    @Mock
    private Storage mockStorage;

    @Mock
    private Executor mockExecutor;

    @Mock
    private Executor mockPostExecutor;

    @Mock
    private GetSingerInteractor.Callbacks mockCallbacks;

    private GetSingerInteractor getSingerInteractor;

    private static final int SINGER_ID = 45;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        getSingerInteractor = new GetSingerInteractorImpl(mockStorage, mockExecutor, mockPostExecutor);

    }

    @Test
    public void execution() {
        getSingerInteractor.execute(mockCallbacks, SINGER_ID);
        verify(mockExecutor).execute(any(Runnable.class));
    }

    @Test
    public void runWithoutError() throws Throwable {
        getSingerInteractor.execute(mockCallbacks, SINGER_ID);
        getSingerInteractor.run();

        verify(mockExecutor).execute(any(Runnable.class));
        verify(mockStorage).getSinger(anyInt());
        verify(mockPostExecutor).execute(any(Runnable.class));

        verifyNoMoreInteractions(mockStorage);
        verifyNoMoreInteractions(mockExecutor);
        verifyNoMoreInteractions(mockPostExecutor);

    }

    @Test
    public void runWithError() throws Throwable {
        doThrow(Throwable.class).when(mockStorage).getSinger(anyInt());

        getSingerInteractor.execute(mockCallbacks, SINGER_ID);
        getSingerInteractor.run();

        verify(mockExecutor).execute(any(Runnable.class));
        verify(mockStorage).getSinger(anyInt());
        verify(mockPostExecutor).execute(any(Runnable.class));

        verifyNoMoreInteractions(mockStorage);
        verifyNoMoreInteractions(mockExecutor);
        verifyNoMoreInteractions(mockPostExecutor);
    }

}
