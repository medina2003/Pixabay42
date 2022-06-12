package kg.geektech.pixabay42.network.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kg.geektech.pixabay42.databinding.ItemImageBinding;
import kg.geektech.pixabay42.network.model.model.Hit;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<Hit> list;

    public Adapter(ArrayList<Hit> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemImageBinding.
                inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemImageBinding binding;

        public ViewHolder(ItemImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void onBind(Hit obj) {
            Glide.with(binding.imageV).load(obj.getLargeImageURL()).into(binding.imageV);

        }
    }
}
