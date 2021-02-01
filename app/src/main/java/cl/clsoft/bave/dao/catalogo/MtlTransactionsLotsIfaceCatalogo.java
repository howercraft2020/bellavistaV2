package cl.clsoft.bave.dao.catalogo;

public class MtlTransactionsLotsIfaceCatalogo {

    public static final String TABLA = "mtl_transactions_lots_iface";
    public static final String CAMPO_MTL_TRANSACTION_LOTS_IFACE_MTL = "mtl_transaction_lots_iface";
    public static final String CAMPO_LAST_UPDATE_DATE_MTL = "last_update_date";
    public static final String CAMPO_LAST_UPDATE_BY_MTL = "last_update_by";
    public static final String CAMPO_CREATION_DATE_MTL = "creation_date";
    public static final String CAMPO_CREATED_BY_MTL = "created_by";
    public static final String CAMPO_PO_HEADER_ID_MTL = "po_header_id";
    public static final String CAMPO_PO_LINE_ID_MTL = "po_line_id";
    public static final String CAMPO_INVENTORY_ITEM_ID_MTL = "inventory_item_id";
    public static final String CAMPO_LAST_UPDATE_LOGIN_MTL = "last_update_login";
    public static final String CAMPO_LOT_NUMBER_MTL = "lot_number";
    public static final String CAMPO_TRANSACTION_QUANTITY_MTL = "transaction_quantity";
    public static final String CAMPO_PRIMARY_QUANTITY_MTL = "primary_quantity";
    public static final String CAMPO_SERIAL_TRANSACTION_TEMP_ID_MTL = "serial_transaction_temp_id";
    public static final String CAMPO_PRODUCT_CODE_MTL = "product_code";
    public static final String CAMPO_PRODUCT_TRANSACTION_ID_MTL = "product_transaction_id";
    public static final String CAMPO_SUPPLIER_LOT_NUMBER_MTL = "supplier_lot_number";
    public static final String CAMPO_LOT_EXPIRATION_DATE_MTL = "lot_expiration_date";
    public static final String CAMPO_ATTRIBUTE_CATEGORY_MTL = "attribute_category";
    public static final String CAMPO_ATTRIBUTE_1_MTL = "attribute1";
    public static final String CAMPO_ATTRIBUTE_2_MTL = "attribute2";
    public static final String CAMPO_ATTRIBUTE_3_MTL = "attribute3";
    public static final String CREAR_TABLA = "CREATE TABLE "+TABLA +"("+CAMPO_MTL_TRANSACTION_LOTS_IFACE_MTL+" INTEGER, "+CAMPO_LAST_UPDATE_DATE_MTL+" TEXT, "+CAMPO_LAST_UPDATE_BY_MTL+" INTEGER, "+CAMPO_CREATION_DATE_MTL+" TEXT, "+CAMPO_CREATED_BY_MTL+" INTEGER, "+CAMPO_PO_HEADER_ID_MTL+" INTEGER, "+CAMPO_PO_LINE_ID_MTL+" INTEGER, "+CAMPO_INVENTORY_ITEM_ID_MTL+" INTEGER, "+CAMPO_LAST_UPDATE_LOGIN_MTL+" INTEGER, "+CAMPO_LOT_NUMBER_MTL+" TEXT, "+CAMPO_TRANSACTION_QUANTITY_MTL+" INTEGER, "+CAMPO_PRIMARY_QUANTITY_MTL+" INTEGER, "+CAMPO_SERIAL_TRANSACTION_TEMP_ID_MTL+" INTEGER, "+CAMPO_PRODUCT_CODE_MTL+" TEXT, "+CAMPO_PRODUCT_TRANSACTION_ID_MTL+" INTEGER, "+CAMPO_SUPPLIER_LOT_NUMBER_MTL+" TEXT, "+CAMPO_LOT_EXPIRATION_DATE_MTL+" TEXT, "+CAMPO_ATTRIBUTE_CATEGORY_MTL+" TEXT, "+CAMPO_ATTRIBUTE_1_MTL+" TEXT, "+CAMPO_ATTRIBUTE_2_MTL+" TEXT, "+CAMPO_ATTRIBUTE_3_MTL+" TEXT) ";

}
