package pe.com.reus.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.com.reu.Model.Gente;
import pe.com.reu.R;

public class GenteAdapter extends RecyclerView.Adapter<GenteAdapter.MyViewHolder> {

    private List<Gente> genteList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre, telefono, distrito;

        public MyViewHolder(View view) {
            super(view);
            nombre = (TextView) view.findViewById(R.id.txtNombre);
            telefono = (TextView) view.findViewById(R.id.txtTelefono);
            distrito = (TextView) view.findViewById(R.id.txtDistrito);
        }
    }


    public GenteAdapter(List<Gente> genteList) {
        this.genteList = genteList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gente_fila, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Gente gente = genteList.get(position);
        holder.nombre.setText(gente.getNombre());
        holder.telefono.setText(gente.getTelefono());
        holder.distrito.setText(gente.getDistrito());
    }

    @Override
    public int getItemCount() {
        return genteList.size();
    }

}
