package cl.clsoft.bave.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cl.clsoft.bave.R;

public class FragmentTagsInventariados extends Fragment {

    private static final String TAG = "FragmentTagsInventariados";

    // Controles
    private RecyclerView recyclerViewTags;
    private AdapterInventarioFisicoDetalle adapter;

    public FragmentTagsInventariados(AdapterInventarioFisicoDetalle adapter) {
        this.adapter = adapter;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tags_inventariados, container, false);
        this.recyclerViewTags = view.findViewById(R.id.recyclerViewTags);
        this.recyclerViewTags.setHasFixedSize(true);
        this.recyclerViewTags.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.recyclerViewTags.setAdapter(this.adapter);
        return view;
    }

}
