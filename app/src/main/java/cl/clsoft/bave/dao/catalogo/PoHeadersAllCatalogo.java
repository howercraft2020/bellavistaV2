package cl.clsoft.bave.dao.catalogo;

public class PoHeadersAllCatalogo {

    public static final String TABLE = "po_headers_all";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_CREATION_DATE = "creation_date";
    public static final String COLUMN_VENDOR_NAME = "vendor_name";
    public static final String COLUMN_VENDOR_ID = "vendor_id";
    public static final String COLUMN_VENDOR_SITE_ID = "vendor_site_id";
    public static final String COLUMN_VENDOR_SITE_CODE = "vendor_site_code";
    public static final String COLUMN_PO_HEADER_ID = "po_header_id";
    public static final String COLUMN_SEGMENT1 = "segment1";
    public static final String COLUMN_ORG_ID = "org_id";
    public static final String COLUMN_APPROVED_DATE = "approved_date";
    public static final String COLUMN_OPERATING_UNIT = "operating_unit";
    public static final String COLUMN_TERMS_ID = "terms_id";
    public static final String COLUMN_CURRENCY_CODE = "currency_code";
    public static final String COLUMN_RATE_TYPE = "rate_type";
    public static final String COLUMN_RATE_DATE = "rate_date";
    public static final String COLUMN_RATE = "rate";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_RECEIPT_NUM = "receipt_num";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + " ( " +
                    COLUMN_CREATED_BY + " INTEGER, " +
                    COLUMN_CREATION_DATE + " TEXT ," +
                    COLUMN_VENDOR_NAME + " TEXT, " +
                    COLUMN_VENDOR_ID + " INTEGER, " +
                    COLUMN_VENDOR_SITE_ID + " INTEGER, " +
                    COLUMN_VENDOR_SITE_CODE + " TEXT, " +
                    COLUMN_PO_HEADER_ID + " INTEGER, " +
                    COLUMN_SEGMENT1 + " TEXT, " +
                    COLUMN_ORG_ID + " INTEGER, " +
                    COLUMN_APPROVED_DATE + " TEXT, " +
                    COLUMN_OPERATING_UNIT + " TEXT, " +
                    COLUMN_TERMS_ID + " INTEGER, " +
                    COLUMN_CURRENCY_CODE + " TEXT, " +
                    COLUMN_RATE_TYPE + " TEXT, " +
                    COLUMN_RATE_DATE + " TEXT, " +
                    COLUMN_RATE + " TEXT, " +
                    COLUMN_USER_ID + " INTEGER, " +
                    COLUMN_RECEIPT_NUM +" INTEGER " +
            ")";

    public static final String SELECT_ALL = " SELECT * FROM " + TABLE;

}
