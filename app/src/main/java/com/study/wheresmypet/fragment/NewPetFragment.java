package com.study.wheresmypet.fragment;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.santalu.maskedittext.MaskEditText;
import com.study.wheresmypet.R;
import com.study.wheresmypet.helper.Permissoes;
import com.study.wheresmypet.helper.UserFirebase;
import com.study.wheresmypet.model.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPetFragment extends Fragment implements View.OnClickListener{

    private ImageView imageViewPet, imageViewPet2;
    private TextView textViewAddPhoto;
    private EditText editTextPetType, editTextPetName, editTextPetBreed, editTextAddress;
    private MaskEditText editTextPhone;
    private Button buttonNewPet;
    private Spinner spinnerState, spinnerTipo;
    private static final int GALERY_SELECT = 200;
    private Pet pet;
    private StorageReference storage;


    private String[] permissoes = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private List<String> listaFotosRecuperadas = new ArrayList<>();

    public NewPetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_pet, container, false);

        // Configuracoes Iniciais
        storage = UserFirebase.getFirebaseStorage();

        // Validando permissões
        requestPermissions(permissoes, 1);

        imageViewPet = view.findViewById(R.id.imageViewPet);
        imageViewPet2 = view.findViewById(R.id.imageViewPet2);
        textViewAddPhoto = view.findViewById(R.id.textViewAddPhoto);
        editTextPetName = view.findViewById(R.id.editTextPetName);
        editTextPetBreed = view.findViewById(R.id.editTextPetBreed);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        buttonNewPet = view.findViewById(R.id.buttonNewPet);
        spinnerState = view.findViewById(R.id.spinnerState);
        spinnerTipo = view.findViewById(R.id.spinnerTipo);


        imageViewPet.setOnClickListener(this);
        imageViewPet2.setOnClickListener(this);
        carregarDadosSpinner();
        buttonNewPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDadosAnuncio();
            }
        });
        return view;
    }

    public void salvarAnuncioPet(){
        // Salvando imagens no storage
        for(int i=0; i < listaFotosRecuperadas.size(); i++){
            String urlImagem = listaFotosRecuperadas.get(i);
            int tamanhoDaLista = listaFotosRecuperadas.size();
            salvarFotoStorage(urlImagem, tamanhoDaLista, i);
        }
    }

    private void salvarFotoStorage(String urlString, int totalFotos, int contador) {
        // Criando nó no storage
        StorageReference imagemPet = storage.child("imagens")
                .child("pets")
                .child(pet.getIdPet())
                .child("imagem"+contador);

        // Upload da foto
        UploadTask uploadTask = imagemPet.putFile(Uri.parse(urlString));
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> url = taskSnapshot.getStorage().getDownloadUrl();
                String urlConvertida = url.toString();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                exibirMensagemErro("Falha no upload da foto");
                Log.i("INFO", "Falha ao fazer upload: " + e.getMessage());
            }
        });
    }

    public void validarDadosAnuncio(){
        pet = configurarAnuncioPet();

        if(listaFotosRecuperadas.size() != 0){
            if(!pet.getEstado().isEmpty()){
                if(!pet.getTipo().isEmpty()){
                    if(!pet.getEndereco().isEmpty()){
                        if(!pet.getTelefone().isEmpty()){
                            salvarAnuncioPet();
                        }else {
                            exibirMensagemErro("Preencha o campo telefone");
                        }
                    }else {
                        exibirMensagemErro("Preencha o campo endereço");
                    }
                }else {
                    exibirMensagemErro("Preencha o campo tipo");
                }
            }else {
                exibirMensagemErro("Preencha o campo estado");
            }
        }else{
            exibirMensagemErro("Selecione ao menos uma foto");
        }
    }

    private Pet configurarAnuncioPet(){
        String estado = spinnerState.getSelectedItem().toString();
        String tipo = spinnerTipo.getSelectedItem().toString();
        String endereco = editTextAddress.getText().toString();
        String telefone = editTextPhone.getText().toString();
        String nome = editTextPetName.getText().toString();
        String raca = editTextPetBreed.getText().toString();

        Pet pet = new Pet();
        pet.setEndereco(endereco);
        pet.setNome(nome);
        pet.setRaca(raca);
        pet.setEstado(estado);
        pet.setTipo(tipo);
        pet.setTelefone(telefone);

        return pet;
    }
    private void exibirMensagemErro(String mensagem){
        Toast.makeText(getContext(),mensagem,Toast.LENGTH_SHORT).show();
    }

    private void carregarDadosSpinner() {
        String[] estados = getResources().getStringArray(R.array.estados);
        String[] tipos = getResources().getStringArray(R.array.tipos);

        ArrayAdapter<String> adapterEstado = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, estados);
        adapterEstado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(adapterEstado);

        ArrayAdapter<String> adapterTipos = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, tipos);
        adapterTipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapterTipos);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int permissaoResultado: grantResults){
            if(permissaoResultado == PackageManager.PERMISSION_DENIED){
                aletaValidacaoPermissao();
            }
        }
    }

    private void aletaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Permissões negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageViewPet:
                escolherImagem(1);
                break;
            case R.id.imageViewPet2:
                escolherImagem(2);
                break;
        }
    }

    public void escolherImagem(int requestCode){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {

            // Recuperar image
            Uri imageSelecionada = data.getData();
            String caminhoImagem = imageSelecionada.toString();

            //Configurar imagem no ImageView
            if (requestCode == 1) {
                imageViewPet.setImageURI(imageSelecionada);
            } else if (requestCode == 2) {
                imageViewPet2.setImageURI(imageSelecionada);
            }
            listaFotosRecuperadas.add(caminhoImagem);
        } else{
            Log.d("imagem2", "tes2");
        }
    }
}