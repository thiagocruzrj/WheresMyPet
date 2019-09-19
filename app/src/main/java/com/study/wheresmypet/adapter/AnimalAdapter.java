package com.study.wheresmypet.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.wheresmypet.Entities.Animal;
import com.study.wheresmypet.R;
import com.study.wheresmypet.ViewHolders.AnimalViewHolder;

import java.util.List;
import java.util.Locale;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalViewHolder> {
    private List<Animal>  animals;



    public AnimalAdapter(List<Animal> myUsers) {
        this.animals = myUsers;
    }


    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnimalViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animal_card_viewholder, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        holder.Name.setText(String.format(Locale.getDefault(),
                animals.get(position).getName()));
        holder.State.setText(String.format(Locale.getDefault(),
                animals.get(position).getState()));
    }

    @Override
    public int getItemCount() { return animals != null ? animals.size() : 0;    }
}
