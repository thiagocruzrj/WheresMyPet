package com.study.wheresmypet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;
import com.study.wheresmypet.R;
import com.study.wheresmypet.helper.UserFirebase;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    private CircleImageView imageUpdatePhoto;
    private TextView textUpdatePhoto;
    private TextInputEditText editUser, editTelephone;
    private Button buttonUpdateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        // Inicializando componentes
        initializingComponents();

        // Recuperando dados do usuario
        FirebaseUser userProfile = UserFirebase.getCurrentUser();
        editUser.setText(userProfile.getDisplayName());
        editTelephone.setText(userProfile.getPhoneNumber());
    }

    public void initializingComponents(){
        imageUpdatePhoto = findViewById(R.id.imageUpdatePhoto);
        textUpdatePhoto = findViewById(R.id.textUpdatePhoto);
        editUser = findViewById(R.id.editUser);
        editTelephone = findViewById(R.id.editTelephone);
        buttonUpdateProfile = findViewById(R.id.buttonUpdateProfile);
    }
}
