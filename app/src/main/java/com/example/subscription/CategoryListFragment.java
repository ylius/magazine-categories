package com.example.subscription;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CategoryListFragment extends Fragment {
    private RecyclerView mRvContent;

    public CategoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        mRvContent = view.findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new GridLayoutManager(this.getActivity(), 2));
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            categoryList.add(new Category(null, "Flowers", false));
        }
        mRvContent.setAdapter(new CategoryAdapter(this.getActivity(), categoryList));
        return view;
    }
}