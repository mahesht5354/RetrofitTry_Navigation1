package com.example.retrofittry;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class RedFragment extends Fragment {

    public RedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle mBundle = new Bundle();
        mBundle = getArguments();
        mBundle.getString("LIST");
        return inflater.inflate(R.layout.fragment_red, container, false);
    }
}