package com.study.wheresmypet.config;

import com.google.firebase.auth.FirebaseAuth;

public class configFirebase {
    private static FirebaseAuth auth;

    public static FirebaseAuth getFirebaseAuth() {
        if(auth == null){
        auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
