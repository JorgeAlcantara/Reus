package pe.com.reus.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pe.com.reus.Adapter.OnReuListener;
import pe.com.reus.Adapter.ReuListadoAdapter;
import pe.com.reus.GaleriaFotosActivity;
import pe.com.reus.Model.Reu;
import pe.com.reus.Model.Reu2;
import pe.com.reus.R;

public class GaleriaFragment extends Fragment {

    private List<Reu2> reuList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReuListadoAdapter mAdapter;

    public GaleriaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_galeria, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new ReuListadoAdapter(reuList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);

        listarReu();

        return view;
    }

    private void listarReu() {
        Reu2 reu = new Reu2( "Cumple de Mafer", "07/12/2018", "Av. La marina 163 - San Miguel");
        reuList.add(reu);

        reu = new Reu2("Reencuentro de la Promo", "09/12/2018", "Av. Sucre 562 - Magdalena");
        reuList.add(reu);

        reu = new Reu2("Pichanga saliendo del trabajo", "09/12/2018", "Av. Bolognesi 562 - Magdalena");
        reuList.add(reu);

        reu = new Reu2("Ir en mancha al cine", "10/12/2018", "Av. Riva aguero 562 - San Miguel");
        reuList.add(reu);


        mAdapter.setOnReuListener(new OnReuListener() {
            @Override
            public void onFondoClicked(int position) {
                //Toast.makeText(getActivity(), "Reu seleccionado : " + position, Toast.LENGTH_SHORT).show();
                abrirCamara();
            }
        });

        mAdapter.notifyDataSetChanged();
    }

    private void abrirCamara() {
        Intent intent = new Intent(getActivity(), GaleriaFotosActivity.class);
        startActivity(intent);
    }

}
