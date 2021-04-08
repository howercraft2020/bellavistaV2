package cl.clsoft.bave.dao.catalogo;

public class SubinventarioCatalogo {

    public static final String TABLE = "subinventario";
    public static final String COLUMN_ORG_ID = "organization_id";
    public static final String COLUMN_COD_SUB = "cod_subinventario";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_COD_LOC = "cod_localizador";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + " ( " +
            COLUMN_ORG_ID + " INTEGER, " +
            COLUMN_COD_SUB + " TEXT," +
            COLUMN_DESCRIPTION + " TEXT, " +
            COLUMN_COD_LOC + " TEXT, " +
            " PRIMARY KEY (" + COLUMN_ORG_ID + "," + COLUMN_COD_SUB + " )" +
            " ) ";

    public static final String UPDATE = COLUMN_ORG_ID + " = ? AND " + COLUMN_COD_SUB + " = ? ";

    public static final String SELECT =
            " SELECT * " +
            " FROM " +
            "     subinventario " +
            " WHERE " +
            "     organization_id = ? " +
            "     AND cod_subinventario = ? ";

    public static final String SELECT_BY_CODIGO =
            " SELECT * " +
            " FROM " +
            "     subinventario " +
            " WHERE " +
            "     cod_subinventario = ? ";

    public static final String SELECT_ALL =
            " SELECT * " +
            " FROM " +
            "     subinventario " +
            " ORDER BY " +
            "     cod_subinventario ";

    public static final String SELECT_ALL_BY_CICLICOID =
            " SELECT * " +
            " FROM " +
            "     subinventario " +
            " WHERE " +
            "     cod_subinventario in (SELECT DISTINCT(subinventory) FROM mtl_cycle_count_entries WHERE cycle_count_header_id = ?)";

}
