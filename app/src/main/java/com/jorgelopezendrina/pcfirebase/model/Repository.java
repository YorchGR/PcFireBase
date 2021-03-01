package com.jorgelopezendrina.pcfirebase.model;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.jorgelopezendrina.pcfirebase.model.pojo.Informatico;
import com.jorgelopezendrina.pcfirebase.model.pojo.Ordenador;
import com.jorgelopezendrina.pcfirebase.viewmodel.ViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {

    private FirebaseFirestore db;
    private FirebaseUser currentUser;
    private FirebaseAuth firebaseAuth;
    private Context context;

    public Repository(Context context) {
        this.context = context;
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    /*-------------------------------------------USUARIOS-----------------------------------------*/

    public MutableLiveData<Boolean> loguearUsuario(String correo, String clave) {
        MutableLiveData<Boolean> resultado = new MutableLiveData<>();
        firebaseAuth
                .signInWithEmailAndPassword(correo, clave)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        currentUser = firebaseAuth.getCurrentUser();
                        resultado.setValue(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        currentUser = null;
                        resultado.setValue(false);
                    }
                });
        return resultado;
    }

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(FirebaseUser currentUser) {
        this.currentUser = currentUser;
    }

    /*---------------------------------------INFORMATICOS-----------------------------------------*/

    public MutableLiveData<List<Informatico>> listaDeInformaticos() {
        MutableLiveData<List<Informatico>> informaticos = new MutableLiveData<>();
        db.collection("user/" + getCurrentUser().getUid() + "/informatico").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    informaticos.setValue(task.getResult().toObjects(Informatico.class));
                }
            }
        });
        return informaticos;
    }

    public void insertaInformatico(Informatico informatico) {
        db.collection("user/" + getCurrentUser().getUid() + "/informatico").document(informatico.getDniInfor()).set(informatico)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Informático dado de alta con exito", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error dando de alta un informático", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void borrarInformatico(Informatico informatico) {
        db.collection("user/" + getCurrentUser().getUid() + "/informatico").document(informatico.getDniInfor()).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Informático borrado", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error borrando informático", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void actualizaInformatico(Informatico informatico) {
        db.collection("user/" + getCurrentUser().getUid() + "/informatico").document(informatico.getDniInfor())
                .update("apellInfor", informatico.getApellInfor(), "imgInfor",informatico.getImgInfor(), "nombreInfor",
                        informatico.getNombreInfor(), "tlfInfor", informatico.getTlfInfor())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Informático actualizado", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error durante la actualización.", Toast.LENGTH_LONG).show();
                    }
                });

    }

    /*-------------------------------------------ORDENADORES--------------------------------------*/

    public MutableLiveData<List<Ordenador>> listaDeOrdenadores() {
        MutableLiveData<List<Ordenador>> ordenadores = new MutableLiveData<>();
        db.collection("user/" + getCurrentUser().getUid() + "/ordenador").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ordenadores.setValue(task.getResult().toObjects(Ordenador.class));
                }
            }
        });
        return ordenadores;
    }

    public void insertaOrdenador(Ordenador ordenador) {
        db.collection("user/" + getCurrentUser().getUid() + "/ordenador").document(ordenador.getIdPc()).set(ordenador)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "ordenador dado de alta con exito", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error dando de alta un ordenador", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void borrarOrdenador(Ordenador ordenador) {
        db.collection("user/" + getCurrentUser().getUid() + "/ordenador").document(ordenador.getIdPc()).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Ordenador borrado", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error borrando Ordenador", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void actualizaOrdenador(Ordenador ordenador) {
        db.collection("user/" + getCurrentUser().getUid() + "/ordenador").document(ordenador.getIdPc()).update("marcaPc", ordenador.getMarcaPc(),
                "modeloPc", ordenador.getModeloPc(), "dniInfor", ordenador.getDniInfor())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Ordenador actualizado.", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Se ha producido un error durante la actualización", Toast.LENGTH_LONG).show();
                    }
                });

    }

}
