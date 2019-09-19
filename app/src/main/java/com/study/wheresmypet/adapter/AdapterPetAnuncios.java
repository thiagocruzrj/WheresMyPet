package com.study.wheresmypet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.study.wheresmypet.R;
import com.study.wheresmypet.model.Pet;

import java.util.List;

public class AdapterPetAnuncios extends RecyclerView.Adapter<AdapterPetAnuncios.MyViewHolder> {

    private List<Pet> pets;
    private Context context;

    public AdapterPetAnuncios(List<Pet> pet, Context context) {
        this.pets = pet;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_anuncio, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pet pet = pets.get(position);
        holder.nome.setText(pet.getNome());
        holder.endereco.setText(pet.getEndereco());

        List<String> urlFotos = pet.getFotos();
        String urlCapa = urlFotos.get(0);

        Picasso.get().load(urlCapa).into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        TextView endereco;
        ImageView foto;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            nome = itemView.findViewById(R.id.textNome);
            endereco = itemView.findViewById(R.id.textEndereco);
            foto = itemView.findViewById(R.id.imagePetAnuncio);

        }
    }
}