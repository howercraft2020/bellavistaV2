package cl.clsoft.bave.dao.catalogo;

public class LocalizadorCatalogo {

    public static final String TABLA = "localizador";
    public static final String CAMPO_ID_LOC = "id_localizador";
    public static final String CAMPO_ORG_ID_LOC = "organization_id";
    public static final String CAMPO_COD_SUBINV_LOC = "cod_subinventario";
    public static final String CAMPO_COD_LOC_LOC = "cod_localizador";
    public static final String CAMPO_COD_SEG1_LOC = "cod_seg1";
    public static final String CAMPO_COD_SEG2_LOC = "cod_seg2";
    public static final String CAMPO_COD_SEG3_LOC = "cod_seg3";
    public static final String CAMPO_COD_SEG4_LOC = "cod_seg4";
    public static final String CAMPO_COD_SEG5_LOC = "cod_seg5";
    public static final String CAMPO_COD_SEG6_LOC = "cod_seg6";
    public static final String CREAR_TABLA="CREATE TABLE "+ TABLA +" ("+CAMPO_ID_LOC+" INTEGER, "+CAMPO_ORG_ID_LOC+" INTEGER, "+CAMPO_COD_SUBINV_LOC+" TEXT, "+CAMPO_COD_LOC_LOC+" TEXT, "+ CAMPO_COD_SEG1_LOC+" TEXT, "+CAMPO_COD_SEG2_LOC+" TEXT, "+CAMPO_COD_SEG3_LOC+" TEXT, "+CAMPO_COD_SEG4_LOC+" TEXT, "+CAMPO_COD_SEG5_LOC+" TEXT, "+CAMPO_COD_SEG6_LOC+" TEXT) ";

}
