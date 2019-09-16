package com.study.wheresmypet.model;

import android.content.res.Configuration;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.study.wheresmypet.config.configFirebase;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String telefone;
    private String caminhoFoto;

    public User() {
    }

    public void salvar(){
        final FirebaseDatabase firebaseRef = FirebaseDatabase.getInstance();
        DatabaseReference usuariosRef = firebaseRef.getReference("usuarios").child(getId());
        usuariosRef.setValue( this );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
