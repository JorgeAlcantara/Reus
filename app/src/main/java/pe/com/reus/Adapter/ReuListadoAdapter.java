package pe.com.reus.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.com.reus.Model.Reu;
import pe.com.reus.R;

public class ReuListadoAdapter extends RecyclerView.Adapter<ReuListadoAdapter.MyViewHolder>  {

    private List<Reu> reuList;

    private OnReuListener onReuListener;

    public void setOnReuListener(OnReuListener onReuListener) {
        this.onReuListener = onReuListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre, fecha, direccion;

        public MyViewHolder(View view, final OnReuListener onReuListener) {
            super(view);
            nombre = (TextView) view.findViewById(R.id.txtNombre);
            fecha = (TextView) view.findViewById(R.id.txtFecha);
            direccion = (TextView) view.findViewById(R.id.txtDireccion);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(position!= RecyclerView.NO_POSITION) {
                        onReuListener.onFondoClicked(position);
                    }

                }
            });
        }
    }


    public ReuListadoAdapter(List<Reu> reuList) {
        this.reuList = reuList;
    }

    @Override
    public ReuListadoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reu_fila, parent, false);

        return new ReuListadoAdapter.MyViewHolder(itemView,onReuListener);
    }

    @Override
    public void onBindViewHolder(ReuListadoAdapter.MyViewHolder holder, int position) {
        Reu reu = reuList.get(position);
        holder.nombre.setText(reu.getNombre());
        holder.fecha.setText(reu.getFecha());
        holder.direccion.setText(reu.getDireccion());
    }

    @Override
    public int getItemCount() {
        return reuList.size();
    }

}
