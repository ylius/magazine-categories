package com.example.subscription;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

public class CategoryListActivity extends AppCompatActivity {

    private static final int UPDATE_DATA = 0;

    private RecyclerView mRvContent;
    private CategoryAdapter mCategoryAdapter;
    private GridLayoutManager mGridLayoutManager;
    private List<Category> mCategories = new ArrayList<>();
    private int mLastLoadDataItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Subscribe");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mRvContent = findViewById(R.id.rv_content);
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mCategoryAdapter.isFooterView(position) ? mGridLayoutManager.getSpanCount() : 1;
            }
        });
        mRvContent.setLayoutManager(mGridLayoutManager);
        new LoadDataThread().start();
        mCategoryAdapter = new CategoryAdapter(this, mCategories);
        mRvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == SCROLL_STATE_IDLE &&
                        mLastLoadDataItemPosition >= mCategoryAdapter.getItemCount() - 5) {
                    new LoadDataThread().start();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                RecyclerView.LayoutManager layoutManager = mRvContent.getLayoutManager();
                if (layoutManager instanceof GridLayoutManager) {
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                    int firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();
                    int lastCompleteItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                    mLastLoadDataItemPosition = lastCompleteItem + 1;
                }
            }
        });
        mRvContent.setAdapter(mCategoryAdapter);
    }

    /**
     * 初始化数据
     *
     * @return
     */
    public List<Category> initData() {
        List<Category> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Category(null, "Flowers", false));
        }
        return list;
    }

    /**
     * 线程模拟加载数据
     */
    class LoadDataThread extends Thread {
        @Override
        public void run() {
            List<Category> list = initData();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = handler.obtainMessage();
            message.what = UPDATE_DATA;
            message.obj = list;
            handler.sendMessage(message);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == UPDATE_DATA) {
                List<Category> list = (List<Category>) msg.obj;
                mCategoryAdapter.addCategories(list);
                mCategoryAdapter.notifyDataSetChanged();
            }
        }
    };
}