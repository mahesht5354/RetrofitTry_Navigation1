package com.example.retrofittry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofittry.GitHubRetrofitService;
import com.example.retrofittry.Model.Source;
import com.example.retrofittry.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;
    Retrofit retrofit;
    GitHubRetrofitService gfs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://inshortsapi.vercel.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gfs = retrofit.create(GitHubRetrofitService.class);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void getDeatils(View view) {

        progressBar.setVisibility(View.VISIBLE);
        Call<List<Source>> call = gfs.getGitHubData("sports");
        call.enqueue(new Callback<List<Source>>() {
            @Override
            public void onResponse(Call<List<Source>> call, Response<List<Source>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Mahesh", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Source>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
        /*call.enqueue(new Callback<List<Source>>() {
            @Override
            public void onResponse(Call<List<Source>> call, Response<List<Source>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                textView.setText("");
                //Source s = response.body().get(0);
                for(Source s: response.body())

                {
                    Toast.makeText(MainActivity.this, s.getCategory().toString(), Toast.LENGTH_LONG).show();

                }

                *//*if (s.getName() == null){
                    Toast.makeText(MainActivity.this, " name is null",Toast.LENGTH_LONG).show();
                }
                else {
                    textView.append(s.);
                    Toast.makeText(MainActivity.this, s.getName().toString(), Toast.LENGTH_LONG).show();
                }*//*
            }

            @Override
            public void onFailure(Call<List<Source>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Error",Toast.LENGTH_LONG).show();
            }
        });*/



    }
}