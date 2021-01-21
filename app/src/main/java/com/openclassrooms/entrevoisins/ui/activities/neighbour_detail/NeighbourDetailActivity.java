package com.openclassrooms.entrevoisins.ui.activities.neighbour_detail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;

public class NeighbourDetailActivity extends AppCompatActivity {

    private static final String TAG = "NeighbourDetailActivity";

    public ImageView mImage;
    public TextView mName;
    public TextView mNameSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        mName = findViewById(R.id.username_txt);
        mImage = findViewById(R.id.user_img);
        mNameSecond = findViewById(R.id.second_username_txt);

        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("Neighbour");

        Log.d(TAG, "Image: " + neighbour.getName());
        mName.setText(neighbour.getName());

        Glide.with(this)
                .load(Uri.parse(neighbour.getAvatarUrl()))
                .into(mImage);

        mNameSecond.setText(neighbour.getName());



    }
}