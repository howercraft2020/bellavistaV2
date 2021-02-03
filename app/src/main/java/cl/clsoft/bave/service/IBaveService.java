package cl.clsoft.bave.service;

import java.io.File;

import cl.clsoft.bave.exception.ServiceException;

public interface IBaveService {

    public void cargarArchivoSetup(File archivo) throws ServiceException;
    public void cargarArchivoStock(File archivo) throws ServiceException;
    public void cargarArchivoCiclico(String nombreArchivo) throws ServiceException;
    public void cargarArchivoFisico(String nombreArchivo) throws ServiceException;

}
