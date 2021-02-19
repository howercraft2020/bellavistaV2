package cl.clsoft.bave.dao.catalogo;

public class MtlCycleCountEntriesCatalogo {

    public static final String TABLE = "mtl_cycle_count_entries";
    public static final String COLUMN_ID = "cycle_count_entry_id";
    public static final String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    public static final String COLUMN_SUBINVENTORY = "subinventory";
    public static final String COLUMN_ENTRY_STATUS_CODE = "entry_status_code";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_CYCLE_COUNT_HEADER_ID = "cycle_count_header_id";
    public static final String COLUMN_LOCATOR_ID = "locator_id";
    public static final String COLUMN_LOT_NUMBER = "lot_number";
    public static final String COLUMN_SEGMENT1 = "segment1";
    public static final String COLUMN_PRIMARY_UOM_CODE = "primary_uom_code";
    public static final String COLUMN_SERIAL_NUMBER = "serial_number";

    public static final String CREATE_TABLE =
            "CREATE TABLE "+ TABLE + "(" +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_INVENTORY_ITEM_ID + " INTEGER, " +
                    COLUMN_SUBINVENTORY + " INTEGER, " +
                    COLUMN_ENTRY_STATUS_CODE + " TEXT, " +
                    COLUMN_ORGANIZATION_ID + " INTEGER, " +
                    COLUMN_CYCLE_COUNT_HEADER_ID + " TEXT, " +
                    COLUMN_LOCATOR_ID + " INTEGER, " +
                    COLUMN_LOT_NUMBER + " TEXT, " +
                    COLUMN_SEGMENT1 + " INTEGER, " +
                    COLUMN_PRIMARY_UOM_CODE + " INTEGER, " +
                    COLUMN_SERIAL_NUMBER + " TEXT " +
                    ")";

    public static final String UPDATE = COLUMN_ID + " = ?";
    public static final String DELETE = COLUMN_ID + " = ?";
    public static final String DELETE_BY_HEADER_ID = COLUMN_CYCLE_COUNT_HEADER_ID + " = ?";
    public static final String SELECT = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ? ";
    public static final String SELECT_ALL = " SELECT * FROM " + TABLE;
    public static final String SELECT_BY_HEADER_COUNT_HEADER_ID = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_CYCLE_COUNT_HEADER_ID + " = ?";

}