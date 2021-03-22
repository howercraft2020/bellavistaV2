package cl.clsoft.bave.dao.catalogo;

public class MtlTransactionLotNumbersCatalogo {

    public static final String TABLE = "mtl_transaction_lot_numbers";
    public static final String COLUMN_ID = "transaction_id";
    public static final String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_LOT_NUMBER = "lot_number";
    public static final String COLUMN_SERIAL_TRANSACTION_ID = "serial_transaction_id";
    public static final String COLUMN_LOT_ATTRIBUTE_CATEGORY = "lot_attribute_category";
    public static final String COLUMN_C_ATTRIBUTE1 = "c_attribute1";
    public static final String COLUMN_C_ATTRIBUTE2 = "c_attribute2";
    public static final String COLUMN_C_ATTRIBUTE3 = "c_attribute3";
    public static final String COLUMN_SHIPMENT_HEADER_ID = "shipment_header_id";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + "(" +
            COLUMN_ID + " INTEGER, " +
            COLUMN_INVENTORY_ITEM_ID + " INTEGER, " +
            COLUMN_ORGANIZATION_ID + " INTEGER, " +
            COLUMN_LOT_NUMBER + " TEXT, " +
            COLUMN_SERIAL_TRANSACTION_ID + " INTEGER, " +
            COLUMN_LOT_ATTRIBUTE_CATEGORY + " TEXT, " +
            COLUMN_C_ATTRIBUTE1 + " TEXT, " +
            COLUMN_C_ATTRIBUTE2 + " TEXT, " +
            COLUMN_C_ATTRIBUTE3 + " TEXT, " +
            COLUMN_SHIPMENT_HEADER_ID + " INTEGER, " +
            " PRIMARY KEY (" + COLUMN_ID + " )" +
            ")";

    public static final String UPDATE = COLUMN_ID + " = ?";
    public static final String DELETE = COLUMN_ID + " = ?";
    public static final String SELECT =
            " SELECT * "
            + " FROM "
            + "     mtl_transaction_lot_numbers "
            + " WHERE "
            + "     transaction_id = ? ";

}
