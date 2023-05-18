package com.example.samuelparcialnumero4.dao.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samuelparcialnumero4.R;
import com.example.samuelparcialnumero4.VehiculoActivity;
import com.example.samuelparcialnumero4.dao.models.MdVehiculos;

import java.util.ArrayList;

public class VehiAdapter extends RecyclerView.Adapter<VehiAdapter.VehiViewHolder>{

    ArrayList<MdVehiculos> lista;
    ArrayList<MdVehiculos> listaO;

    public VehiAdapter(ArrayList<MdVehiculos> li) {
        this.lista = li;
        listaO = new ArrayList<>();
        listaO.addAll(li);
    }

    @NonNull
    @Override
    public VehiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_vehi, null, false);
        return new VehiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiViewHolder holder, int position) {
        holder.viewMarca.setText(lista.get(position).getMarca());
        holder.viewModelo.setText(lista.get(position).getModelo());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class VehiViewHolder extends RecyclerView.ViewHolder {

        TextView viewMarca, viewModelo;

        public VehiViewHolder(@NonNull View itemView) {
            super(itemView);

            viewMarca = itemView.findViewById(R.id.viewMarca);
            viewModelo = itemView.findViewById(R.id.viewModelo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VehiculoActivity.class);
                    intent.putExtra("ID", lista.get(getAdapterPosition()).getIdVehiculo());
                    context.startActivity(intent);
                }
            });
        }
    }
}
