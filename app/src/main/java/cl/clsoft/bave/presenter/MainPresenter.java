package cl.clsoft.bave.presenter;

import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.service.IBaveService;
import cl.clsoft.bave.view.ActivityMain;

public class MainPresenter extends BasePresenter {

    private static final String TAG = "MainPresenter";
    private ActivityMain mView;
    private IBaveService baveService;

    public MainPresenter(@NonNull final ActivityMain view, @NonNull final IBaveService baveService) {
        this.mView = view;
        this.baveService = baveService;
    }

    public void cargaArchivos() {

        try {
            File path = mView.getExternalFilesDir(null);
            Log.d(TAG, "path write: " + path.getPath());
            File file = new File(path, "my-file-name.txt");
            FileOutputStream stream = new FileOutputStream(file);
            try {
                stream.write("text-to-write".getBytes());
            } finally {
                stream.close();
            }
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        try {

            // Carga archivo Setup
            File tarjetaSD = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String path = tarjetaSD.getPath();
            String path2 = this.mView.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath();
            Log.d(TAG, path2);
            File Dir = new File(path);
            //File rutaArchivo = new File(this.mView.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "setup.txt");
            File rutaArchivo = new File(this.mView.getApplicationContext().getExternalFilesDir(null).getPath() + "/" + "setup.txt");
            this.baveService.cargarArchivoSetup(rutaArchivo);

            File rutaArchivoStock = new File(this.mView.getApplicationContext().getExternalFilesDir(null).getPath() + "/" + "stock.txt");
            this.baveService.cargarArchivoStock(rutaArchivoStock);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
