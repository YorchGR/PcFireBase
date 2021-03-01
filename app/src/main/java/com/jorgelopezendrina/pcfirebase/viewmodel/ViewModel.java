package com.jorgelopezendrina.pcfirebase.viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.jorgelopezendrina.pcfirebase.model.Repository;
import com.jorgelopezendrina.pcfirebase.model.pojo.Informatico;
import com.jorgelopezendrina.pcfirebase.model.pojo.Ordenador;
import com.jorgelopezendrina.pcfirebase.view.MainActivity;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private Informatico informatico;
    private Ordenador ordenador;
    private boolean rvelementos,crearInfor,creaOrdenador, userLogin;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    /*----------------------------------------LOGIN---------------------------------------------*/

    public MutableLiveData<Boolean> loguearUsuario(String correo, String clave) {
        return repository.loguearUsuario(correo, clave);
    }

    public FirebaseUser getCurrentUser() {
        return repository.getCurrentUser();
    }

    public void setCurrentUser(FirebaseUser currentUser) {
        repository.setCurrentUser(currentUser);
    }

    /*----------------------------------------INFORMATICO-----------------------------------------*/

    public Informatico getInformatico() {
        return informatico;
    }

    public void setInformatico(Informatico informatico) {
        this.informatico = informatico;
    }

    public MutableLiveData<List<Informatico>> listaDeInformaticos() {
        return repository.listaDeInformaticos();
    }

    public void insertaInformatico(Informatico informatico) {
        repository.insertaInformatico(informatico);
    }

    public void borrarInformatico(Informatico informatico) {
        repository.borrarInformatico(informatico);
    }

    public void actualizaInformatico(Informatico informatico) {
        repository.actualizaInformatico(informatico);
    }

    /*-----------------------------------------ORDENADOR------------------------------------------*/

    public Ordenador getOrdenador() {
        return ordenador;
    }

    public void setOrdenador(Ordenador ordenador) {
        this.ordenador = ordenador;
    }

    public MutableLiveData<List<Ordenador>> listaDeOrdenadores() {
        return repository.listaDeOrdenadores();
    }

    public void insertaOrdenador(Ordenador ordenador) {
        repository.insertaOrdenador(ordenador);
    }

    public void borrarOrdenador(Ordenador ordenador) {
        repository.borrarOrdenador(ordenador);
    }

    public void actualizaOrdenador(Ordenador ordenador) {
        repository.actualizaOrdenador(ordenador);
    }

    /*----------------------------------------ESTADOS---------------------------------------------*/

    public boolean isRvelementos() {
        return rvelementos;
    }

    public void setRvelementos(boolean rvelementos) {
        this.rvelementos = rvelementos;
    }

    public boolean isCrearInfor() {
        return crearInfor;
    }

    public void setCrearInfor(boolean crearInfor) {
        this.crearInfor = crearInfor;
    }

    public boolean isCreaOrdenador() {
        return creaOrdenador;
    }

    public void setCreaOrdenador(boolean creaOrdenador) {
        this.creaOrdenador = creaOrdenador;
    }

    public boolean isUserLogin() {
        return userLogin;
    }

    public void setUserLogin(boolean userLogin) {
        this.userLogin = userLogin;
    }
}
