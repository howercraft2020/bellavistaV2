package cl.clsoft.bave.dao.catalogo;

public class MtlMaterialTransactionsCatalogo {

    public static final String TABLE = "mtl_material_transactions";
    public static final String COLUMN_ID = "transaction_id";
    public static final String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_TRANSACTION_TYPE_ID = "transaction_type_id";
    public static final String COLUMN_TRANSACTION_ACTION_ID = "transaction_action_id";
    public static final String COLUMN_TRANSACTION_SOURCE_TYPE_ID = "transaction_source_type_id";
    public static final String COLUMN_TRANSACTION_SOURCE_NAME = "transaction_source_name";
    public static final String COLUMN_TRANSACTION_QUANTITY = "transaction_quantity";
    public static final String COLUMN_TRANSACTION_UOM = "transaction_uom";
    public static final String COLUMN_PRIMARY_QUANTITY = "primary_quantity";
    public static final String COLUMN_TRANSACTION_DATE = "transaction_date";
    public static final String COLUMN_ACTUAL_COST = "actual_cost";
    public static final String COLUMN_TRANSFER_ORGANIZATION_ID = "transfer_organization_id";
    public static final String COLUMN_SHIP_TO_LOCATION_ID = "ship_to_location_id";
    public static final String COLUMN_RECEIPT_NUM = "receipt_num";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_SHIPMENT_NUMBER = "shipment_number";
    public static final String COLUMN_SHIPMENT_HEADER_ID = "shipment_header_id";
    public static final String COLUMN_SHIPMENT_LINE_ID = "shipment_line_id";
    public static final String COLUMN_ORG_ID = "org_id";
    public static final String COLUMN_ENTREGA_CREATION_DATE = "entrega_creation_date";
    public static final String COLUMN_ENTREGA_QUANTITY = "entrega_quantity";
    public static final String COLUMN_SUBINVENTORY ="subinventory";
    public static final String COLUMN_LOCATOR_ID ="locator_id";
    public static final String COLUMN_USE_MTL_LOT = "use_mtl_lot";
    public static final String COLUMN_USE_MTL_SERIAL = "use_mtl_serial";
    public static final String COLUMN_HEADER_INTERFACE_ID = "headerInterfaceId";
    public static final String COLUMN_INTERFACE_TRANSACTION_ID = "interfaceTransactionId";
    public static final String COLUMN_GROUP_ID = "groupId";
    public static final String COLUMN_TRANSACTION_INTERFACE_ID = "transactionInterfaceId";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + "(" +
            COLUMN_ID + " INTEGER, " +
            COLUMN_INVENTORY_ITEM_ID + " INTEGER, " +
            COLUMN_ORGANIZATION_ID + " INTEGER, " +
            COLUMN_TRANSACTION_TYPE_ID + " INTEGER, " +
            COLUMN_TRANSACTION_ACTION_ID + " INTEGER, " +
            COLUMN_TRANSACTION_SOURCE_TYPE_ID + " INTEGER, " +
            COLUMN_TRANSACTION_SOURCE_NAME + " TEXT, " +
            COLUMN_TRANSACTION_QUANTITY + " REAL, " +
            COLUMN_TRANSACTION_UOM + " TEXT, " +
            COLUMN_PRIMARY_QUANTITY + " REAL, " +
            COLUMN_TRANSACTION_DATE + " TEXT, " +
            COLUMN_ACTUAL_COST + " INTEGER, " +
            COLUMN_TRANSFER_ORGANIZATION_ID + " INTEGER, " +
            COLUMN_SHIP_TO_LOCATION_ID + " INTEGER, " +
            COLUMN_RECEIPT_NUM + " TEXT, " +
            COLUMN_USER_ID + " INTEGER, " +
            COLUMN_SHIPMENT_NUMBER + " TEXT, " +
            COLUMN_SHIPMENT_HEADER_ID + " INTEGER, " +
            COLUMN_SHIPMENT_LINE_ID + " INTEGER, " +
            COLUMN_ORG_ID + " INTEGER, " +
            COLUMN_HEADER_INTERFACE_ID + " INTEGER, " +
            COLUMN_GROUP_ID + " INTEGER, " +
            COLUMN_INTERFACE_TRANSACTION_ID + " INTEGER, " +
            COLUMN_TRANSACTION_INTERFACE_ID + " INTEGER, " +
            COLUMN_ENTREGA_CREATION_DATE + " TEXT, " +
            COLUMN_ENTREGA_QUANTITY + " REAL, " +
            COLUMN_SUBINVENTORY + " TEXT, " +
            COLUMN_LOCATOR_ID + " INTEGER, " +
            COLUMN_USE_MTL_LOT + " INTEGER, " +
            COLUMN_USE_MTL_SERIAL + " INTEGER, " +
            " PRIMARY KEY (" + COLUMN_ID + " )" +
            ")";

    public static final String UPDATE = COLUMN_ID + " = ?";
    public static final String DELETE = COLUMN_ID + " = ?";
    public static final String DELETE_BY_SHIPMENT_HEADER_ID = COLUMN_SHIPMENT_HEADER_ID + " = ?";
    public static  final String SELECT =
            " SELECT * " +
            " FROM " +
            TABLE +
            " WHERE " +
            COLUMN_ID + " = ? ";
    public static  final String SELECT_ALL_BY_SHIPMENT_HEADER_ID =
            " SELECT * " +
            " FROM " +
            TABLE +
            " WHERE " +
            COLUMN_SHIPMENT_HEADER_ID + " = ? ";
    public static  final String SELECT_ALL_BY_SHIPMENT_HEADER_ID__INVENTORY_ITEM_ID =
            " SELECT * " +
            " FROM " +
            TABLE +
            " WHERE " +
            COLUMN_SHIPMENT_HEADER_ID + " = ? " +
            " AND " + COLUMN_INVENTORY_ITEM_ID + " = ? ";

    public static  final String SELECT_ALL_BY_SHIPMENT_HEADER_ID__ENTREGA =
            " SELECT * " +
                    " FROM " +
                    TABLE +
                    " WHERE " +
                    COLUMN_SHIPMENT_HEADER_ID + " = ? " +
                    " AND " + COLUMN_ENTREGA_CREATION_DATE + " is not null ";

    public static final String SELECT_SEGMENT_BY_SHIPMENT =
            " SELECT " +
            "     segment1 " +
            " FROM " +
            "     mtl_system_items " +
            " WHERE " +
            "     inventory_item_id in ( SELECT distinct(inventory_item_id) FROM mtl_material_transactions WHERE shipment_header_id = ?) " +
            " ORDER BY " +
            "     segment1 ";

}
