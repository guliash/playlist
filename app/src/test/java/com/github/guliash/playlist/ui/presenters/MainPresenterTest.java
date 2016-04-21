package com.github.guliash.playlist.ui.presenters;

import com.github.guliash.playlist.interactors.GetSingersInteractor;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.ui.views.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by gulash on 19.04.16.
 */
public class MainPresenterTest {

    private MainPresenter mainPresenter;

    @Mock
    private GetSingersInteractor mockGetSingersInteractor;

    @Mock
    private MainView mockMainView;

    @Mock
    Singer mockSinger;

    private static final String FILTER = "tove";

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mainPresenter = new MainPresenterImpl(mockGetSingersInteractor);
    }

    @Test
    public void viewAttach() {

        mainPresenter.onViewAttach(mockMainView);

        verify(mockMainView).showProgress();
        verify(mockGetSingersInteractor).execute(any(GetSingersInteractor.Callbacks.class));

        verifyNoMoreInteractions(mockMainView);
        verifyNoMoreInteractions(mockGetSingersInteractor);
    }

    @Test
    public void singersSearch() {

        mainPresenter.onSingersSearch(FILTER);

        verify(mockGetSingersInteractor).execute(any(GetSingersInteractor.Callbacks.class));
        verifyNoMoreInteractions(mockGetSingersInteractor);

    }

    @Test
    public void singersRefresh() {

        mainPresenter.onSingersRefresh();

        verify(mockGetSingersInteractor).execute(any(GetSingersInteractor.Callbacks.class));
        verifyNoMoreInteractions(mockGetSingersInteractor);

    }

    @Test
    public void singerClicked() {

        mainPresenter.onViewAttach(mockMainView);

        mainPresenter.onSingerSelected(mockSinger);

        verify(mockMainView).navigateToDescription(any(Singer.class));
    }

}
