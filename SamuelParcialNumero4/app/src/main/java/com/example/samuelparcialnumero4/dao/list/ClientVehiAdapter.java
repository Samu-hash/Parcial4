package com.example.samuelparcialnumero4.dao.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samuelparcialnumero4.ClienteVehiculoActivity;
import com.example.samuelparcialnumero4.R;
import com.example.samuelparcialnumero4.dao.models.MdClienteVehiculo;

import java.util.ArrayList;

public class ClientVehiAdapter extends RecyclerView.Adapter<ClientVehiAdapter.ClientVehiViewHolder>{

    ArrayList<MdClienteVehiculo> lista;
    ArrayList<MdClienteVehiculo> listaO;

    public ClientVehiAdapter(ArrayList<MdClienteVehiculo> li) {
        this.lista = li;
        listaO = new ArrayList<>();
        listaO.addAll(li);
    }

    @NonNull
    @Override
    public ClientVehiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_client_vehi, null, false);
        return new ClientVehiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientVehiViewHolder holder, int position) {
        holder.viewClient.setText(lista.get(position).getIdCliente()+"");
        holder.viewVehi.setText(lista.get(position).getIdVehiculo()+"");
        holder.viewMatricula.setText(lista.get(position).getMatricula());
        holder.viewKilo.setText(lista.get(position).getKilometros());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ClientVehiViewHolder extends RecyclerView.ViewHolder {

        TextView viewClient, viewVehi, viewMatricula, viewKilo;

        public ClientVehiViewHolder(@NonNull View itemView) {
            super(itemView);

            viewClient = itemView.findViewById(R.id.viewClient);
            viewVehi = itemView.findViewById(R.id.viewVehi);
            viewMatricula = itemView.findViewById(R.id.viewMatricula);
            viewKilo = itemView.findViewById(R.id.viewKilo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ClienteVehiculoActivity.class);
                    intent.putExtra("ID", lista.get(getAdapterPosition()).getIdVehiculo());
                    context.startActivity(intent);
                }
            });
        }
    }
}
