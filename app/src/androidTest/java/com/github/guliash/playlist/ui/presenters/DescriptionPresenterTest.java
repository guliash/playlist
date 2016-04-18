package com.github.guliash.playlist.ui.presenters;

import android.content.Context;
import android.test.AndroidTestCase;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.interactors.GetSingerInteractor;
import com.github.guliash.playlist.ui.views.DescriptionView;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Created by gulash on 17.04.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class DescriptionPresenterTest extends AndroidTestCase{

    private DescriptionPresenter descPresenter;

    @Mock
    private Context mockContext;

    @Mock
    private DescriptionView mockDescView;

    @Mock
    private GetSingerInteractor mockGetSingerInteractor;

    @Mock
    private Storage mockStorage;

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        try {
            MockitoAnnotations.initMocks(this);
        } catch (NullPointerException e) {
            //waiting for fix
        }

        descPresenter = new DescriptionPresenterImpl(mockGetSingerInteractor);
    }

    public void testViewAttach() {
        descPresenter.onViewAttach(mockDescView);

        doNothing().when(mockGetSingerInteractor).execute(any(GetSingerInteractor.Callbacks.class),
                anyInt());
        descPresenter.getSinger(45);
        verify(mockDescView).showLoading();
        verify(mockDescView).hideLoading();
        verify(mockGetSingerInteractor).execute(any(GetSingerInteractor.Callbacks.class),
                anyInt());
    }
}
