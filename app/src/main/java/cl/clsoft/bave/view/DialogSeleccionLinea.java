package cl.clsoft.bave.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogSeleccionLinea extends DialogFragment {

    private static final String TAG = "DialogSeleccionLinea";
    private CharSequence[] items;
    public String selectedItem;
    public DialogSeleccionLinea.DialogSeleccionLineaListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog");
        Bundle mArgs = getArguments();
        Log.d(TAG, "getItems");
        items = mArgs.getCharSequenceArray("items");
        selectedItem = items[0].toString();
        Log.d(TAG, "Items size " + items.length);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Seleccione la línea")
            .setSingleChoiceItems(items,0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectedItem = items[which].toString();
                }
            })
            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    listener.onDialogLineaPositiveClick(DialogSeleccionLinea.this);
                }
            });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (DialogSeleccionLinea.DialogSeleccionLineaListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface DialogSeleccionLineaListener {
        public void onDialogLineaPositiveClick(DialogFragment dialog);
    }

}
