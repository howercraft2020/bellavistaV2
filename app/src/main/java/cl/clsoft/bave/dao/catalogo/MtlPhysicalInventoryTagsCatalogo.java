package cl.clsoft.bave.dao.catalogo;

public class MtlPhysicalInventoryTagsCatalogo {

    public static final String TABLE = "mtl_physical_inventory_tags";
    public static final String COLUMN_TAG_ID = "tag_id";
    public static final String COLUMN_PHYSICAL_INVENTORY_ID = "physical_inventory_id";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    public static final String COLUMN_SUBINVENTORY = "subinventory";
    public static final String COLUMN_LOCATOR_ID = "locator_id";
    public static final String COLUMN_LOT_NUMBER = "lot_number";
    public static final String COLUMN_LOT_EXPIRATION_DATE = "lot_expiration_date";
    public static final String COLUMN_SERIAL_NUM = "serial_num";
    public static final String COLUMN_SEGMENT1 = "segment1";
    public static final String COLUMN_PRIMARY_UOM_CODE = "primary_uom_code";

    public static final String CREATE_TABLE =
            "CREATE TABLE "+ TABLE + "(" +
                    COLUMN_TAG_ID + " INTEGER, " +
                    COLUMN_PHYSICAL_INVENTORY_ID + " INTEGER, " +
                    COLUMN_ORGANIZATION_ID + " INTEGER, " +
                    COLUMN_INVENTORY_ITEM_ID + " INTEGER, " +
                    COLUMN_SUBINVENTORY + " TEXT, " +
                    COLUMN_LOCATOR_ID + " INTEGER, " +
                    COLUMN_LOT_NUMBER + " TEXT, " +
                    COLUMN_LOT_EXPIRATION_DATE + " TEXT, " +
                    COLUMN_SERIAL_NUM + " TEXT, " +
                    COLUMN_SEGMENT1 + " TEXT, " +
                    COLUMN_PRIMARY_UOM_CODE + " TEXT " +
                    ")";

}
