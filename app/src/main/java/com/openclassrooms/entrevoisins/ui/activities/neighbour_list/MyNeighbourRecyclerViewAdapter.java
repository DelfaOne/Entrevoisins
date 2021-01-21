package com.openclassrooms.entrevoisins.ui.activities.neighbour_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> { //Le type est le nom de l'adapteur créer

    private final List<Neighbour> items;
    private OnUserListener mOnUserListener;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, OnUserListener onUserListener) {
        this.items = items;
        this.mOnUserListener = onUserListener;
    } //Constructeur qui prend en paramétre une List de type Neighbour



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { //Permet de récupérer les élements View de fragment_neighbour_list
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        OnUserListener onUserListener;

        public ViewHolder(View view, OnUserListener onUserListener) { //Constructeur qui prend en paramétre une view
            super(view); // ????
            ButterKnife.bind(this, view); //Va bind tout ce qu'on lui à affecter au dessus
            this.onUserListener = onUserListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onUserListener.onUserClick(getAdapterPosition());
        }
    }

    public interface OnUserListener {
        void onUserClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour_item, parent, false);
        return new ViewHolder(view, mOnUserListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = items.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
