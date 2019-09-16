package com.study.wheresmypet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.study.wheresmypet.R;
import com.study.wheresmypet.config.configFirebase;
import com.study.wheresmypet.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btnSingIn, btnRegister;
    private User user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPass);
        btnSingIn = findViewById(R.id.buttonSingIn);
        btnRegister = findViewById(R.id.buttonRegister);

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textEmail = email.getText().toString();
                String textPass = password.getText().toString();

                    if (!textEmail.isEmpty()) {
                        if (!textPass.isEmpty()) {

                            user = new User();
                            user.setEmail(textEmail);
                            user.setPassword(textPass);
                            loginValidation();

                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Preencha a senha!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this,
                                "Preencha o email",
                                Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
    public void loginValidation(){
        auth = configFirebase.getFirebaseAuth();
        auth.signInWithEmailAndPassword(
                user.getEmail(),
                user.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    openListActivity();
                }else{
                    String exception = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        exception = "E-mail e/ou senha incorretos!";
                    }catch (FirebaseAuthInvalidUserException e) {
                        exception = "Usuário não cadastrado!";
                    }

                    catch (Exception e){
                        exception = "Erro ao fazer login " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this,
                            exception,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void openListActivity(){
        startActivity(new Intent(this, ListActivity.class));
        finish();
    }

    public void btRegister(View view){
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
