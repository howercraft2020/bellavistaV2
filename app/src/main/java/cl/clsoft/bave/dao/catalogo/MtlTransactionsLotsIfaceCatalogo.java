package cl.clsoft.bave.dao.catalogo;

public class MtlTransactionsLotsIfaceCatalogo {

    public static final String TABLE = "mtl_transactions_lots_iface";
    public static final String COLUMN_MTL_TRANSACTION_LOTS_IFACE = "transaction_interface_id";
    public static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    public static final String COLUMN_LAST_UPDATE_BY = "last_update_by";
    public static final String COLUMN_CREATION_DATE = "creation_date";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_PO_HEADER_ID = "po_header_id";
    public static final String COLUMN_PO_LINE_ID = "po_line_id";
    public static final String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    public static final String COLUMN_LAST_UPDATE_LOGIN = "last_update_login";
    public static final String COLUMN_LOT_NUMBER = "lot_number";
    public static final String COLUMN_TRANSACTION_QUANTITY = "transaction_quantity";
    public static final String COLUMN_PRIMARY_QUANTITY = "primary_quantity";
    public static final String COLUMN_SERIAL_TRANSACTION_TEMP_ID = "serial_transaction_temp_id";
    public static final String COLUMN_PRODUCT_CODE = "product_code";
    public static final String COLUMN_PRODUCT_TRANSACTION_ID = "product_transaction_id";
    public static final String COLUMN_SUPPLIER_LOT_NUMBER = "supplier_lot_number";
    public static final String COLUMN_LOT_EXPIRATION_DATE = "lot_expiration_date";
    public static final String COLUMN_ATTRIBUTE_CATEGORY = "attribute_category";
    public static final String COLUMN_ATTRIBUTE_1 = "attribute1";
    public static final String COLUMN_ATTRIBUTE_2 = "attribute2";
    public static final String COLUMN_ATTRIBUTE_3 = "attribute3";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + "( " +
                    COLUMN_MTL_TRANSACTION_LOTS_IFACE + " INTEGER, " +
                    COLUMN_LAST_UPDATE_DATE + " TEXT, " +
                    COLUMN_LAST_UPDATE_BY + " INTEGER, " +
                    COLUMN_CREATION_DATE + " TEXT, " +
                    COLUMN_CREATED_BY + " INTEGER, " +
                    COLUMN_PO_HEADER_ID + " INTEGER, " +
                    COLUMN_PO_LINE_ID + " INTEGER, " +
                    COLUMN_INVENTORY_ITEM_ID + " INTEGER, " +
                    COLUMN_LAST_UPDATE_LOGIN + " INTEGER, " +
                    COLUMN_LOT_NUMBER + " TEXT, " +
                    COLUMN_TRANSACTION_QUANTITY + " INTEGER, " +
                    COLUMN_PRIMARY_QUANTITY + " INTEGER, " +
                    COLUMN_SERIAL_TRANSACTION_TEMP_ID + " INTEGER, " +
                    COLUMN_PRODUCT_CODE + " TEXT, " +
                    COLUMN_PRODUCT_TRANSACTION_ID + " INTEGER, " +
                    COLUMN_SUPPLIER_LOT_NUMBER + " TEXT, " +
                    COLUMN_LOT_EXPIRATION_DATE + " TEXT, " +
                    COLUMN_ATTRIBUTE_CATEGORY + " TEXT, " +
                    COLUMN_ATTRIBUTE_1 + " TEXT, " +
                    COLUMN_ATTRIBUTE_2 + " TEXT, " +
                    COLUMN_ATTRIBUTE_3 +" TEXT " +
            ")";

    private static final String SELECT = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_MTL_TRANSACTION_LOTS_IFACE + " = ?";

    public static final String SELECT_ALL = "SELECT * from " + TABLE + " WHERE " + COLUMN_MTL_TRANSACTION_LOTS_IFACE + " = ?";

}
