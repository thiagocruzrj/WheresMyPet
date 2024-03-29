package com.study.wheresmypet.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private ProgressBar progressBar;
    private CircleImageView imageProfile;
    public GridView gridViewPerfil;
    private TextView publicacoes;
    private Button ButtonEditarPerfil;



    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        imageProfile = view.findViewById(R.id.imageProfile);
        progressBar = view.findViewById(R.id.progressBarProfile);
        publicacoes = view.findViewById(R.id.publicacoes);
        ButtonEditarPerfil = view.findViewById(R.id.ButtonEditarPerfil);

        // Abrindo edição do perfil
        ButtonEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(i);
            }
        });

        return view;
    }


}
