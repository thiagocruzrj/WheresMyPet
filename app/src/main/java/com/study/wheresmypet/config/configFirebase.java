package com.study.wheresmypet.config;

import com.google.firebase.auth.FirebaseAuth;

public class configFirebase {
    private static FirebaseAuth auth;

    public static String getIdUsuario(){
        FirebaseAuth auth = getFirebaseAuth();
        return auth.getCurrentUser().getUid();
    }

    public static FirebaseAuth getFirebaseAuth() {
        if(auth == null){
        auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
