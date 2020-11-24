package com.example.subscription;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int CATEGORY_FOOTER = 1;
    private static final int CATEGORY_DATA = 2;
    private Context mContext;
    private List<Category> mCategories;
    private RecyclerView mRecyclerView;

    public CategoryAdapter() {
    }

    public CategoryAdapter(Context context, List<Category> categories) {
        this.mContext = context;
        this.mCategories = categories;
    }

    public void setCategories(List<Category> categories) {
        this.mCategories = categories;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CategoryDataViewHolder) {
            CategoryDataViewHolder categoryDataViewHolder = (CategoryDataViewHolder) holder;
            Category category = mCategories.get(position);

            categoryDataViewHolder.image.setImageResource(R.drawable.ic_launcher_background);
            categoryDataViewHolder.title.setText(category.getTitle());
            categoryDataViewHolder.subscribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Haha", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof CategoryFooterViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return mCategories.size() + 1;
    }

    static class CategoryDataViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private Button subscribe;

        public CategoryDataViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            subscribe = itemView.findViewById(R.id.btn_subscribe);
        }
    }

    static class CategoryFooterViewHolder extends RecyclerView.ViewHolder {

        public CategoryFooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mRecyclerView = null;
    }
}
