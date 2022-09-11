package com.example.retrofittry;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofittry.Model.APIClient;
import com.example.retrofittry.Model.Source;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RedFragment extends Fragment {

    GitHubRetrofitService apiInterface;
    Context context;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    List<News> list;

    public RedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_red, container, false);
        // 1. get a reference to recyclerView
         recyclerView = rootView.findViewById(R.id.recyclerView_red);
        newsAdapter = new NewsAdapter(getContext(), list );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsAdapter);

        APICall();



        return rootView.getRootView();
    }

    private void APICall() {
        apiInterface = APIClient.getClient().create(GitHubRetrofitService.class);
        //gfs = retrofit.create(GitHubRetrofitService.class);
        /*LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);*/

        //recyclerView.setAdapter(newsAdapter);
        list = new ArrayList<>();
        Call<Source> call = apiInterface.getGitHubData("entertainment");
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


                //newsAdapter = new NewsAdapter(getContext(), list);
                newsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Source> call, Throwable t) {

                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
            }
        });
        //newsAdapter.notifyDataSetChanged();
    }
}