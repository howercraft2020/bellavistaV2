package cl.clsoft.bave.dao.catalogo;

public class MtlPhysicalInventoriesCatalogo {

    public static final String TABLE = "mtl_physical_inventories";
    public static final String COLUMN_ID = "physical_inventory_id";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    public static final String COLUMN_LAST_UPDATED_BY = "last_updated_by";
    public static final String COLUMN_CREATION_DATE = "creation_date";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_PHYSICAL_INVENTORY_DATE = "physical_inventory_date";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FREEZE_DATE = "freeze_date";
    public static final String COLUMN_PHYSICAL_INVENTORY_NAME = "physical_inventory_name";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_EMPLOYEE_ID = "employee_id";
    public static final String COLUMN_APPROVAL_REQUIRED = "approval_required";
    public static final String COLUMN_ALL_SUBINVENTORIES_FLAG = "all_subinventories_flag";

    public static final String CREATE_TABLE =
            "CREATE TABLE "+ TABLE + "(" +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_ORGANIZATION_ID + " INTEGER, " +
                    COLUMN_LAST_UPDATE_DATE + " TEXT, " +
                    COLUMN_LAST_UPDATED_BY + " INTEGER, " +
                    COLUMN_CREATION_DATE + " TEXT, " +
                    COLUMN_CREATED_BY + " INTEGER, " +
                    COLUMN_PHYSICAL_INVENTORY_DATE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_FREEZE_DATE + " TEXT, " +
                    COLUMN_PHYSICAL_INVENTORY_NAME + " TEXT, " +
                    COLUMN_USER_ID + " INTEGER, " +
                    COLUMN_EMPLOYEE_ID + " INTEGER, " +
                    COLUMN_APPROVAL_REQUIRED + " INTEGER, " +
                    COLUMN_ALL_SUBINVENTORIES_FLAG + " INTEGER " +
                    ")";
    public static final String UPDATE = COLUMN_ID + " = ?";
    public static final String DELETE = COLUMN_ID + " = ?";
    public static final String SELECT = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ? ";
    public static final String SELECT_ALL = " SELECT * FROM " + TABLE;

}
