package com.example.retrofittry;

import com.example.retrofittry.Model.Source;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubRetrofitService {

    @GET("news")
    Call<List<Source>> getGitHubData(@Query("category") String category);


}
