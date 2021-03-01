package com.jorgelopezendrina.pcfirebase.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jorgelopezendrina.pcfirebase.R;
import com.jorgelopezendrina.pcfirebase.viewmodel.ViewModel;


public class Inicio extends Fragment {

    private ViewModel viewModel;

    public Inicio() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navC = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        viewModel.setCrearInfor(false);
        viewModel.setCreaOrdenador(false);
        botonInformaticos(view, navC);
        botonOrdenadores(view, navC);
    }

    private void botonOrdenadores(@NonNull View view, NavController navC) {
        Button btOrdenadores = view.findViewById(R.id.btPcs);
        btOrdenadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setRvelementos(false);
                navC.navigate(R.id.ac_inicio_to_listaElementos);
            }
        });
    }

    private void botonInformaticos(@NonNull View view, NavController navC) {
        Button btinformaticos = view.findViewById(R.id.btInfor);
        btinformaticos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setRvelementos(true);
                navC.navigate(R.id.ac_inicio_to_listaElementos);
            }
        });
    }
}