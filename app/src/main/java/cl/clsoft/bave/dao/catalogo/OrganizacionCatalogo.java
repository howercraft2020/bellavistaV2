package cl.clsoft.bave.dao.catalogo;

public class OrganizacionCatalogo {

    public static final String TABLE = "organizacion";
    public static final String COLUMN_ID = "id_organizacion";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_TRANSFERENCIA = "transferencia";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ( " +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_CODE + " TEXT, " +
                    COLUMN_TRANSFERENCIA + " TEXT, " +
                    " PRIMARY KEY (" + COLUMN_ID + ", " + COLUMN_CODE + ", " + COLUMN_TRANSFERENCIA + " )" +
            ")";
    public static final String UPDATE = " WHERE " + COLUMN_ID + " = ?";
    public static final String DELETE = " WHERE " + COLUMN_ID + " = ?";
    public static final String SELECT = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ? AND " + COLUMN_CODE + " = ? AND " + COLUMN_TRANSFERENCIA + " = ?";
    public static final String SELECT_HACIA = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_TRANSFERENCIA + " = 'HACIA' ";
    public static final String SELECT_BY_CODE =  " SELECT * FROM " + TABLE + " WHERE " + COLUMN_CODE + " = ? AND " + COLUMN_TRANSFERENCIA + " = 'HACIA' ";
    public static final String SELECT_BY_ID =  " SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ? AND " + COLUMN_TRANSFERENCIA + " = 'HACIA' ";

}
