package com.example.retrofittry;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofittry.Model.APIClient;
import com.example.retrofittry.Model.Source;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YellowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YellowFragment extends Fragment {

    GitHubRetrofitService apiInterface;
    Context context;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    List<News> list;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_yellow, container, false);
        // 1. get a reference to recyclerView
        recyclerView = rootView.findViewById(R.id.recyclerView_yellow);
        progressBar = rootView.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

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

        Call<Source> call = apiInterface.getGitHubData("politics");
        call.enqueue(new Callback<Source>() {
            @Override
            public void onResponse(Call<Source> call, Response<Source> response) {

                progressBar.setVisibility(View.GONE);
                for (int i=0; i<response.body().getData().size();i++)
                {
                    String title = response.body().getData().get(i).getTitle();
                    String content = response.body().getData().get(i).getContent();
                    String imageUrl = response.body().getData().get(i).getImageUrl();

                    //Toast.makeText(MainActivity.this, title+"\n"+content+"\n"+imageUrl, Toast.LENGTH_LONG).show();
                    list.add(new News(title, content, imageUrl));
                }


                newsAdapter = new NewsAdapter(getContext(), list );
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(newsAdapter);

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