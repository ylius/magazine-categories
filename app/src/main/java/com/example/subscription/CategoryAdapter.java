package com.example.subscription;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int CATEGORY_DATA = 1;
    private static final int CATEGORY_FOOTER = 2;
    private Context mContext;
    private List<Category> mCategories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.mContext = context;
        this.mCategories = categories;
    }

    public void setCategories(List<Category> categories) {
        this.mCategories = categories;
    }

    public void addCategories(List<Category> categories) {
        this.mCategories.addAll(categories);
        Log.d("CategoryAdapter", "size: " + mCategories.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case CATEGORY_DATA:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
                viewHolder = new CategoryDataViewHolder(view);
                break;
            case CATEGORY_FOOTER:
                view = LayoutInflater.from(mContext).inflate(R.layout.category_list_footer, parent, false);
                viewHolder = new CategoryFooterViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() - 1 ? CATEGORY_FOOTER : CATEGORY_DATA;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CategoryDataViewHolder) {
            CategoryDataViewHolder categoryDataViewHolder = (CategoryDataViewHolder) holder;
            Category category = mCategories.get(position);

            categoryDataViewHolder.ivImageUrl.setImageResource(R.drawable.ic_launcher_background);
            categoryDataViewHolder.tvChannelName.setText(position + 1 + "");
            categoryDataViewHolder.btnSubscribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CustomToast.INSTANCE.showToast(mContext, "Haha");
                }
            });
        } else if (holder instanceof CategoryFooterViewHolder) {

        }
    }

    public boolean isFooterView(int position) {
        return position == getItemCount() - 1;
    }

    @Override
    public int getItemCount() {
        return mCategories.size() + 1;
    }

    static class CategoryDataViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivImageUrl = itemView.findViewById(R.id.iv_image_url);
        private final TextView tvChannelName = itemView.findViewById(R.id.tv_channel_name);
        private final Button btnSubscribe = itemView.findViewById(R.id.btn_subscribe);

        public CategoryDataViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class CategoryFooterViewHolder extends RecyclerView.ViewHolder {
        public CategoryFooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
