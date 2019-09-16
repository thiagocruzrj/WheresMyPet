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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.study.wheresmypet.R;
import com.study.wheresmypet.config.configFirebase;
import com.study.wheresmypet.helper.UserFirebase;
import com.study.wheresmypet.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, email, password, phone;
    private Button btnRegister;
    private FirebaseAuth auth;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.editName);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPass);
        phone = findViewById(R.id.editPhone);
        btnRegister = findViewById(R.id.buttonRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textName = name.getText().toString();
                String textEmail = email.getText().toString();
                String textPass = password.getText().toString();
                String textPhone = phone.getText().toString();

                // Validating fields
                if( !textName.isEmpty()) {
                    if (!textEmail.isEmpty()) {
                        if (!textPass.isEmpty()) {
                            if (!textPhone.isEmpty()) {
                            user = new User();
                            user.setName(textName);
                            user.setEmail(textEmail);
                            user.setPassword(textPass);
                            user.setTelefone(textPhone);
                            userRegister();
                        } else {
                            Toast.makeText(RegisterActivity.this,
                                    "Preencha a senha!",
                                    Toast.LENGTH_LONG).show();
                        }
                        }else{
                            Toast.makeText(RegisterActivity.this,
                                    "Preencha o email",
                                    Toast.LENGTH_LONG).show();
                        }
                        } else {
                            Toast.makeText(RegisterActivity.this,
                                    "Preencha o nome!",
                                    Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });
    }

    public void userRegister(){
        auth = configFirebase.getFirebaseAuth();
        auth.createUserWithEmailAndPassword(
          user.getEmail(), user.getPassword()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String idUsuario = task.getResult().getUser().getUid();
                    user.setId(idUsuario);
                    user.salvar();

                    //Salvar dados no profile do Firebase
                    UserFirebase.updateUserName(user.getName());
                    finish();
                }else{
                    String exception = "";
                    try{
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        exception = "Digite uma senha mais forte!";
                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        exception = "Digite um E-mail válido!";
                    }catch (FirebaseAuthUserCollisionException e) {
                        exception = "Conta já cadastrada!";
                    }catch (Exception e){
                        exception = "Erro ao cadastrar usuário " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(RegisterActivity.this,
                            exception,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void openListActivity(){
        startActivity(new Intent(this, ListActivity.class));
    }
}
