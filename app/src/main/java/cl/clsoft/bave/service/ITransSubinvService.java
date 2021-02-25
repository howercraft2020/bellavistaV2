package cl.clsoft.bave.service;

import java.util.List;


import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosTransSubinv;
import cl.clsoft.bave.model.MtlTransactionsInterface;


public interface ITransSubinvService {

    public List<DatosTransSubinv> getTransSubinv() throws ServiceException;
    public void cargaTransferencia(String articulo,String lote, String subinventario) throws ServiceException;
}
