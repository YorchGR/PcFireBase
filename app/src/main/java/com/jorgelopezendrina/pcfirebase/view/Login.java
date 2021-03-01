package com.jorgelopezendrina.pcfirebase.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jorgelopezendrina.pcfirebase.R;
import com.jorgelopezendrina.pcfirebase.viewmodel.ViewModel;


public class Login extends Fragment {

    private ViewModel viewModel;
    private NavController navC;

    public Login() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navC = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        botonLoguear(view, navC);
    }

    private void botonLoguear(View view, NavController navC) {
        Button btLoguearte = view.findViewById(R.id.bt_log);
        btLoguearte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              login(view);
           }
        });
    }

    private void login(View view) {
        EditText etEmail;
        EditText etPass;
        etEmail = view.findViewById(R.id.etMail);
        etPass = view.findViewById(R.id.etPass);
        viewModel.loguearUsuario(etEmail.getText().toString(), etPass.getText().toString()).observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    navC.navigate(R.id.ac_login_to_inicio);
                    Toast.makeText(getContext(), "Usuario logueado", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(), "Se ha producido un error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}