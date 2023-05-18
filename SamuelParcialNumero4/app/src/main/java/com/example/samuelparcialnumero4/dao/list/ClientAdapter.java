package com.example.samuelparcialnumero4.dao.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.samuelparcialnumero4.ClienteActivity;
import com.example.samuelparcialnumero4.R;
import com.example.samuelparcialnumero4.dao.models.MdClientes;

import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder>{

    ArrayList<MdClientes> lista;
    ArrayList<MdClientes> listaO;

    public ClientAdapter(ArrayList<MdClientes> li) {
        this.lista = li;
        listaO = new ArrayList<>();
        listaO.addAll(li);
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_client, null, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        holder.viewNombre.setText(lista.get(position).getNombreCliente());
        holder.viewApellido.setText(lista.get(position).getApellidosCliente());
        holder.viewDireccion.setText(lista.get(position).getDireccionCliente());
        holder.viewCiudad.setText(lista.get(position).getCuidadCliente());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewDireccion, viewCiudad, viewApellido;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewApellido = itemView.findViewById(R.id.viewApellido);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewCiudad = itemView.findViewById(R.id.viewCiudad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ClienteActivity.class);
                    intent.putExtra("ID", lista.get(getAdapterPosition()).getIdCliente());
                    context.startActivity(intent);
                }
            });
        }
    }
}
