package com.vista.textscanner.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.vista.textscanner.R;
import com.vista.textscanner.databinding.OcrlistLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class list_adapter extends RecyclerView.Adapter<list_adapter.list_Holder> {
    public List<Item> data;
    private Context context;
//    private OnItemClickCallback onItemClickCallback;
    public List<Item> selecteditem;


    public list_adapter(List<Item> list, Context context, List<Item> selecteditem) {
        this.data = list;
        this.context = context;
        this.selecteditem = selecteditem;
    }

    @NonNull
    @Override
    public list_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new list_Holder(OcrlistLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull list_Holder holder, int position) {
        Item item = data.get(position);
        switch(item.getType()){
            case "Tidak Dikategori":
                holder.binding.cardBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.Kategori1));
                break;
            case "Pekerjaan":
                holder.binding.cardBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.Kategori2));
                break;
            case "Rumah":
                holder.binding.cardBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.Kategori3));
                break;
            case "Sekolah":
                holder.binding.cardBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.Kategori4));
                break;
            case "oke":
                holder.binding.cardBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                break;
        }
        holder.binding.PreviewText.setText(item.getContent());
        holder.binding.date.setText(item.getDatetime());
        holder.binding.type.setText(item.getType());
        if(selecteditem.contains(data.get(position))){
            holder.binding.btnDetail.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        } else {
            holder.binding.btnDetail.setBackgroundColor(ContextCompat.getColor(context, R.color.normal_item));
        }
//        holder.binding.btnDetail.setOnClickListener(v -> {
//            onItemClickCallback.onItemClicked(v,position, data);
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class list_Holder extends RecyclerView.ViewHolder{
        OcrlistLayoutBinding binding;

        public list_Holder(@NonNull OcrlistLayoutBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

//    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback;
//    }
//
//    public interface OnItemClickCallback {
//        void onItemClicked(View view, int position, List<Item> data);
//    }


}
