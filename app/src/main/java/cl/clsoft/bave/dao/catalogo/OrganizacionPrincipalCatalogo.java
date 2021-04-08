package cl.clsoft.bave.dao.catalogo;

public class OrganizacionPrincipalCatalogo {

    public static final String TABLE = "organizacion_principal";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_NOMBRE_CORTO = "nombre_corto";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_ID = "id_organizacion";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ( " +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_CODE + " TEXT, " +
                    COLUMN_NOMBRE + " TEXT, " +
                    COLUMN_NOMBRE_CORTO + " TEXT, " +
                    " PRIMARY KEY (" + COLUMN_ID + " )" +
                    ")";

    public static final String UPDATE = COLUMN_ID + " = ?";
    public static final String SELECT = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ? ";
    public static final String SELECT_ALL = " SELECT * FROM " + TABLE;
}
