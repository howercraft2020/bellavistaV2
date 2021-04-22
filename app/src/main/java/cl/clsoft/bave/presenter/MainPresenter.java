package cl.clsoft.bave.presenter;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.service.IBaveService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityMain;

public class MainPresenter extends BasePresenter {

    private static final String TAG = "MainPresenter";
    private ActivityMain mView;
    private IBaveService baveService;
    private TaskExecutor mTaskExecutor;

    public MainPresenter(@NonNull final ActivityMain view, @NonNull final TaskExecutor taskExecutor, @NonNull final IBaveService baveService) {
        this.mView = view;
        this.baveService = baveService;
        this.mTaskExecutor = taskExecutor;
    }

    public void cargaArchivos() {
        mView.showProgres("Cargando archivos");
        mTaskExecutor.async(new MainPresenter.CargaArchivo());
    }

    private class CargaArchivo implements AppTask<Long> {

        public CargaArchivo() {}

        @Override
        public Long execute() {
            try {
                File tarjetaSD = Environment.getExternalStorageDirectory();
                File ruta = new File(tarjetaSD.getAbsolutePath(), "outbound");
                Log.d(TAG, ruta.getAbsolutePath());
                File[] listFile = ruta.listFiles();

                if (listFile == null) {
                    mView.runOnUiThread(new Runnable() {
                        public void run() {
                            mView.hideProgres();
                            mView.showWarning("Directorio outbound no encontrado");
                        }
                    });
                    return 0L;
                }

                for (int i = 0; i < listFile.length; i++) {
                    Log.d(TAG, listFile[i].getName());


                    // Archivo setup
                    if (listFile[i].getName().startsWith("XXEJE_INI_Setup") || listFile[i].getName().startsWith("XXEJE_INI_SETUP")) {
                        mView.runOnUiThread(new Runnable() {
                            public void run() {
                                mView.showProgres("Cargando archivo Setup");
                            }
                        });
                        File archivoSetup = new File(ruta.getPath() + "/" + listFile[i].getName());
                        baveService.cargarArchivoSetup(archivoSetup);
                        //mView.getContentResolver().delete(Uri.fromFile(archivoSetup), null, null);
                        MediaScannerConnection.scanFile(mView, new String[] {archivoSetup.getAbsolutePath()}, null, null);
                    }

                    // Archivo stock
                    if (listFile[i].getName().startsWith("XXEJE_OUT_Saldo_Stock") || listFile[i].getName().startsWith("XXEJE_OUT_SALDO_STOCK")) {
                        mView.runOnUiThread(new Runnable() {
                            public void run() {
                                mView.showProgres("Cargando archivo Stock");
                            }
                        });
                        File archivoStock = new File(ruta.getPath() + "/" + listFile[i].getName());
                        baveService.cargarArchivoStock(archivoStock);
                        //mView.getContentResolver().delete(Uri.fromFile(archivoStock), null, null);
                        MediaScannerConnection.scanFile(mView, new String[] {archivoStock.getAbsolutePath()}, null, null);
                    }

                    // Archivo Ciclico
                    if (listFile[i].getName().startsWith("O_C_") || listFile[i].getName().startsWith("o_c_")) {
                        mView.runOnUiThread(new Runnable() {
                            public void run() {
                                mView.showProgres("Cargando archivo Conteo Ciclico");
                            }
                        });
                        File archivoCiclico = new File(ruta.getPath() + "/" + listFile[i].getName());
                        baveService.cargarArchivoCiclico(archivoCiclico);
                        //mView.getContentResolver().delete(Uri.fromFile(archivoCiclico), null, null);
                        MediaScannerConnection.scanFile(mView, new String[] {archivoCiclico.getAbsolutePath()}, null, null);
                    }

                    // Archivo Fisico
                    if (listFile[i].getName().startsWith("O_F_") || listFile[i].getName().startsWith("o_f_")) {
                        mView.runOnUiThread(new Runnable() {
                            public void run() {
                                mView.showProgres("Cargando archivo Inventario Fisico");
                            }
                        });
                        File archivoFisico = new File(ruta.getPath() + "/" + listFile[i].getName());
                        baveService.cargarArchivoFisico(archivoFisico);
                        //mView.getContentResolver().delete(Uri.fromFile(archivoFisico), null, null);
                        MediaScannerConnection.scanFile(mView, new String[] {archivoFisico.getAbsolutePath()}, null, null);
                    }

                    // Archivo Recepción
                    if (listFile[i].getName().startsWith("O_1_") || listFile[i].getName().startsWith("o_1_")) {
                        mView.runOnUiThread(new Runnable() {
                            public void run() {
                                mView.showProgres("Cargando archivo Recepcion OC");
                            }
                        });
                        File archivoRecepcion = new File(ruta.getPath() + "/" + listFile[i].getName());
                        baveService.cargarArchivoRecepcion(archivoRecepcion);
                        //mView.getContentResolver().delete(Uri.fromFile(archivoRecepcion), null, null);
                        MediaScannerConnection.scanFile(mView, new String[] {archivoRecepcion.getAbsolutePath()}, null, null);
                    }

                    // Archivo Entrega
                    if (listFile[i].getName().startsWith("O_2_") || listFile[i].getName().startsWith("o_2_")) {
                        mView.runOnUiThread(new Runnable() {
                            public void run() {
                                mView.showProgres("Cargando archivo Entrega");
                            }
                        });
                        File archivoEntrega = new File(ruta.getPath() + "/" + listFile[i].getName());
                        baveService.cargarArchivoEntrega(archivoEntrega);
                        //mView.getContentResolver().delete(Uri.fromFile(archivoEntrega), null, null);
                        MediaScannerConnection.scanFile(mView, new String[] {archivoEntrega.getAbsolutePath()}, null, null);
                    }

                    // Archivo Entrega Orgs
                    if (listFile[i].getName().startsWith("O_R_") || listFile[i].getName().startsWith("o_r_")) {
                        mView.runOnUiThread(new Runnable() {
                            public void run() {
                                mView.showProgres("Cargando archivo Entrega Orgs");
                            }
                        });
                        File archivoEntrega = new File(ruta.getPath() + "/" + listFile[i].getName());
                        baveService.cargarArchivoEntregaOrgs(archivoEntrega);
                        //mView.getContentResolver().delete(Uri.fromFile(archivoEntrega), null, null);
                        MediaScannerConnection.scanFile(mView, new String[] {archivoEntrega.getAbsolutePath()}, null, null);
                    }
                }
                return 0L;
            } catch (ServiceException e) {
                Log.d(TAG, "GetEntriesInventariadas::execute::ServiceException");
                e.printStackTrace();
                mView.runOnUiThread(new Runnable() {
                    public void run() {
                        mView.showError(e.getDescripcion());
                    }
                });
            }
            return 0L;
        }

        @Override
        public void onPostExecute(@Nullable Long result) {
            mView.hideProgres();

        }
    }

}
