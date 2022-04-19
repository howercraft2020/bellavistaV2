package cl.clsoft.bave.dao.rowmapper.service;

import java.io.File;

import cl.clsoft.bave.exception.ServiceException;

public interface IBaveService {

    public void cargarArchivoSetup(File archivo) throws ServiceException;
    public void cargarArchivoStock(File archivo) throws ServiceException;
    public void cargarArchivoCiclico(File archivo) throws ServiceException;
    public void cargarArchivoFisico(File archivo) throws ServiceException;
    public void cargarArchivoRecepcion(File archivo) throws ServiceException;
    public void cargarArchivoEntrega(File archivo) throws ServiceException;
    public void cargarArchivoEntregaOrgs(File archivo) throws ServiceException;

}
