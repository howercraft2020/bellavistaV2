package cl.clsoft.bave.dao.impl;

import java.util.List;

import cl.clsoft.bave.dao.IEntregaOrgsHeaderDao;
import cl.clsoft.bave.dao.catalogo.EntregaOrgsHeaderCatalogo;
import cl.clsoft.bave.dao.rowmapper.EntregaOrgsHeaderRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.EntregaOrgsHeader;

public class EntregaOrgsHeaderDaoImpl extends GenericDao<EntregaOrgsHeader> implements IEntregaOrgsHeaderDao {

    @Override
    public List<EntregaOrgsHeader> getAll() throws DaoException {
        return super.selectMany(EntregaOrgsHeaderCatalogo.SELECT_ALL, new EntregaOrgsHeaderRowMapper());
    }

    @Override
    public EntregaOrgsHeader get(Long shipmentHeaderId) throws DaoException {
        return super.selectOne(EntregaOrgsHeaderCatalogo.SELECT, new EntregaOrgsHeaderRowMapper(), shipmentHeaderId);
    }

}
