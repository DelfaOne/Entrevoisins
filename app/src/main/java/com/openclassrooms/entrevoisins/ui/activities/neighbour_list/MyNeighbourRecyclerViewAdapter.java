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

    private List<Neighbour> items;
    private OnUserListener mOnUserListener;

    public MyNeighbourRecyclerViewAdapter(OnUserListener onUserListener) {
        this.mOnUserListener = onUserListener;
    } //Constructeur qui prend en paramétre une List de type Neighbour

    public void submitList(List<Neighbour> items) {
        this.items = items;
    }

    public interface OnUserListener {
        void onUserClick(Neighbour neighbour);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //Appeller à chaque fois que RecyclerView crée un nouveau ViewHolder
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) { //Appeller pour associer un ViewHolder à ses données
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnUserListener.onUserClick(neighbour);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{ //Permet de récupérer les élements de la vue
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    } //Retourne la taille entiére


}
