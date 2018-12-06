package pe.com.reus.Fragment;

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

import pe.com.reus.Adapter.GenteAdapter;
import pe.com.reus.Model.Gente;
import pe.com.reus.R;

public class GenteFragment extends Fragment {

    private List<Gente> genteList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GenteAdapter mAdapter;

    //private OnFragmentInteractionListener mListener;

    public GenteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gente, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new GenteAdapter(genteList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));


        recyclerView.setAdapter(mAdapter);

        listarGente();

        return view;
    }


    private void listarGente() {
        Gente gente = new Gente("Jorge Alcantara", "998191233","Lima");
        genteList.add(gente);

        gente = new Gente("Hugo Mora", "971514071","Lima");
        genteList.add(gente);

        mAdapter.notifyDataSetChanged();


    }

}
