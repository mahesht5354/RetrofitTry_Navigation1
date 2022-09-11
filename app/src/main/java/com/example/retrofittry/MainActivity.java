package com.example.retrofittry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofittry.GitHubRetrofitService;
import com.example.retrofittry.Model.Datum;
import com.example.retrofittry.Model.Source;
import com.example.retrofittry.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TabLayout tl;
    ViewPager vp;
    RecyclerView recyclerView;
    Button b;
    Retrofit retrofit;
    GitHubRetrofitService gfs;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recyclerView = findViewById(R.id.recyclerView);
        tl = findViewById(R.id.tabLayout);
        vp = findViewById(R.id.viewpager);
        Vpadapter vpadapter = new Vpadapter(getSupportFragmentManager());
        vp.setAdapter(vpadapter);
        tl.setupWithViewPager(vp);
        b = findViewById(R.id.button);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://inshortsapi.vercel.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gfs = retrofit.create(GitHubRetrofitService.class);
        b.setVisibility(View.GONE);

        List<News> list = new ArrayList<>();
        Call<Source> call = gfs.getGitHubData("entertainment");
        call.enqueue(new Callback<Source>() {
            @Override
            public void onResponse(Call<Source> call, Response<Source> response) {


                for (int i=0; i<response.body().getData().size();i++)
                {
                    String title = response.body().getData().get(i).getTitle();
                    String content = response.body().getData().get(i).getContent();
                    String imageUrl = response.body().getData().get(i).getImageUrl();

                    //Toast.makeText(MainActivity.this, title+"\n"+content+"\n"+imageUrl, Toast.LENGTH_LONG).show();
                    list.add(new News(title, content, imageUrl));
                }
                String jsonList = gson.toJson(list);


                RedFragment fragment = new RedFragment(); // replace your custom fragment class
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                bundle.putString("LIST",jsonList); // use as per your need
                fragment.setArguments(bundle);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                /*NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this, list);
                recyclerView.setAdapter(newsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));*/


            }

            @Override
            public void onFailure(Call<Source> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void getDeatils(View view) {

    }

    class Vpadapter extends FragmentStatePagerAdapter
    {

        public Vpadapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return new RedFragment();
                case 1: return new GreenFragment();
                case 2: return new YellowFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return "Sports";
                case 1: return "Entertainment";
                case 2: return "Politics";
            }
            return "null";
        }


    }
}