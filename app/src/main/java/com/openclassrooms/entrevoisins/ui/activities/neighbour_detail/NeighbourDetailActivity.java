package com.openclassrooms.entrevoisins.ui.activities.neighbour_detail;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.RefreshDataEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.activities.neighbour_list.ListNeighbourActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailActivity extends AppCompatActivity {

    private static final String TAG = "NeighbourDetailActivity";

    @BindView(R.id.back_btn)
    ImageButton mBackBtn;
    @BindView(R.id.fav_float_btn)
    FloatingActionButton mFloatBtn;
    @BindView(R.id.username_txt)
    public TextView mNameTxt;
    @BindView(R.id.user_img)
    public ImageView mImage;
    @BindView(R.id.second_username_txt)
    public TextView mNameSecondTxt;
    @BindView(R.id.address_txt)
    public TextView mLocation;
    @BindView(R.id.phone_txt)
    public TextView mPhone;
    @BindView(R.id.facebook_txt)
    public TextView mFacebook;

    private NeighbourApiService mApiService = DI.getNeighbourApiService();
    private Neighbour m_Neighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        ButterKnife.bind(this);

        getSupportActionBar().hide();

        getNeighbourData();

        setContent(m_Neighbour);

        backButtonClick();

        addFav();
    }

    public void setContent(Neighbour neighbour) {
        mNameTxt.setText(neighbour.getName());
        Glide.with(this)
                .load(Uri.parse(neighbour.getAvatarUrl()))
                .centerCrop()
                .into(mImage);
        mNameSecondTxt.setText(neighbour.getName());
        mLocation.setText(neighbour.getAddress());
        mPhone.setText(neighbour.getPhoneNumber());
        mFacebook.setText("www.facebook.fr/" + neighbour.getName());
    }

    public Neighbour getNeighbourData() {
        Intent intent = getIntent();
        return m_Neighbour = intent.getParcelableExtra("Neighbour");
    }

    public void backButtonClick() {
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ListNeighbourActivity.class);
                startActivity(intent);
            }
        });
    }


    public void addFav() {
        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiService.addFavorites(m_Neighbour);
                EventBus.getDefault().post(new RefreshDataEvent());
            }
        });
    }
}