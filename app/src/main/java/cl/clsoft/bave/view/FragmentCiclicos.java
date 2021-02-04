package cl.clsoft.bave.view;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cl.clsoft.bave.R;

public class FragmentCiclicos extends Fragment {

    private static final String TAG = "FragmentCiclicos";
    private RecyclerView recyclerViewCiclicos;
    private AdapterItemConteoCiclico adapter;

    public FragmentCiclicos(AdapterItemConteoCiclico adapter) {this.adapter = adapter; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_ciclicos, container, false);

        // Bind Controls
        final GestureDetector mGestureDetector = new GestureDetector(view.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        this.recyclerViewCiclicos = view.findViewById(R.id.recyclerViewCiclicos);
        this.recyclerViewCiclicos.setHasFixedSize(true);
        this.recyclerViewCiclicos.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.recyclerViewCiclicos.setAdapter(adapter);
        return view;
    }
}
