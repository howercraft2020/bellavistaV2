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
    public static final String UPDATE = COLUMN_ID + " = ?";
    public static final String DELETE = COLUMN_ID + " = ?";
    public static final String SELECT = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ? ";

    public static final String SELECT_BY_SEGMENT =
            " SELECT * " +
            " FROM " +
            "     mtl_system_items " +
            " WHERE " +
            "     segment1 = ? ";

    public static final String SELECT_ALL = "SELECT * FROM " + TABLE;
    public static final String SELECT_ALL_BY_DESCRIPTION =
            " SELECT * " +
            " FROM " +
            "     mtl_system_items " +
            " WHERE " +
            "     description || long_description like ? ";

    public static final String SELECT_ALL_BY_DESCRIPTION_SHIPMENT =
            " SELECT * " +
            " FROM " +
            "     mtl_system_items " +
            " WHERE " +
            "     description || long_description like ? " +
            "     AND inventory_item_id in (SELECT distinct(item_id) FROM rcv_transactions WHERE shipment_header_id = ?)";

    public static final String SELECT_ALL_BY_DESCRIPTION_SHIPMENT_ORGANIZACION =
            " SELECT * " +
                    " FROM " +
                    "     mtl_system_items " +
                    " WHERE " +
                    "     description || long_description like ? " +
                    "     AND inventory_item_id in (SELECT distinct(inventory_item_id) FROM mtl_material_transactions WHERE shipment_header_id = ?)";

    public static final String SELECT_ALL_BY_DESCRIPTION_COUNTEHEADER_LOCATOR =
            " SELECT * " +
                    " FROM " +
                    "     mtl_system_items " +
                    " WHERE " +
                    "     description || long_description like ? " +
                    "     AND inventory_item_id in (SELECT distinct(inventory_item_id) FROM mtl_cycle_count_entries WHERE cycle_count_header_id = ? and locator_id = ? )";

    public static final String SELECT_ALL_BY_DESCRIPTION_INVENTORY_LOCATOR =
            " SELECT * " +
                    " FROM " +
                    "     mtl_system_items " +
                    " WHERE " +
                    "     description || long_description like ? " +
                    "     AND inventory_item_id in (SELECT distinct(inventory_item_id) FROM mtl_physical_inventory_tags WHERE physical_inventory_id = ? and locator_id = ? )";

    public static final String SELECT_BY_OC_RECEIPT =
            "SELECT msi.* FROM PO_HEADERS_ALL pha, " +
            "PO_LINES_ALL pla, MTL_SYSTEM_ITEMS msi " +
            "WHERE pha.PO_HEADER_ID = pla.PO_HEADER_ID " +
            "AND pla.ITEM_ID = msi.INVENTORY_ITEM_ID " +
            "AND pha.PO_HEADER_ID = ? " +
            "AND pha.RECEIPT_NUM = ? ";

}
