package com.jorgelopezendrina.pcfirebase.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.jorgelopezendrina.pcfirebase.R;
import com.jorgelopezendrina.pcfirebase.model.pojo.Ordenador;
import com.jorgelopezendrina.pcfirebase.viewmodel.ViewModel;

import java.util.List;

public class RVA_Ordenadores  extends RecyclerView.Adapter<RVA_Ordenadores.ViewHolder> {

    private List<Ordenador> listaOrdenadores;
    private NavController navC;
    private ViewModel viewModel;
    private Activity activity;

    public RVA_Ordenadores(List<Ordenador> listaOrdenadores, NavController navC, ViewModel viewModel, Activity activity) {
        this.listaOrdenadores = listaOrdenadores;
        this.navC = navC;
        this.viewModel = viewModel;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RVA_Ordenadores.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ordenador, parent, false);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVA_Ordenadores.ViewHolder holder, int position) {
        holder.tvMarcaRV.setText(listaOrdenadores.get(position).getMarcaPc());
        holder.tvModeloRV.setText(listaOrdenadores.get(position).getModeloPc());
        holder.consOrdenador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setOrdenador(listaOrdenadores.get(position));
                navC.navigate(R.id.ac_listaElementos_to_verOrdenador);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaOrdenadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMarcaRV, tvModeloRV;
        ConstraintLayout consOrdenador;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            consOrdenador = itemView.findViewById(R.id.consOrdenador);
            tvMarcaRV = itemView.findViewById(R.id.tvMarcaRV);
            tvModeloRV = itemView.findViewById(R.id.tvModeloRV);
        }
    }
}
