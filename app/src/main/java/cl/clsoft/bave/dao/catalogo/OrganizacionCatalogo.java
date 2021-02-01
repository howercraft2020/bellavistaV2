package cl.clsoft.bave.dao.catalogo;

public class OrganizacionCatalogo {

    public static final String TABLE = "organizacion";
    public static final String COLUMN_ID = "id_organizacion";
    public static final String COLUMN_CODE = "code";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ( " +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_CODE + " TEXT " +
            ")";
    public static final String UPDATE = " WHERE " + COLUMN_ID + " = ?";
    public static final String DELETE = " WHERE " + COLUMN_ID + " = ?";
    public static final String SELECT = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ? ";

}
