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

            // Carga archivo Setup
            File tarjetaSD = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String path = tarjetaSD.getPath();
            String path2 = this.mView.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath();
            Log.d(TAG, path2);
            File Dir = new File(path);

            File ruta = new File(this.mView.getApplicationContext().getExternalFilesDir(null).getPath());
            File listFile[] = ruta.listFiles();
            for (int i = 0; i < listFile.length; i++) {
                Log.d(TAG, listFile[i].getName());

                // Archivo setup
                if (listFile[i].getName().startsWith("setup") || listFile[i].getName().startsWith("SETUP")) {
                    File archivoSetup = new File(ruta.getPath() + "/" + listFile[i].getName());
                    this.baveService.cargarArchivoSetup(archivoSetup);
                }

                // Archivo stock
                if (listFile[i].getName().startsWith("XXEJE_OUT_Saldo_Stock") || listFile[i].getName().startsWith("XXEJE_OUT_SALDO_STOCK")) {
                    File archivoStock = new File(ruta.getPath() + "/" + listFile[i].getName());
                    this.baveService.cargarArchivoStock(archivoStock);
                }

                // Archivo Ciclico
                if (listFile[i].getName().startsWith("O_C_") || listFile[i].getName().startsWith("o_c_")) {
                    File archivoCiclico = new File(ruta.getPath() + "/" + listFile[i].getName());
                    this.baveService.cargarArchivoCiclico(archivoCiclico);
                }

                // Archivo Fisico
                if (listFile[i].getName().startsWith("O_F_") || listFile[i].getName().startsWith("o_f_")) {
                    File archivoFisico = new File(ruta.getPath() + "/" + listFile[i].getName());
                    this.baveService.cargarArchivoFisico(archivoFisico);
                }
            }

            //File rutaArchivo = new File(this.mView.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "setup.txt");
           // File rutaArchivo = new File(this.mView.getApplicationContext().getExternalFilesDir(null).getPath() + "/" + "setup.txt");
            //this.baveService.cargarArchivoSetup(rutaArchivo);

            //File rutaArchivoStock = new File(this.mView.getApplicationContext().getExternalFilesDir(null).getPath() + "/" + "stock.txt");
            //this.baveService.cargarArchivoStock(rutaArchivoStock);

            //File rutaArchivoCiclico = new File(this.mView.getApplicationContext().getExternalFilesDir(null).getPath() + "/" + "O_C_CICLO_RA01.txt");
            //this.baveService.cargarArchivoCiclico(rutaArchivoCiclico);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
