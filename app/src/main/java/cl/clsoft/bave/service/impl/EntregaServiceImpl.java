package cl.clsoft.bave.service.impl;

import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.ILocalizadorDao;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.IRcvShipmentHeadersDao;
import cl.clsoft.bave.dao.IRcvTransactionsDao;
import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.impl.LocalizadorDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.RcvShipmentHeadersDaoImpl;
import cl.clsoft.bave.dao.impl.RcvTransactionsDaoImpl;
import cl.clsoft.bave.dao.impl.SubinventarioDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.IEntregaService;

public class EntregaServiceImpl implements IEntregaService {

    private static final String TAG = "SERVICE";

    @Override
    public List<RcvShipmentHeaders> getEntregas() throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getEntregas");

        IRcvShipmentHeadersDao rcvShipmentHeadersDao = new RcvShipmentHeadersDaoImpl();
        try {
            return rcvShipmentHeadersDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public RcvShipmentHeaders getEntrega(Long shipmentHeaderId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getEntrega");
        Log.d(TAG, "EntregaServiceImpl::getEntrega::shipmentHeaderId: " + shipmentHeaderId);

        IRcvShipmentHeadersDao rcvShipmentHeadersDao = new RcvShipmentHeadersDaoImpl();
        try {
            RcvShipmentHeaders rcvShipmentHeaders = rcvShipmentHeadersDao.get(shipmentHeaderId);
            if (rcvShipmentHeaders == null) {
                throw new ServiceException(1, "Entrega " + shipmentHeaderId + " no existe en el sistema");
            }
            return rcvShipmentHeaders;
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getMtlSystemItemsBySegment");

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            MtlSystemItems mtlSystemItems = mtlSystemItemsDao.getBySegment(segment);
            if (mtlSystemItems == null) {
                throw new ServiceException(1, "Item " + segment + " no encontrado en tabla maestra");
            }
            return mtlSystemItems;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlSystemItems getMtlSystemItemsById(Long inventoryItemId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getMtlSystemItemsById");

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            MtlSystemItems mtlSystemItems = mtlSystemItemsDao.get(inventoryItemId);
            if (mtlSystemItems == null) {
                throw new ServiceException(1, "Item ID " + inventoryItemId + " no encontrado en tabla maestra");
            }
            return mtlSystemItems;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<RcvTransactions> getTransaccionsByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getTransaccionsByShipmentInventory");
        Log.d(TAG, "EntregaServiceImpl::getTransaccionsByShipmentInventory::shipmentHeaderId: " + shipmentHeaderId);
        Log.d(TAG, "EntregaServiceImpl::getTransaccionsByShipmentInventory");

        IRcvTransactionsDao rcvTransactionsDao = new RcvTransactionsDaoImpl();
        try {
            return rcvTransactionsDao.getAllByShipmentInventory(shipmentHeaderId, inventoryItemId);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public RcvTransactions getTransactionById(Long transactionId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getTransactionById");
        IRcvTransactionsDao rcvTransactionsDao = new RcvTransactionsDaoImpl();
        try {
            return rcvTransactionsDao.get(transactionId);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<Subinventario> getSubinventarios() throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getSubinventarios");

        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        try {
            return subinventarioDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public Localizador getLocalizadorByCodigo(String codigo) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getLocalizadorByCodigo");

        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        try {
            Localizador localizador = localizadorDao.getByCodigo(codigo);
            return localizador;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException {
        Log.d(TAG, "ConteoCiclicoService::getLocalizadoresBySubinventario");
        Log.d(TAG, "ConteoCiclicoService::getLocalizadoresBySubinventario::subinventarioCodigo: " + subinventarioCodigo);

        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        try {
            List<Localizador> salida = localizadorDao.getAllBySubinventario(subinventarioCodigo);
            return salida;
        } catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException(2, e.getDescripcion());
        }
    }

}
