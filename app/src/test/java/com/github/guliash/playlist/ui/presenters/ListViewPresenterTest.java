package com.github.guliash.playlist.ui.presenters;

import android.content.Context;

import com.github.guliash.playlist.interactors.GetAppsInteractor;
import com.github.guliash.playlist.interactors.GetSingersInteractor;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.ui.views.ListView;
import com.github.guliash.playlist.utils.DeviceStateResolver;
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
public class ListViewPresenterTest {

    private ListViewPresenter listViewPresenter;

    @Mock
    private GetSingersInteractor mockGetSingersInteractor;

    @Mock
    private GetAppsInteractor mockGetAppsInteractor;

    @Mock
    private ListView mockListView;

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

        listViewPresenter = new ListViewPresenterImpl(mockGetSingersInteractor, mockGetAppsInteractor,
                mockStateResolver, mockContext);
    }

    @Test
    public void viewAttach() {

        listViewPresenter.onViewAttach(mockListView);

        verify(mockListView).showProgress();
        verify(mockGetSingersInteractor).execute(any(GetSingersInteractor.Callbacks.class));

        verifyNoMoreInteractions(mockListView);
        verifyNoMoreInteractions(mockGetSingersInteractor);
    }

    @Test
    public void singersSearch() {

        listViewPresenter.onSingersSearch(FILTER);

        verify(mockGetSingersInteractor).execute(any(GetSingersInteractor.Callbacks.class));
        verifyNoMoreInteractions(mockGetSingersInteractor);

    }

    @Test
    public void singersRefresh() {

        listViewPresenter.onSingersRefresh();

        verify(mockGetSingersInteractor).execute(any(GetSingersInteractor.Callbacks.class));
        verifyNoMoreInteractions(mockGetSingersInteractor);

    }

    @Test
    public void singerClicked() {

        listViewPresenter.onViewAttach(mockListView);

        listViewPresenter.onSingerSelected(mockSinger);

        verify(mockListView).navigateToDescription(any(Singer.class));
    }

}
