package com.example.subscription;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

public class CategoryListFragment extends Fragment {
    private static final int UPDATE_DATA = 3;

    private RecyclerView mRvContent;
    private CategoryAdapter mCategoryAdapter;
    private List<Category> mCategories = new ArrayList<>();
    private int mLastLoadDataItemPosition;

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
        initData();
        mCategoryAdapter = new CategoryAdapter(this.getActivity(), mCategories);
        mRvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == SCROLL_STATE_IDLE &&
                        mLastLoadDataItemPosition == mCategoryAdapter.getItemCount()) {
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
        return view;
    }

    /**
     * 初始化数据
     *
     * @return
     */
    public void initData() {
        for (int i = 0; i < 30; i++) {
            mCategories.add(new Category(null, "Flowers", false));
        }
        Log.d("CategoryList", mCategories.size() + "");
    }

    /**
     * 线程模拟加载数据
     */
    class LoadDataThread extends Thread {
        @Override
        public void run() {
            initData();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = handler.obtainMessage();
            message.what = UPDATE_DATA;
            message.obj = mCategories;
            handler.sendMessage(message);
        }
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_DATA:
                    mCategories = (List<Category>) msg.obj;
                    mCategoryAdapter.setCategories(mCategories);
                    mCategoryAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
}