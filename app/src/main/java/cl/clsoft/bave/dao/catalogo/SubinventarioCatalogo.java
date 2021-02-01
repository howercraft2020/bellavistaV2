package cl.clsoft.bave.dao.catalogo;

public class SubinventarioCatalogo {

    public static final String TABLA = "subinventario";
    public static final String CAMPO_ORG_ID_SUB = "organization_id";
    public static final String CAMPO_COD_SUB = "cod_subinventario";
    public static final String CAMPO_COD_LOC = "cod_localizador";
    public static final String CREAR_TABLA = "CREATE TABLE "+ TABLA +" ("+CAMPO_ORG_ID_SUB+" INTEGER, "+CAMPO_COD_SUB+" TEXT ,"+CAMPO_COD_LOC+" TEXT) ";

}
