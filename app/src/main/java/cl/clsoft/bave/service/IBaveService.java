package cl.clsoft.bave.service;

import java.io.File;

import cl.clsoft.bave.exception.ServiceException;

public interface IBaveService {

    public void cargarArchivoSetup(File archivo) throws ServiceException;
    public void cargarArchivoStock(File archivo) throws ServiceException;
    public void cargarArchivoCiclico(File archivo) throws ServiceException;
    public void cargarArchivoFisico(File archivo) throws ServiceException;

}
