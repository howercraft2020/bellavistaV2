package cl.clsoft.bave.dao.catalogo;

public class MtlCycleCountHeadersCatalogo {

    public static final String TABLE = "mtl_cycle_count_headers";
    public static final String COLUMN_ID = "cycle_count_header_id";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    public static final String COLUMN_LAST_UPDATED_BY = "last_updated_by";
    public static final String COLUMN_CREATION_DATE = "creation_date";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_CYCLE_COUNT_HEADER_NAME = "cycle_count_header_name";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_EMPLOYEE_ID = "employee_id";
    public static final String COLUMN_DESCRIPTION = "description";

    public static final String CREATE_TABLE =
            "CREATE TABLE "+ TABLE + "(" +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_ORGANIZATION_ID + " INTEGER, " +
                    COLUMN_LAST_UPDATE_DATE + " TEXT, " +
                    COLUMN_LAST_UPDATED_BY + " INTEGER, " +
                    COLUMN_CREATION_DATE + " TEXT, " +
                    COLUMN_CREATED_BY + " INTEGER, " +
                    COLUMN_CYCLE_COUNT_HEADER_NAME + " TEXT, " +
                    COLUMN_USER_ID + " INTEGER, " +
                    COLUMN_EMPLOYEE_ID + " INTEGER, " +
                    COLUMN_DESCRIPTION + " TEXT " +
                    ")";

    public static final String UPDATE = " WHERE " + COLUMN_ID + " = ?";
    public static final String DELETE = COLUMN_ID + " = ?";
    public static final String SELECT = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ? ";

}
