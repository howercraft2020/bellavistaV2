package cl.clsoft.bave.service.impl;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import cl.clsoft.bave.dao.ILocalizadorDao;
import cl.clsoft.bave.dao.IOrganizacionDao;
import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.impl.LocalizadorDaoImpl;
import cl.clsoft.bave.dao.impl.OrganizacionDaoImpl;
import cl.clsoft.bave.dao.impl.SubinventarioDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.Organizacion;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.IBaveService;

public class BaveServiceImpl implements IBaveService {

    @Override
    public void cargarArchivoSetup(File archivo) throws ServiceException {
        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        IOrganizacionDao organizacionDao = new OrganizacionDaoImpl();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(archivo);
            InputStreamReader abrirArchivo = new InputStreamReader(fis);
            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = null;
            linea = leerArchivo.readLine();
            while(linea != null){
                String [] extraccion = linea.split("\\|");
                if (extraccion[0].equals("2")) {
                    Subinventario subinventario = new Subinventario();
                    subinventario.setOrganizationId(new Long(extraccion[1]));
                    subinventario.setCodSubinventario(extraccion[2]);
                    subinventario.setCodLocalizador(extraccion[3]);
                    subinventarioDao.insert(subinventario);
                } else if (extraccion[0].equals("3")) {
                    Localizador localizador = new Localizador();
                    localizador.setIdLocalizador(new Long(extraccion[1]));
                    localizador.setOrganizationId(new Long(extraccion[2]));
                    localizador.setCodSubinventario(extraccion[3]);
                    localizador.setCodLocalizador(extraccion[4]);
                    localizador.setCodSeg1(extraccion[5]);
                    localizador.setCodSeg2(extraccion[6]);
                    localizador.setCodSeg3(extraccion[7]);
                    localizador.setCodSeg4(extraccion[8]);
                    localizador.setCodSeg5(extraccion[9]);
                    localizador.setCodSeg6(extraccion[10]);
                    localizadorDao.insert(localizador);
                } else if (extraccion[0].equals("4")) {
                    Organizacion organizacion = new Organizacion();
                    organizacion.setIdOrganizacion(new Long(extraccion[1]));
                    organizacion.setCode(extraccion[2]);
                    organizacionDao.insert(organizacion);
                }
                linea = leerArchivo.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cargarArchivoCiclico(String nombreArchivo) throws ServiceException {

    }

    @Override
    public void cargarArchivoFisico(String nombreArchivo) throws ServiceException {

    }

}
