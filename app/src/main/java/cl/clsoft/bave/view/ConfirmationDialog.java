package cl.clsoft.bave.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ConfirmationDialog extends DialogFragment {

    private static final String TAG = "ConfirmationDialog";
    public ConfirmationDialog.ConfirmationDialogListener listener;

    public static ConfirmationDialog newInstance(String message,String title) {
        ConfirmationDialog f = new ConfirmationDialog();
        Bundle args = new Bundle();
        args.putString("message", message);
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    public static ConfirmationDialog newInstance(String message,String title, String tipo) {
        ConfirmationDialog f = new ConfirmationDialog();
        Bundle args = new Bundle();
        args.putString("message", message);
        args.putString("title", title);
        args.putString("tipo", tipo);
        f.setArguments(args);
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String message = getArguments().getString("message");
        String title = getArguments().getString("title");
        builder.setTitle(title);
        builder.setMessage(message)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogAceptarClick(ConfirmationDialog.this);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogCancelarClick(ConfirmationDialog.this);
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
            listener = (ConfirmationDialog.ConfirmationDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface ConfirmationDialogListener {
        public void onDialogAceptarClick(DialogFragment dialog);
        public void onDialogCancelarClick(DialogFragment dialog);
    }

}
