package cl.clsoft.bave.dao.catalogo;

public class RcvHeadersInterfaceCatalogo {

    public static final String TABLE = "rcv_headers_interface";
    public static final String COLUMN_HEADER_INTERFACE_ID = "header_interface_id";
    public static final String COLUMN_RECEIPT_SOURCE_CODE = "receipt_source_code";
    public static final String COLUMN_TRANSACTION_TYPE = "transaction_type";
    public static final String COLUMN_AUTO_TRANSACT_CODE = "autoTransactCode";
    public static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    public static final String COLUMN_LAST_UPDATED_BY = "last_updated_by";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_RECEIPT_NUM = "receipt_num";
    public static final String COLUMN_VENDOR_ID = "vendor_id";
    public static final String COLUMN_VENDOR_SITE_CODE = "vendor_site_code";
    public static final String COLUMN_VENDOR_SITE_ID = "vendor_site_id";
    public static final String COLUMN_SHIP_TO_ORGANIZATION_CODE = "ship_to_organization_code";
    public static final String COLUMN_LOCATION_ID = "location_id";
    public static final String COLUMN_RECEIVER_ID = "receiver_id";
    public static final String COLUMN_CURRENCY_CODE = "currency_code";
    public static final String COLUMN_CONVERSION_RATE_TYPE = "conversion_rate_type";
    public static final String COLUMN_CONVERSION_RATE = "conversion_rate";
    public static final String COLUMN_CONVERSION_RATE_DATE = "conversion_rate_date";
    public static final String COLUMN_PAYMENT_TERMS_ID = "payment_terms_id";
    public static final String COLUMN_TRANSACTION_DATE = "transaction_date";
    public static final String COLUMN_COMMENTS = "comments";
    public static final String COLUMN_ORG_ID = "org_id";
    public static final String COLUMN_PROCESSING_STATUS_CODE = "processing_status_code";
    public static final String COLUMN_VALIDATION_FLAG = "validation_flag";
    public static final String COLUMN_GROUP_ID = "group_id";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ( " +
                    COLUMN_HEADER_INTERFACE_ID + " TEXT, " +
                    COLUMN_RECEIPT_SOURCE_CODE + " TEXT, " +
                    COLUMN_TRANSACTION_TYPE + " TEXT, " +
                    COLUMN_AUTO_TRANSACT_CODE + " TEXT, " +
                    COLUMN_LAST_UPDATE_DATE + " TEXT, " +
                    COLUMN_LAST_UPDATED_BY + " INTEGER, " +
                    COLUMN_CREATED_BY + " INTEGER, " +
                    COLUMN_RECEIPT_NUM + " TEXT, " +
                    COLUMN_VENDOR_ID + " INTEGER, " +
                    COLUMN_VENDOR_SITE_CODE + " TEXT, " +
                    COLUMN_VENDOR_SITE_ID + " INTEGER, " +
                    COLUMN_SHIP_TO_ORGANIZATION_CODE + " TEXT, " +
                    COLUMN_LOCATION_ID + " INTEGER, " +
                    COLUMN_RECEIVER_ID + " INTEGER, " +
                    COLUMN_CURRENCY_CODE + " TEXT, " +
                    COLUMN_CONVERSION_RATE_TYPE + " TEXT, " +
                    COLUMN_CONVERSION_RATE + " INTEGER, " +
                    COLUMN_CONVERSION_RATE_DATE + " TEXT, " +
                    COLUMN_PAYMENT_TERMS_ID + " INTEGER, " +
                    COLUMN_TRANSACTION_DATE + " TEXT, " +
                    COLUMN_COMMENTS + " TEXT, " +
                    COLUMN_ORG_ID + " INTEGER, " +
                    COLUMN_PROCESSING_STATUS_CODE + " TEXT, " +
                    COLUMN_VALIDATION_FLAG + " TEXT, " +
                    COLUMN_GROUP_ID +" INTEGER " +
                    " ) ";

}
