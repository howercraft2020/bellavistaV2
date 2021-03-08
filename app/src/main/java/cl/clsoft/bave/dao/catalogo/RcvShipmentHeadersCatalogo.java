package cl.clsoft.bave.dao.catalogo;

public class RcvShipmentHeadersCatalogo {

    public static final String TABLE = "rcv_shipment_headers";
    public static final String COLUMN_SHIPMENT_HEADER_ID = "shipment_header_id";
    public static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    public static final String COLUMN_LAST_UPDATED_BY = "last_updated_by";
    public static final String COLUMN_CREATION_DATE = "creation_date";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_VENDOR_ID = "vendor_id";
    public static final String COLUMN_VENDOR_SITE_ID = "vendor_site_id";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_SHIPMENT_NUM = "shipment_num";
    public static final String COLUMN_RECEIPT_NUM = "receipt_num";
    public static final String COLUMN_EMPLOYEE_ID = "employee_id";
    public static final String COLUMN_SHIP_TO_ORG_ID = "ship_to_org_id";
    public static final String COLUMN_PO_NUMBER = "po_number";
    public static final String COLUMN_HEADER_INTERFACE_ID = "headerInterfaceId";
    public static final String COLUMN_INTERFACE_TRANSACTION_ID = "interfaceTransactionId";
    public static final String COLUMN_GROUP_ID = "groupId";
    public static final String COLUMN_TRANSACTION_INTERFACE_ID = "transactionInterfaceId";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + " ( " +
                    COLUMN_SHIPMENT_HEADER_ID + " INTEGER, " +
                    COLUMN_LAST_UPDATE_DATE + " TEXT, " +
                    COLUMN_LAST_UPDATED_BY + " INTEGER, " +
                    COLUMN_CREATION_DATE + " TEXT, " +
                    COLUMN_CREATED_BY + " INTEGER, " +
                    COLUMN_USER_ID + " INTEGER, " +
                    COLUMN_VENDOR_ID + " INTEGER, " +
                    COLUMN_VENDOR_SITE_ID + " INTEGER, " +
                    COLUMN_ORGANIZATION_ID + " INTEGER, " +
                    COLUMN_SHIPMENT_NUM + " TEXT, " +
                    COLUMN_RECEIPT_NUM + " TEXT, " +
                    COLUMN_EMPLOYEE_ID + " INTEGER, " +
                    COLUMN_SHIP_TO_ORG_ID +" INTEGER, " +
                    COLUMN_PO_NUMBER +" INTEGER, " +
                    COLUMN_HEADER_INTERFACE_ID +" INTEGER, " +
                    COLUMN_INTERFACE_TRANSACTION_ID +" INTEGER, " +
                    COLUMN_GROUP_ID +" INTEGER, " +
                    COLUMN_TRANSACTION_INTERFACE_ID +" INTEGER, " +
                    " PRIMARY KEY (" + COLUMN_SHIPMENT_HEADER_ID + " )" +
                    ")";

    public static final String UPDATE = COLUMN_SHIPMENT_HEADER_ID + " = ?";

    public static final String DELETE = COLUMN_SHIPMENT_HEADER_ID + " = ?";

    public static final String SELECT =
            " SELECT * " +
            " FROM " +
            "     rcv_shipment_headers " +
            " WHERE " +
            "     shipment_header_id = ? ";

    public static final String SELECT_ALL =
            " SELECT * " +
            " FROM " +
            "     rcv_shipment_headers " +
            " ORDER BY " +
            "     shipment_header_id ";


}
