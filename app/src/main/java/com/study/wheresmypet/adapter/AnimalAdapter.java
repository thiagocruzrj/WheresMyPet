package com.study.wheresmypet.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.wheresmypet.R;
import com.study.wheresmypet.ViewHolders.AnimalViewHolder;
import com.study.wheresmypet.activity.DetailActivity;
import com.study.wheresmypet.model.Pet;

import java.util.List;
import java.util.Locale;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalViewHolder> {
    private List<Pet> animals;


    public void setMyAnimals(List<Pet> myAnimals) {
        this.animals = myAnimals;
        notifyDataSetChanged();
    }

    public AnimalAdapter(List<Pet> pets) {
        this.animals = pets;
    }


    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnimalViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animal_card_viewholder, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull final AnimalViewHolder holder, final int position) {
        holder.Name.setText(String.format(Locale.getDefault(),
                animals.get(position).getNome()));
        holder.State.setText(String.format(Locale.getDefault(),
                animals.get(position).getEstado()));
        holder.AnimalImg.setImageURI((Uri.parse(animals.get(position).getFotos().get(0))));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextAct = new Intent(holder.itemView.getContext(), DetailActivity.class);
                nextAct.putExtra("petID", animals.get(position).getIdPet());
                holder.itemView.getContext().startActivity(nextAct);
            }
        });

    }

    @Override
    public int getItemCount() {
        return animals != null ? animals.size() : 0;
    }
}
