package com.study.wheresmypet.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.study.wheresmypet.adapter.AnimalAdapter;
import com.study.wheresmypet.helper.UserFirebase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pet implements Serializable {
    private String idPet;
    private String nome;
    private String estado;
    private String tipo;
    private String raca;
    private String endereco;
    private String telefone;
    private List<String> fotos;

    ArrayList<Pet> tempPetsList = new ArrayList<>();


    public ArrayList<Pet> loadPets(final AnimalAdapter animalAdapter) {

        DatabaseReference petDbReference = UserFirebase.getFirebase()
                .child("meus_pets");

        petDbReference.addValueEventListener(new ValueEventListener() {
            Pet tempPet = new Pet();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.w("test", dataSnapshot.toString());
                Iterator<DataSnapshot> dataSnapshotIterator = dataSnapshot.getChildren().iterator();

                while (dataSnapshotIterator.hasNext()) {
                    DataSnapshot child = dataSnapshotIterator.next();
                    tempPet = child.getChildren().iterator().next().getValue(Pet.class);
                    tempPetsList.add(tempPet);
                }

                animalAdapter.setMyAnimals(tempPetsList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("test", "Failed to read value.", error.toException());
            }
        });

        return tempPetsList;
    }

    public Pet() {
        DatabaseReference petRef = UserFirebase.getFirebase()
                .child("meus_pets");
        setIdPet(petRef.push().getKey());
    }

    public void salvar() {
        String idUsuario = UserFirebase.getIdUsuario();
        DatabaseReference petRef = UserFirebase.getFirebase()
                .child("meus_pets");

        petRef.child(idUsuario)
                .child(getIdPet())
                .setValue(this);

        salvarAnuncioPetPublico();
    }


    private void salvarAnuncioPetPublico() {
        DatabaseReference petAnuncioRef = UserFirebase.getFirebase()
                .child("anuncios");

        petAnuncioRef.child(getEstado())
                .child(getTipo())
                .child(getIdPet())
                .setValue(this);
    }

    public void remover() {
        String idUsuario = UserFirebase.getIdUsuario();
        DatabaseReference anuncioRef = UserFirebase.getFirebase()
                .child("meus_anuncios")
                .child(idUsuario)
                .child(getIdPet());
    }

    private void removerAnuncioPublico() {
        DatabaseReference anuncioRef = UserFirebase.getFirebase()
                .child("anuncios")
                .child(getEstado())
                .child(getTipo())
                .child(getIdPet());

        anuncioRef.removeValue();
    }

    public String getIdPet() {
        return idPet;
    }

    public void setIdPet(String idPet) {
        this.idPet = idPet;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
}
