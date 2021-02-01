package cl.clsoft.bave.dao.catalogo;

public class OrganizacionCatalogo {

    public static final String TABLA = "organizacion";
    public static final String CAMPO_ID = "id_organizacion";
    public static final String CAMPO_CODE = "code";
    public static final String CREAR_TABLA = "CREATE TABLE "+ TABLA +" ("+CAMPO_ID+" INTEGER, "+CAMPO_CODE+" TEXT) ";
    public static final String UPDATE = " WHERE " + CAMPO_ID + " = ?";
    public static final String DELETE = " WHERE " + CAMPO_ID + " = ?";
    public static final String SELECT = " SELECT * FROM " + TABLA + " WHERE " + CAMPO_ID + " = ? ";

}
