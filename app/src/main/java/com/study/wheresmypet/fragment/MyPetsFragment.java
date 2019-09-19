package com.study.wheresmypet.fragment;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.study.wheresmypet.R;
import com.study.wheresmypet.activity.EditProfileActivity;
import com.study.wheresmypet.adapter.AdapterPetAnuncios;
import com.study.wheresmypet.helper.UserFirebase;
import com.study.wheresmypet.model.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPetsFragment extends Fragment {

    private RecyclerView recyclerPetView;
    private List<Pet> anunciosPet = new ArrayList<>();
    private AdapterPetAnuncios adapterPetAnuncios;
    private DatabaseReference anuncioUsuarioRef;
    private AlertDialog dialog;

    public MyPetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_pets, container, false);

        // Configurações iniciais
        anuncioUsuarioRef = UserFirebase.getFirebase()
                .child("anuncios")
                .child(UserFirebase.getIdUsuario());

        recyclerPetView = view.findViewById(R.id.my_pets_recicleview);

        recyclerPetView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerPetView.setHasFixedSize(true);
        adapterPetAnuncios = new AdapterPetAnuncios(anunciosPet, getContext());
        recyclerPetView.setAdapter(adapterPetAnuncios);

        recuperarAnuncios();

        return view;
    }

    private void recuperarAnuncios() {

        dialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage("Recuperando seus Pets")
                .setCancelable(false)
                .build();
        dialog.show();

        anuncioUsuarioRef.child("meus_pets").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                anunciosPet.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    anunciosPet.add(ds.getValue(Pet.class));
                }
                Collections.reverse(anunciosPet);
                adapterPetAnuncios.notifyDataSetChanged();

                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
