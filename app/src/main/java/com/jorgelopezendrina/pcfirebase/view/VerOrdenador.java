package com.jorgelopezendrina.pcfirebase.view;

import android.content.DialogInterface;
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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jorgelopezendrina.pcfirebase.R;
import com.jorgelopezendrina.pcfirebase.model.pojo.Informatico;
import com.jorgelopezendrina.pcfirebase.model.pojo.Ordenador;
import com.jorgelopezendrina.pcfirebase.view.adapter.RVA_Info;
import com.jorgelopezendrina.pcfirebase.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class VerOrdenador extends Fragment {

    private ViewModel viewModel;
    private List<Informatico> listaMontadores = new ArrayList<>();

    public VerOrdenador() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_ver_ordenador, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navC = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        RVA_Info rvIdInfo = abrirRecycler(view,navC);
        escuchaInformaticos (rvIdInfo);
        escondeBotones(view);
        if (viewModel.isCreaOrdenador()) {
            botonCrear(view, navC);
        } else {
            cargarOrdenador(view);
            botonBorrar(view, navC);
            botonActualizar(view, navC);
        }
    }

    private void cargarOrdenador(@NonNull View view) {
        EditText etIdInforCreador = view.findViewById(R.id.etIdInforCreador);
        EditText etMarcaPc = view.findViewById(R.id.etMarcaPc);
        EditText etModeloPc = view.findViewById(R.id.etModeloPc);
        EditText etIdIdPc = view.findViewById(R.id.etIdPc);
        etIdIdPc.setText(viewModel.getOrdenador().getIdPc());
        etIdIdPc.setEnabled(false);
        etIdInforCreador.setText(viewModel.getOrdenador().getDniInfor());
        etMarcaPc.setText(viewModel.getOrdenador().getMarcaPc());
        etModeloPc.setText(viewModel.getOrdenador().getModeloPc());
    }

    private void escuchaInformaticos(RVA_Info rvIdInfo) {
        viewModel.listaDeInformaticos().observe(getActivity(), new Observer<List<Informatico>>() {
            @Override
            public void onChanged(List<Informatico> informaticos) {
                listaMontadores.clear();
                listaMontadores.addAll(informaticos);
                rvIdInfo.notifyDataSetChanged();
            }
        });
    }

    private boolean recogeDatosOrdenador(@NonNull View view, boolean actualizaOcrea) {
        EditText etIdInforCreador = view.findViewById(R.id.etIdInforCreador);
        EditText etIdIdPc = view.findViewById(R.id.etIdPc);
        EditText etMarcaPc = view.findViewById(R.id.etMarcaPc);
        EditText etModeloPc = view.findViewById(R.id.etModeloPc);
        Ordenador ordenador;
        if (actualizaOcrea) {
            ordenador = viewModel.getOrdenador();
            ordenador.setDniInfor(etIdInforCreador.getText().toString());
        } else {
            ordenador = new Ordenador();
        }
        ordenador.setIdPc(etIdIdPc.getText().toString());
        ordenador.setDniInfor(etIdInforCreador.getText().toString());
        ordenador.setMarcaPc(etMarcaPc.getText().toString());
        ordenador.setModeloPc(etModeloPc.getText().toString());
        if (etIdInforCreador.getText().toString().equals("") || etIdInforCreador.getText().toString().isEmpty() || etModeloPc.getText().toString().equals("") || etModeloPc.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Los campos de ID Informático y modelo, son obligatorios", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (actualizaOcrea) {
                viewModel.actualizaOrdenador(ordenador);
            } else {
                viewModel.insertaOrdenador(ordenador);
            }
        }
        return true;
    }


    private void escondeBotones(@NonNull View view) {
        EditText etIdInforCreador = view.findViewById(R.id.etIdInforCreador);
        etIdInforCreador.setEnabled(false);
        if (viewModel.isCreaOrdenador()) {
            Button btBorrarPc = view.findViewById(R.id.btborraPc);
            btBorrarPc.setVisibility(View.INVISIBLE);
            Button btActualizarPc = view.findViewById(R.id.btActualizarPc);
            btActualizarPc.setVisibility(View.INVISIBLE);
        } else {
            Button btCrearPc = view.findViewById(R.id.btAniadirPc);
            btCrearPc.setVisibility(View.INVISIBLE);
        }
    }

    private RVA_Info abrirRecycler(View view, NavController navC) {
        RecyclerView rvMontadores = view.findViewById(R.id.rvMontadores);
        RVA_Info rvIdInfo = new RVA_Info(listaMontadores,getView());
        rvMontadores.setAdapter(rvIdInfo);
        rvMontadores.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rvIdInfo;
    }

    public void dialogoAlerta(NavController navC) {
        MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(getActivity());
        alertDialog.setTitle("Cuidado");
        alertDialog.setMessage("Estás a punto de borrar a un ordenador, ¿Estás seguro?");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModel.borrarOrdenador(viewModel.getOrdenador());
                navC.navigate(R.id.ac_verOrdenador_to_inicio);
            }
        });
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    private void botonCrear(@NonNull View view, NavController navC) {
        Button btCrear = view.findViewById(R.id.btAniadirPc);
        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recogeDatosOrdenador(view, false)) {
                    viewModel.setCreaOrdenador(false);
                    navC.navigate(R.id.ac_verOrdenador_to_inicio);
                }
            }
        });
    }

    private void botonBorrar(@NonNull View view, NavController navC) {
        Button btBorrar = view.findViewById(R.id.btborraPc);
        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoAlerta(navC);
            }
        });
    }

    private void botonActualizar(@NonNull View view, NavController navC) {
        Button btActualizar = view.findViewById(R.id.btActualizarPc);
        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recogeDatosOrdenador(view, true)) {
                    viewModel.setCreaOrdenador(false);
                    navC.navigate(R.id.ac_verOrdenador_to_inicio);
                }
            }
        });
    }
}