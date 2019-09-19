package com.study.wheresmypet.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.study.wheresmypet.Entities.Animal;
import com.study.wheresmypet.R;
import com.study.wheresmypet.adapter.AnimalAdapter;
import com.study.wheresmypet.model.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    RecyclerView recyclerView;
    List<Animal> animals;
    AnimalAdapter animalAdapter;
    Pet petModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        animals = new ArrayList<>();
        petModel = new Pet();
        recyclerView = view.findViewById(R.id.feed_recicle_view);
        animalAdapter = new AnimalAdapter(new ArrayList<Pet>());


        carregar();

        return view;
    }


    private void carregar() {
        try {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            petModel.loadPets(animalAdapter);
            recyclerView.setAdapter(animalAdapter);
        } catch (
                Exception e) {
            Log.w("test", e.getMessage());

            Toast.makeText(getActivity(), "Erro : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
