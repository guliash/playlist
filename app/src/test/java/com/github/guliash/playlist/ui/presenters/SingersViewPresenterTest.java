package com.github.guliash.playlist.ui.presenters;

import android.content.Context;

import com.github.guliash.playlist.interactors.GetAppsInteractor;
import com.github.guliash.playlist.interactors.GetSingersInteractor;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.ui.views.SingersView;
import com.github.guliash.playlist.utils.FakeDeviceStateResolver;

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
public class SingersViewPresenterTest {

    private SingersViewPresenter singersViewPresenter;

    @Mock
    private GetSingersInteractor mockGetSingersInteractor;

    @Mock
    private GetAppsInteractor mockGetAppsInteractor;

    @Mock
    private SingersView mockSingersView;

    @Mock
    private FakeDeviceStateResolver mockStateResolver;

    @Mock
    private Context mockContext;

    @Mock
    Singer mockSinger;

    private static final String FILTER = "tove";

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        singersViewPresenter = new SingersViewPresenterImpl(mockGetSingersInteractor, mockGetAppsInteractor,
                mockStateResolver, mockContext);
    }

    @Test
    public void viewAttach() {

        singersViewPresenter.onViewAttach(mockSingersView);

        verify(mockSingersView).showProgress();
        verify(mockGetSingersInteractor).execute(any(GetSingersInteractor.Callbacks.class));

        verifyNoMoreInteractions(mockSingersView);
        verifyNoMoreInteractions(mockGetSingersInteractor);
    }

    @Test
    public void singersSearch() {

        singersViewPresenter.onSingersSearch(FILTER);

        verify(mockGetSingersInteractor).execute(any(GetSingersInteractor.Callbacks.class));
        verifyNoMoreInteractions(mockGetSingersInteractor);

    }

    @Test
    public void singersRefresh() {

        singersViewPresenter.onSingersRefresh();

        verify(mockGetSingersInteractor).execute(any(GetSingersInteractor.Callbacks.class));
        verifyNoMoreInteractions(mockGetSingersInteractor);

    }

    @Test
    public void singerClicked() {

        singersViewPresenter.onViewAttach(mockSingersView);

        singersViewPresenter.onSingerSelected(mockSinger);

        verify(mockSingersView).navigateToDescription(any(Singer.class));
    }

}
