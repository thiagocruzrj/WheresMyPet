package com.study.wheresmypet.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.wheresmypet.R;

public class AnimalViewHolder extends RecyclerView.ViewHolder {
    public TextView Name;
    public TextView State;
    public ImageView AnimalImg;

    public AnimalViewHolder(@NonNull View itemView) {
        super(itemView);
        Name = itemView.findViewById(R.id.animal_name);
        State = itemView.findViewById(R.id.animal_state);
        AnimalImg = itemView.findViewById(R.id.animal_image);
    }
}
