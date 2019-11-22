package com.example.engineering_ai;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends PagedListAdapter<Hit, ItemAdapter.ItemViewHolder> {

    private static DiffUtil.ItemCallback<Hit> DIFF_CALLBACK =
        new DiffUtil.ItemCallback<Hit>() {
            @Override
            public boolean areItemsTheSame(Hit oldItem, Hit newItem) {
                return oldItem.getStoryId() == newItem.getStoryId();
            }

            @SuppressLint("DiffUtilEquals")
            @Override
            public boolean areContentsTheSame(Hit oldItem, Hit newItem) {
                return oldItem.equals(newItem);
            }
        };

    private Context mCtx;

    ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_recycler_movie, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Hit item = getItem(position);

        if (item != null) {
            holder.title.setText(item.getTitle());
            holder.createAt.setText(item.getCreatedAt());
            holder.aSwitch.setChecked(item.getOn());
        } else {
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        TextView createAt;

        Switch aSwitch;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            createAt = itemView.findViewById(R.id.tvCreatedAt);
            aSwitch = itemView.findViewById(R.id.switchMain);
        }
    }
}