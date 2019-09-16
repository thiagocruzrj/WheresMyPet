package com.study.wheresmypet.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.study.wheresmypet.R;
import com.study.wheresmypet.config.configFirebase;
import com.study.wheresmypet.fragment.FeedFragment;
import com.study.wheresmypet.fragment.MyPetsFragment;
import com.study.wheresmypet.fragment.NewPetFragment;
import com.study.wheresmypet.fragment.ProfileFragment;

public class ListActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private FirebaseAuth autenticacao;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentTransaction.replace(R.id.viewPage, new FeedFragment()).commit();
                    return true;
                case R.id.navigation_new:
                    fragmentTransaction.replace(R.id.viewPage, new NewPetFragment()).commit();
                    return true;
                case R.id.navigation_pets:
                    fragmentTransaction.replace(R.id.viewPage, new MyPetsFragment()).commit();
                    return true;
                case R.id.navigation_perfil:
                    fragmentTransaction.replace(R.id.viewPage, new ProfileFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        autenticacao = configFirebase.getFirebaseAuth();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPage, new FeedFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_nav_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }



    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sair:
                deslogarUsuario();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deslogarUsuario() {
        try {
            autenticacao.signOut();
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/
}
