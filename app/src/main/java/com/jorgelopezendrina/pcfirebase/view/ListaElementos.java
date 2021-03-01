package com.jorgelopezendrina.pcfirebase.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jorgelopezendrina.pcfirebase.R;
import com.jorgelopezendrina.pcfirebase.model.pojo.Informatico;
import com.jorgelopezendrina.pcfirebase.model.pojo.Ordenador;
import com.jorgelopezendrina.pcfirebase.view.adapter.RVA_Informaticos;
import com.jorgelopezendrina.pcfirebase.view.adapter.RVA_Ordenadores;
import com.jorgelopezendrina.pcfirebase.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListaElementos extends Fragment {

    private ViewModel viewModel;
    private List<Informatico> listaInformaticos = new ArrayList<>();
    private List<Ordenador> listaOrdenadores = new ArrayList<>();

    public ListaElementos() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_lista_elementos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navC = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        viewModel.setCrearInfor(false);
        viewModel.setCreaOrdenador(false);
        if (viewModel.isRvelementos()) {
            verInformaticos(view, navC);
        } else {
           verOrdenadores(view, navC);
        }
        botonCrearElementos(view, navC);
    }

    private void verInformaticos(@NonNull View view, NavController navC) {
        viewModel.listaDeInformaticos().observe(getActivity(), new Observer<List<Informatico>>() {
            @Override
            public void onChanged(List<Informatico> informaticos) {
                listaInformaticos.clear();
                listaInformaticos.addAll(informaticos);
                abrirRecyclerInformaticos(view, listaInformaticos, navC);
            }
        });
    }

    private void verOrdenadores(@NonNull View view, NavController navC) {
        viewModel.listaDeOrdenadores().observe(getActivity(), new Observer<List<Ordenador>>() {
            @Override
            public void onChanged(List<Ordenador> ordenadores) {
                listaOrdenadores.clear();
                listaOrdenadores.addAll(ordenadores);
                abrirRecyclerOrdenadores(view, listaOrdenadores, navC);
            }
        });
    }


    private RVA_Ordenadores abrirRecyclerOrdenadores(View view, List<Ordenador> listaOrdenadores, NavController navC) {
        RecyclerView rvElementos = view.findViewById(R.id.rvElementos);
        RVA_Ordenadores rvAdapterPc = new RVA_Ordenadores(listaOrdenadores,navC,viewModel,getActivity());
        rvElementos.setAdapter(rvAdapterPc);
        rvElementos.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rvAdapterPc;
    }

    private RVA_Informaticos abrirRecyclerInformaticos(View view, List<Informatico> listaInformaticos, NavController navC) {
        RecyclerView rvElementos = view.findViewById(R.id.rvElementos);
        RVA_Informaticos rvAdapterInfor = new RVA_Informaticos(listaInformaticos, navC, viewModel, getActivity());
        rvElementos.setAdapter(rvAdapterInfor);
        rvElementos.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rvAdapterInfor;
    }

    private void botonCrearElementos(@NonNull View view, NavController navC) {
        Button btCrearElementos = view.findViewById(R.id.btAiadir);
        btCrearElementos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.isRvelementos()) {
                    viewModel.setCrearInfor(true);
                    navC.navigate(R.id.ac_listaElementos_to_verInformatico);
                }else{
                    viewModel.setCreaOrdenador(true);
                    navC.navigate(R.id.ac_listaElementos_to_verOrdenador);
                }
            }
        });
    }

}