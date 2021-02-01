package cl.clsoft.bave.dao.catalogo;

public class SubinventarioCatalogo {

    public static final String TABLE = "subinventario";
    public static final String COLUMN_ORG_ID = "organization_id";
    public static final String COLUMN_COD_SUB = "cod_subinventario";
    public static final String COLUMN_COD_LOC = "cod_localizador";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + " ( " +
                    COLUMN_ORG_ID + " INTEGER, " +
                    COLUMN_COD_SUB + " TEXT ," +
                    COLUMN_COD_LOC + " TEXT " +
                    " ) ";

}
