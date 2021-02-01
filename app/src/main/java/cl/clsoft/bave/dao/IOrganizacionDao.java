package cl.clsoft.bave.dao;

import cl.clsoft.bave.model.Organizacion;

public interface IOrganizacionDao {

    public void insert(Organizacion organizacion);
    public void update(Organizacion organizacion);
    public void delete(Long idOrganizacion);
    public Organizacion get(Long idOrganizacion);

}
