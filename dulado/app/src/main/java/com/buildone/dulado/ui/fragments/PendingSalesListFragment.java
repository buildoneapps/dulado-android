package com.buildone.dulado.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babic.filip.flexibleadapter.FlexibleAdapter;
import com.buildone.dulado.R;
import com.buildone.dulado.contracts.PendingSalesContract;
import com.buildone.dulado.model.PendingSaleObject;
import com.buildone.dulado.ui.adapter.holder.PendingSaleHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public class PendingSalesListFragment extends BaseFragment implements PendingSalesContract.View {


    @Inject
    PendingSalesContract.Presenter presenter;

    @BindView(R.id.rvNear)
    RecyclerView rvNear;

    private Unbinder unbinder;
    private FlexibleAdapter<PendingSaleHolder> listAdapter;

    //region Android Lifecycle
    public PendingSalesListFragment() {
        // Required empty public constructor
    }

    public static PendingSalesListFragment newInstance() {
        PendingSalesListFragment fragment = new PendingSalesListFragment();
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
        presenter.dispose();
        super.onDestroy();
    }

    //endregion

    //region PendingSalesContract.View
    @Override
    public void initListRecyclerView() {
        rvNear.setHasFixedSize(true);
        rvNear.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        listAdapter = new FlexibleAdapter<>();
        rvNear.setAdapter(listAdapter);

    }

    @Override
    public void populateListRecyclerView(ArrayList<PendingSaleObject> items) {
        ArrayList<PendingSaleHolder> holders = new ArrayList<>();
        for (PendingSaleObject object : items) {
            holders.add(new PendingSaleHolder(getActivity(), object));
        }
        listAdapter.setItems(holders);
    }

    @Override
    public void showEmptyMessage() {

    }

    //endregion
}
