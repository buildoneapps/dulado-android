package com.buildone.dulado.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babic.filip.flexibleadapter.FlexibleAdapter;
import com.buildone.dulado.R;
import com.buildone.dulado.contracts.MainListContract;
import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.ui.adapter.holder.SearchGridHolder;
import com.buildone.dulado.ui.adapter.holder.SearchHolder;
import com.buildone.dulado.utils.HidingScrollListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public class MainListFragment extends BaseFragment implements MainListContract.View {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    MainListContract.Presenter presenter;

    @BindView(R.id.rvNear)
    RecyclerView rvNear;

    private Unbinder unbinder;
    private FlexibleAdapter<SearchHolder> listAdapter;
    private FlexibleAdapter<SearchGridHolder> gridAdapter;
    private RecyclerView.OnScrollListener scrollingListener;

    //region Android Lifecycle
    public MainListFragment() {
        // Required empty public constructor
    }

    public static MainListFragment newInstance() {
        MainListFragment fragment = new MainListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        AndroidSupportInjection.inject(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        presenter.start();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        presenter.unsubscribeAll();
        super.onDestroy();
    }

    //endregion

    //region MainListContract.View
    @Override
    public void initListRecyclerView() {
        rvNear.setHasFixedSize(true);
        rvNear.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        listAdapter = new FlexibleAdapter<>();
        rvNear.setAdapter(listAdapter);

        if (scrollingListener != null) {
            rvNear.removeOnScrollListener(scrollingListener);
        }
        scrollingListener = new HidingScrollListener() {
            @Override
            public void onHide() {
                presenter.onHide();
            }

            @Override
            public void onShow() {
                presenter.onShow();
            }
        };
        rvNear.addOnScrollListener(scrollingListener);

    }

    @Override
    public void populateListRecyclerView(ArrayList<SearchObject> items) {
        ArrayList<SearchHolder> holders = new ArrayList<>();
        for (SearchObject object : items) {
            holders.add(new SearchHolder(getActivity(), object));
        }
        listAdapter.setItems(holders);
    }

    @Override
    public void initGridRecyclerView() {
        rvNear.setHasFixedSize(true);
        rvNear.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        gridAdapter = new FlexibleAdapter<>();
        rvNear.setAdapter(gridAdapter);

        if (scrollingListener != null) {
            rvNear.removeOnScrollListener(scrollingListener);
        }
        scrollingListener = new HidingScrollListener() {
            @Override
            public void onHide() {
                presenter.onHide();
            }

            @Override
            public void onShow() {
                presenter.onShow();
            }
        };
        rvNear.addOnScrollListener(scrollingListener);
    }

    @Override
    public void populateGridRecyclerView(ArrayList<SearchObject> items) {
        ArrayList<SearchGridHolder> holders = new ArrayList<>();
        for (SearchObject object : items) {
            holders.add(new SearchGridHolder(getActivity(), object));
        }
        gridAdapter.setItems(holders);
    }

    @Override
    public void navigateToProductActivity(SearchObject product) {

    }

    @Override
    public void navigateToChatActivity(int productId) {

    }

    @Override
    public void showEmptyMessage() {

    }

    //endregion
}
