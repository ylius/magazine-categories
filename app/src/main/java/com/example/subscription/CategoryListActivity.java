package com.example.subscription;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity extends AppCompatActivity {

    private RecyclerView mRvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Magazine");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        List<Category> categoryList = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            categoryList.add(new Category(null, "Flowers", false));
//        }
//        mRvContent = findViewById(R.id.rv_content);
//        mRvContent.setLayoutManager(new GridLayoutManager(this, 2));
//        mRvContent.setAdapter(new CategoryAdapter(this, categoryList));

        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new CategoryListFragment()).commit();
        }


    }
}