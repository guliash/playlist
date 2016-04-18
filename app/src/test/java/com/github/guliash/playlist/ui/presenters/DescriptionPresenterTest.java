package com.github.guliash.playlist.ui.presenters;

import android.content.Context;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.interactors.GetSingerInteractor;
import com.github.guliash.playlist.ui.views.DescriptionView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

/**
 * Created by gulash on 17.04.16.
 */
public class DescriptionPresenterTest {

    private DescriptionPresenter descPresenter;

    @Mock
    private Context mockContext;

    @Mock
    private DescriptionView mockDescView;

    @Mock
    private GetSingerInteractor mockGetSingerInteractor;

    @Mock
    private Storage mockStorage;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        descPresenter = new DescriptionPresenterImpl(mockGetSingerInteractor);
    }

    @Test
    public void testGetSingerFlow() {
        descPresenter.onViewAttach(mockDescView);
        descPresenter.getSinger(45);
        verify(mockGetSingerInteractor).execute(any(GetSingerInteractor.Callbacks.class),
                anyInt());
        verify(mockDescView).showLoading();
    }




}
