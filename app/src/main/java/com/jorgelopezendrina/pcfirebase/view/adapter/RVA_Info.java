package com.jorgelopezendrina.pcfirebase.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jorgelopezendrina.pcfirebase.R;
import com.jorgelopezendrina.pcfirebase.model.pojo.Informatico;

import java.util.List;

public class RVA_Info extends RecyclerView.Adapter<RVA_Info.ViewHolder>{

    private List<Informatico> listaMontadores;
    private TextView etIdInforCreador;
    private View viewAux;

    public RVA_Info(List<Informatico> listaMontadores, View viewAux) {
        this.listaMontadores = listaMontadores;
        this.viewAux = viewAux;
    }

    @NonNull
    @Override
    public RVA_Info.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_infor_id, parent, false);
        etIdInforCreador = viewAux.findViewById(R.id.etIdInforCreador);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVA_Info.ViewHolder holder, int position) {
        holder.dniInfor.setText(String.valueOf(listaMontadores.get(position).getDniInfor()));
        holder.nombreInfor.setText(listaMontadores.get(position).getNombreInfor());
        holder.consID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etIdInforCreador.setText(listaMontadores.get(position).getDniInfor());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaMontadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dniInfor;
        TextView nombreInfor;
        ConstraintLayout consID;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dniInfor = itemView.findViewById(R.id.tvInforID);
            nombreInfor= itemView.findViewById(R.id.tvInforN);
            consID = itemView.findViewById(R.id.constID);
        }
    }
}
