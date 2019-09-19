package com.study.wheresmypet.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.study.wheresmypet.Entities.Animal;
import com.study.wheresmypet.R;
import com.study.wheresmypet.adapter.AnimalAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    RecyclerView recyclerView;
    List<Animal> animals = new ArrayList<>();
    AnimalAdapter animalAdapter;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View piroca = inflater.inflate(R.layout.fragment_feed, container, false);

        animals.add(new Animal(
                "Renan",
                "123123123",
                "teu cu",
                "gernerfluid",
                "mobile",
                "sofrendo",
                "com.google.android.gms.tasks.zzu@14c2d46"));


        recyclerView = piroca.findViewById(R.id.feed_recicle_view);
        animalAdapter = new AnimalAdapter(animals);

        carregar();

        return piroca;
    }


    private void carregar() {
        try {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(animalAdapter);
        } catch (
                Exception e) {
//            Toast.makeText(this, "Erro : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
