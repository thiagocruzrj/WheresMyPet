package com.study.wheresmypet.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.study.wheresmypet.R;
import com.study.wheresmypet.helper.UserFirebase;
import com.study.wheresmypet.model.Pet;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DatabaseReference petDbReference = UserFirebase.getFirebase()
                .child("meus_pets");

        petDbReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pet tempPet = dataSnapshot.getChildren().iterator().next().child(getIntent().getStringExtra("petID")).getValue(Pet.class);
                ((TextView) findViewById(R.id.animal_name)).setText(tempPet.getNome());
                ((TextView) findViewById(R.id.animal_type)).setText(tempPet.getTipo());
                ((TextView) findViewById(R.id.animal_phone)).setText(tempPet.getTelefone());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                error.toException();
            }
        });


    }
}
