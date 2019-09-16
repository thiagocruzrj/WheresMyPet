package com.study.wheresmypet.helper;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.study.wheresmypet.config.configFirebase;

public class UserFirebase {

    private static DatabaseReference referenciaFirebase;
    private static StorageReference referenciaStorage;
    private static FirebaseAuth referenciaAutenticacao;

    public static DatabaseReference getFirebase(){
        if(referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }

    public static String getIdUsuario(){
        FirebaseAuth auth = getFirebaseAutenticacao();
        return auth.getCurrentUser().getUid();
    }

    public static FirebaseUser getCurrentUser(){
        FirebaseAuth user = configFirebase.getFirebaseAuth();
        return user.getCurrentUser();
    }

    // Retorna a referencia do FirebaseAuth
    public static FirebaseAuth getFirebaseAutenticacao(){
        if (referenciaAutenticacao == null){
            referenciaAutenticacao = FirebaseAuth.getInstance();
        }
        return referenciaAutenticacao;
    }

    public static StorageReference getFirebaseStorage(){
        if (referenciaStorage == null){
            referenciaStorage = FirebaseStorage.getInstance().getReference();
        }
        return referenciaStorage;
    }

    public static void updateUserName(String name){
        try {
            //Usuario Logado no app
            FirebaseUser userLogged = getCurrentUser();

            //Configurar objeto para alteracao do perfil
            UserProfileChangeRequest profile = new UserProfileChangeRequest
                    .Builder()
                    .setDisplayName(name)
                    .build();
            userLogged.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful()){
                        Log.d("Perfil", "Erro ao atualizar nome de perfil");
                    }
                }
            });
        }catch (Exception e){

        }
    }
}