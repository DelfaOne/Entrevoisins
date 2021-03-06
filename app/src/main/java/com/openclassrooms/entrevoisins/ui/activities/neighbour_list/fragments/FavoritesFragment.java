package com.openclassrooms.entrevoisins.ui.activities.neighbour_list.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.RefreshDataEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.activities.neighbour_detail.NeighbourDetailActivity;
import com.openclassrooms.entrevoisins.ui.activities.neighbour_list.MyNeighbourRecyclerViewAdapter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment implements MyNeighbourRecyclerViewAdapter.OnUserListener {

    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    private MyNeighbourRecyclerViewAdapter myNeighbourRecyclerViewAdapter;


    public static FavoritesFragment newInstance() {
        FavoritesFragment favorisFragment = new FavoritesFragment();
        return favorisFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void onRefreshData(RefreshDataEvent refreshDataEvent) {

    }

    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteFavorite(event.neighbour);
        initList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        });
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        myNeighbourRecyclerViewAdapter = new MyNeighbourRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(myNeighbourRecyclerViewAdapter);
        return view;
    }

    private void initList() {
        myNeighbourRecyclerViewAdapter.submitList(mApiService.getFavorites());
    }

    @Override
    public void onUserClick(Neighbour neighbour) {
        Intent intent = new Intent(getContext(), NeighbourDetailActivity.class);
        intent.putExtra("Neighbour", neighbour);
        startActivity(intent);
    }
}