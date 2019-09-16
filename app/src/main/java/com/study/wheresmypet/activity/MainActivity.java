package com.study.wheresmypet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.study.wheresmypet.R;
import com.study.wheresmypet.activity.LoginActivity;
import com.study.wheresmypet.activity.RegisterActivity;
import com.study.wheresmypet.config.configFirebase;

public class MainActivity extends IntroActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.welcome_1)
                .build());

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.welcome_2)
                .build());

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.welcome_3)
                .canGoForward(false)
                .build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        verifyLoggedUser();
    }

    public void btContinue(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void verifyLoggedUser(){
        auth = configFirebase.getFirebaseAuth();
        auth.signOut();
        if(auth.getCurrentUser() != null){
            openListActivity();
        }
    }

    public void openListActivity(){
        startActivity(new Intent(this, ListActivity.class));
    }
}
