package com.example.aroundafrica.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aroundafrica.R;
import com.example.aroundafrica.data.Photo;
import com.example.aroundafrica.data.PhotoInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    //UI elements
    ProgressBar progressBar;
    RecyclerView recyclerView;

    private List<Photo> mList;
    private PhotoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.pb_loading_indicator);
        recyclerView = findViewById(R.id.recycler_view);

        mList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new PhotoAdapter(mList, this);
        recyclerView.setAdapter(mAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();

        retrofit.create(PhotoInterface.class).getPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                mList = response.body();
                mAdapter.setPhotos(mList);
                Log.d(LOG_TAG, mList.get(1).getTitle());
                Log.d(LOG_TAG, "Response");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d(LOG_TAG, t.getMessage());
                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
