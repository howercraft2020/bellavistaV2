package cl.clsoft.bave.service.impl;

import java.util.List;

import cl.clsoft.bave.dao.IDatosTransSubInvDao;
import cl.clsoft.bave.dao.IMtlOnhandQuantitiesDao;
import cl.clsoft.bave.dao.IMtlTransactionsInterfaceDao;
import cl.clsoft.bave.dao.impl.DatosTransSubinvImpl;
import cl.clsoft.bave.dao.impl.MtlOnhandQuantitiesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlTransactionInterfaceDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosTransSubinv;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.model.MtlTransactionsInterface;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.service.ITransSubinvService;

public class TransSubinvService implements ITransSubinvService {
    @Override
    public List<DatosTransSubinv> getTransSubinv() throws ServiceException {
        IDatosTransSubInvDao iDatosTransSubInvDao = new DatosTransSubinvImpl();
        try {
            return iDatosTransSubInvDao.getTransSubinv();
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public void cargaTransferencia(String articulo, String lote,String subinventario) throws ServiceException{
        IMtlOnhandQuantitiesDao iMtlOnhandQuantitiesDao = new MtlOnhandQuantitiesDaoImpl();

        MtlOnhandQuantities mtlOnhandQuantities;

        try{

            //Captura datos
            mtlOnhandQuantities = iMtlOnhandQuantitiesDao.get(articulo, lote,subinventario);
            if(mtlOnhandQuantities == null){
                throw new ServiceException(1,"No se encuentra información de cantidad de stock");
            }





        }catch (ServiceException e){
            throw e;
        }catch (DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }catch (Exception e){
            throw new ServiceException(2, e.getMessage());
        }
    }
}
