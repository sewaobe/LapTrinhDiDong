package com.example.week8_recycleview_search.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.week8_recycleview_search.R;
import com.example.week8_recycleview_search.models.IconModel;

import java.util.List;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconHolder> {
    private Context context;
    private List<IconModel> arrayList;

    public IconAdapter(List<IconModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public IconHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_icon_promotion, parent, false);
        return new IconHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconHolder holder, int position) {
        IconModel iconModel = arrayList.get(position);
        Glide.with(context).load(iconModel.getImgId()).into(holder.imageView);
        holder.tvIcon.setText(iconModel.getDesc());
    }

    @Override
    public int getItemCount() {
        return arrayList!=null? arrayList.size():0;
    }
    public  void setListenerList(List<IconModel> iconModelList) {
        this.arrayList = iconModelList;
        notifyDataSetChanged();
    }

    public class IconHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvIcon;

        public IconHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivImgIcon);
            tvIcon = itemView.findViewById(R.id.tvIcon);
        }
    }
}
