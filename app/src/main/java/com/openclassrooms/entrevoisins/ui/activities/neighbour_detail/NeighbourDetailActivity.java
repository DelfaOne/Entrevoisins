package com.openclassrooms.entrevoisins.ui.activities.neighbour_detail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.activities.neighbour_list.NeighbourFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailActivity extends AppCompatActivity {

    private static final String TAG = "NeighbourDetailActivity";

    @BindView(R.id.back_btn)
    ImageButton mBackBtn;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        ButterKnife.bind(this);

        setContent();

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NeighbourDetailActivity.this, NeighbourFragment.class);
                startActivity(intent);
            }
        });
    }

    public void setContent() {
        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("Neighbour");

        mNameTxt.setText(neighbour.getName());

        Glide.with(this)
                .load(Uri.parse(neighbour.getAvatarUrl()))
                .into(mImage);
        mNameSecondTxt.setText(neighbour.getName());
        mLocation.setText(neighbour.getAddress());
        mPhone.setText(neighbour.getPhoneNumber());
        mFacebook.setText("www.facebook.fr/"+ neighbour.getName());

    }
}