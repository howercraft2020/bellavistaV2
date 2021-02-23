package cl.clsoft.bave.dao.catalogo;

public class MtlSystemItemsCatalogo {

    public static final String TABLE = "mtl_system_items";
    public static final String COLUMN_ID = "inventory_item_id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LONG_DESCRIPTION = "long_description";
    public static final String COLUMN_SEGMENT1 = "segment1";
    public static final String COLUMN_PRIMARY_UOM_CODE = "primary_uom_code";
    public static final String COLUMN_LOT_CONTROL_CODE = "lot_control_code";
    public static final String COLUMN_SHELF_LIFE_CODE = "shelf_life_code";
    public static final String COLUMN_SERIAL_NUMBER_CONTROL_CODE = "serial_number_control_code";

    public static final String CREATE_TABLE =
            "CREATE TABLE "+ TABLE + "(" +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_LONG_DESCRIPTION + " TEXT, " +
                    COLUMN_SEGMENT1 + " TEXT, " +
                    COLUMN_PRIMARY_UOM_CODE + " TEXT, " +
                    COLUMN_LOT_CONTROL_CODE + " TEXT, " +
                    COLUMN_SHELF_LIFE_CODE + " TEXT, " +
                    COLUMN_SERIAL_NUMBER_CONTROL_CODE + " TEXT " +
                    ")";
    public static final String UPDATE = " WHERE " + COLUMN_ID + " = ?";
    public static final String DELETE = " WHERE " + COLUMN_ID + " = ?";
    public static final String SELECT = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ? ";
    public static final String SELECT_BY_SEGMENT =
            " SELECT * " +
            " FROM " +
            "     mtl_system_items " +
            " WHERE " +
            "     segment1 = ? ";

    public static final String SELECT_ALL = "SELECT * FROM " + TABLE;


}
