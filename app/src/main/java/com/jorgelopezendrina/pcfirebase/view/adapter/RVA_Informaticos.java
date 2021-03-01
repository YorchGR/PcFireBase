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
import com.jorgelopezendrina.pcfirebase.model.pojo.Informatico;
import com.jorgelopezendrina.pcfirebase.viewmodel.ViewModel;

import java.util.List;

public class RVA_Informaticos extends RecyclerView.Adapter<RVA_Informaticos.ViewHolder>{

    private List<Informatico> listaInformaticos;
    private NavController navC;
    private ViewModel viewModel;
    private Activity activity;

    public RVA_Informaticos(List<Informatico> listaInformaticos, NavController navC, ViewModel viewModel, Activity activity) {
        this.listaInformaticos = listaInformaticos;
        this.navC = navC;
        this.viewModel = viewModel;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RVA_Informaticos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_informatico, parent, false);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVA_Informaticos.ViewHolder holder, int position) {
        holder.tvInfor.setText(listaInformaticos.get(position).getNombreInfor());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setInformatico(listaInformaticos.get(position));
                navC.navigate(R.id.ac_listaElementos_to_verInformatico);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaInformaticos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvInfor;
        ConstraintLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.consInfor);
            tvInfor = itemView.findViewById(R.id.tvInfor);
        }
    }
}
