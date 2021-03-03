package cl.clsoft.bave.dao.catalogo;

public class RcvTransactionsCatalogo {

    public static final String TABLE = "rcv_transactions";
    public static final String COLUMN_TRANSACTION_ID = "transaction_id";
    public static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    public static final String COLUMN_LAST_UPDATED_BY = "last_updated_by";
    public static final String COLUMN_CREATION_DATE = "creation_date";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_TRANSACTION_TYPE = "transaction_type";
    public static final String COLUMN_TRANSACTION_DATE = "transaction_date";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_UNIT_OF_MEASURE = "unit_of_measure";
    public static final String COLUMN_SHIPMENT_HEADER_ID = "shipment_header_id";
    public static final String COLUMN_SHIPMENT_LINE_ID = "shipment_line_id";
    public static final String COLUMN_SOURCE_DOCUMENT_CODE = "source_document_code";
    public static final String COLUMN_DESTINATION_TYPE_CODE = "destination_type_code";
    public static final String COLUMN_PRIMARY_UNIT_OF_MEASURE = "primary_unit_of_measure";
    public static final String COLUMN_UOM_CODE = "uom_code";
    public static final String COLUMN_EMPLOYEE_ID = "employee_id";
    public static final String COLUMN_PO_HEADER_ID = "po_header_id";
    public static final String COLUMN_PO_LINE_ID = "po_line_id";
    public static final String COLUMN_PO_LINE_LOCATION_ID = "po_line_location_id";
    public static final String COLUMN_PO_DISTRIBUTION_ID = "po_distribution_id";
    public static final String COLUMN_PO_UNIT_PRICE = "po_unit_price";
    public static final String COLUMN_CURRENCY_CONVERSION_TYPE = "currency_conversion_type";
    public static final String COLUMN_CURRENCY_CONVERSION_RATE = "currency_conversion_rate";
    public static final String COLUMN_CURRENCY_CONVERSION_DATE = "currency_conversion_date";
    public static final String COLUMN_DELIVER_TO_LOCATION_ID = "deliver_to_location_id";
    public static final String COLUMN_VENDOR_ID = "vendor_id";
    public static final String COLUMN_VENDOR_SITE_ID = "vendor_site_id";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_LOCATION_ID = "location_id";
    public static final String COLUMN_INSPECTION_STATUS_CODE = "inspection_status_code";
    public static final String COLUMN_DESTINATION_CONTEXT = "destination_context";
    public static final String COLUMN_INTERFACE_TRANSACTION_ID = "interface_transaction_id";
    public static final String COLUMN_ITEM_ID = "item_id";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + " ( " +
                    COLUMN_TRANSACTION_ID + " INTEGER, " +
                    COLUMN_LAST_UPDATE_DATE + " TEXT, " +
                    COLUMN_LAST_UPDATED_BY + " INTEGER, " +
                    COLUMN_CREATION_DATE + " TEXT, " +
                    COLUMN_CREATED_BY + " INTEGER, " +
                    COLUMN_TRANSACTION_TYPE + " TEXT, " +
                    COLUMN_TRANSACTION_DATE + " TEXT, " +
                    COLUMN_QUANTITY + " INTEGER, " +
                    COLUMN_UNIT_OF_MEASURE + " TEXT, " +
                    COLUMN_SHIPMENT_HEADER_ID + " INTEGER, " +
                    COLUMN_SHIPMENT_LINE_ID + " INTEGER, " +
                    COLUMN_SOURCE_DOCUMENT_CODE + " TEXT, " +
                    COLUMN_DESTINATION_TYPE_CODE + " TEXT, " +
                    COLUMN_PRIMARY_UNIT_OF_MEASURE + " TEXT, " +
                    COLUMN_UOM_CODE + " TEXT, " +
                    COLUMN_EMPLOYEE_ID + " INTEGER, " +
                    COLUMN_PO_HEADER_ID + " INTEGER, " +
                    COLUMN_PO_LINE_ID + " INTEGER, " +
                    COLUMN_PO_LINE_LOCATION_ID + " INTEGER, " +
                    COLUMN_PO_DISTRIBUTION_ID + " INTEGER, " +
                    COLUMN_PO_UNIT_PRICE + " INTEGER, " +
                    COLUMN_CURRENCY_CONVERSION_TYPE + " TEXT, " +
                    COLUMN_CURRENCY_CONVERSION_RATE + " INTEGER, " +
                    COLUMN_CURRENCY_CONVERSION_DATE + " TEXT, " +
                    COLUMN_DELIVER_TO_LOCATION_ID + " INTEGER, " +
                    COLUMN_VENDOR_ID + " INTEGER, " +
                    COLUMN_VENDOR_SITE_ID + " INTEGER, " +
                    COLUMN_ORGANIZATION_ID + " INTEGER, " +
                    COLUMN_LOCATION_ID + " INTEGER, " +
                    COLUMN_INSPECTION_STATUS_CODE + " TEXT, " +
                    COLUMN_DESTINATION_CONTEXT + " TEXT, " +
                    COLUMN_INTERFACE_TRANSACTION_ID + " INTEGER, " +
                    COLUMN_ITEM_ID +" INTEGER, " +
                    " PRIMARY KEY (" + COLUMN_TRANSACTION_ID + " )" +
                    " ) ";

    public static final String UPDATE = COLUMN_TRANSACTION_ID + " = ?";

}
