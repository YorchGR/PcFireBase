package com.jorgelopezendrina.pcfirebase.view;

import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jorgelopezendrina.pcfirebase.R;
import com.jorgelopezendrina.pcfirebase.model.pojo.Informatico;
import com.jorgelopezendrina.pcfirebase.viewmodel.ViewModel;

public class VerInformatico extends Fragment {

    private ViewModel viewModel;

    public VerInformatico() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_ver_informatico, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navC = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        escondeBotones(view);
        if (viewModel.isCrearInfor()) {
            botonCrear(view, navC);
        } else {
            cargaInformatico(view);
            botonBorrar(view, navC);
            botonActualizar(view, navC);
        }
    }

    private void cargaInformatico(@NonNull View view) {
        ImageView imgInfor = view.findViewById(R.id.imageViewInformatico);
        EditText etNomInfor = view.findViewById(R.id.etNombreInfor);
        EditText etApellInfor = view.findViewById(R.id.etApellidoInfor);
        EditText etDniInfor = view.findViewById(R.id.etDniInfor);
        EditText etTlfInfor = view.findViewById(R.id.etTlfInfor);
        EditText etImgInfor = view.findViewById(R.id.etImgInfor);
        Glide.with(getContext()).load(viewModel.getInformatico().getImgInfor()).into(imgInfor);
        etNomInfor.setText(viewModel.getInformatico().getNombreInfor());
        etApellInfor.setText(viewModel.getInformatico().getApellInfor());
        etDniInfor.setText(viewModel.getInformatico().getDniInfor());
        etDniInfor.setEnabled(false);
        etTlfInfor.setText(viewModel.getInformatico().getTlfInfor());
        etImgInfor.setText(viewModel.getInformatico().getImgInfor());
    }

    private boolean recogeDatosInformatico(@NonNull View view, boolean actualizaOCrea) {
        EditText etNomInfor = view.findViewById(R.id.etNombreInfor);
        EditText etApellInfor = view.findViewById(R.id.etApellidoInfor);
        EditText etDniInfor = view.findViewById(R.id.etDniInfor);
        EditText etTlfInfor = view.findViewById(R.id.etTlfInfor);
        EditText etImgInfor = view.findViewById(R.id.etImgInfor);
        Informatico informatico;
        if (actualizaOCrea) {
            informatico = viewModel.getInformatico();
        } else {
            informatico = new Informatico();
        }
        informatico.setNombreInfor(etNomInfor.getText().toString());
        informatico.setApellInfor(etApellInfor.getText().toString());
        informatico.setDniInfor(etDniInfor.getText().toString());
        informatico.setTlfInfor(etTlfInfor.getText().toString());
        informatico.setImgInfor(etImgInfor.getText().toString());
        if (informatico.getNombreInfor().equals("") || informatico.getDniInfor().equals("") || informatico.getTlfInfor().equals("")) {
            Toast.makeText(getContext(), "Los campos de nombre, DNI y tlf, son obligatorios", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (actualizaOCrea) {
                viewModel.actualizaInformatico(viewModel.getInformatico());
            } else {
                viewModel.insertaInformatico(informatico);
            }
            return true;
        }
    }

    private void botonCrear(@NonNull View view, NavController navC) {
        Button btCrearInfor = view.findViewById(R.id.btCrearInfor);
        btCrearInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recogeDatosInformatico(view,false)) {
                    viewModel.setCrearInfor(false);
                    navC.navigate(R.id.ac_verInformatico_to_inicio);
                }
            }
        });
    }

    private void botonBorrar(@NonNull View view, NavController navC) {
        Button btBorrar = view.findViewById(R.id.btBorrarInfor);
        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoAlerta(navC);
            }
        });
    }

    private void botonActualizar(@NonNull View view, NavController navC) {
        Button btActualizar = view.findViewById(R.id.btActualizarInfor);
        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (recogeDatosInformatico(view,true)) {
                    viewModel.setCrearInfor(false);
                    navC.navigate(R.id.ac_verInformatico_to_inicio);
                }
            }
        });
    }

    public void dialogoAlerta(NavController navC) {
        MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(getActivity());
        alertDialog.setTitle("Cuidado");
        alertDialog.setMessage("Estás a punto de borrar a un informático, ¿Estás seguro?");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModel.borrarInformatico(viewModel.getInformatico());
                navC.navigate(R.id.ac_verInformatico_to_inicio);
            }
        });
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    private void escondeBotones(@NonNull View view) {
        if (viewModel.isCrearInfor()) {
            Button btBorrarInfor = view.findViewById(R.id.btBorrarInfor);
            btBorrarInfor.setVisibility(View.INVISIBLE);
            Button btActualizarInfor = view.findViewById(R.id.btActualizarInfor);
            btActualizarInfor.setVisibility(View.INVISIBLE);
            ImageView imgInfor = view.findViewById(R.id.imageViewInformatico);
            imgInfor.setImageResource(R.drawable.recurso_1);
        } else {
            Button btCrearInfor = view.findViewById(R.id.btCrearInfor);
            btCrearInfor.setVisibility(View.INVISIBLE);
        }
    }
}