package cl.clsoft.bave.dao.catalogo;

public class LocalizadorCatalogo {

    public static final String TABLE = "localizador";
    public static final String COLUMN_ID = "id_localizador";
    public static final String COLUMN_ORG_ID = "organization_id";
    public static final String COLUMN_COD_SUBINV = "cod_subinventario";
    public static final String COLUMN_COD_LOC = "cod_localizador";
    public static final String COLUMN_COD_SEG1 = "cod_seg1";
    public static final String COLUMN_COD_SEG2 = "cod_seg2";
    public static final String COLUMN_COD_SEG3 = "cod_seg3";
    public static final String COLUMN_COD_SEG4 = "cod_seg4";
    public static final String COLUMN_COD_SEG5 = "cod_seg5";
    public static final String COLUMN_COD_SEG6 = "cod_seg6";

    public static final String CREATE_TABLE =
            "CREATE TABLE "+ TABLE +" ( " +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_ORG_ID + " INTEGER, " +
                    COLUMN_COD_SUBINV + " TEXT, " +
                    COLUMN_COD_LOC + " TEXT, " +
                    COLUMN_COD_SEG1 + " TEXT, " +
                    COLUMN_COD_SEG2 + " TEXT, " +
                    COLUMN_COD_SEG3 + " TEXT, " +
                    COLUMN_COD_SEG4 + " TEXT, " +
                    COLUMN_COD_SEG5 + " TEXT, " +
                    COLUMN_COD_SEG6 + " TEXT " +
            ")";
    public static final String UPDATE = " WHERE " + COLUMN_ID + " = ?";
    public static final String DELETE = " WHERE " + COLUMN_ID + " = ?";
    public static final String SELECT = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ? ";

}
