package cl.clsoft.bave.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cl.clsoft.bave.R;

public class FragmentTagsNoInventariados extends Fragment {

    private static final String TAG = "TagsNoInventariados";

    // Controles
    private RecyclerView recyclerViewTags;
    private AdapterInventarioFisicoDetalleNoInventariado adapter;

    public FragmentTagsNoInventariados(AdapterInventarioFisicoDetalleNoInventariado adapter) {
        this.adapter = adapter;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tags_noinventariados, container, false);

        // Bind Controls
        final GestureDetector mGestureDetector = new GestureDetector(view.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        this.recyclerViewTags = view.findViewById(R.id.recyclerViewTags);
        this.recyclerViewTags.setHasFixedSize(true);
        this.recyclerViewTags.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.recyclerViewTags.setAdapter(this.adapter);
        return view;
    }

}
