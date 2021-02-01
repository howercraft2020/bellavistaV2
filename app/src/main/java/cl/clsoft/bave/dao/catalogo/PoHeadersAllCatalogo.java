package cl.clsoft.bave.dao.catalogo;

public class PoHeadersAllCatalogo {

    public static final String TABLA = "po_headers_all";
    public static final String CAMPO_CREATED_BY_PHA = "created_by";
    public static final String CAMPO_CREATION_DATE_PHA = "creation_date";
    public static final String CAMPO_VENDOR_NAME_PHA = "vendor_name";
    public static final String CAMPO_VENDOR_ID_PHA = "vendor_id";
    public static final String CAMPO_VENDOR_SITE_ID_PHA = "vendor_site_id";
    public static final String CAMPO_VENDOR_SITE_CODE_PHA = "vendor_site_code";
    public static final String CAMPO_PO_HEADER_ID_PHA = "po_header_id";
    public static final String CAMPO_SEGMENT1_PHA = "segment1";
    public static final String CAMPO_ORG_ID_PHA = "org_id";
    public static final String CAMPO_APPROVED_DATE_PHA = "approved_date";
    public static final String CAMPO_OPERATING_UNIT_PHA = "operating_unit";
    public static final String CAMPO_TERMS_ID_PHA = "terms_id";
    public static final String CAMPO_CURRENCY_CODE_PHA = "currency_code";
    public static final String CAMPO_RATE_TYPE_PHA = "rate_type";
    public static final String CAMPO_RATE_DATE_PHA = "rate_date";
    public static final String CAMPO_RATE_PHA = "rate";
    public static final String CAMPO_USER_ID_PHA = "user_id";
    public static final String CAMPO_RECEIPT_NUM_PHA = "receipt_num";
    public static final String CREAR_TABLA = "CREATE TABLE "+ TABLA +" ("+CAMPO_CREATED_BY_PHA+" INTEGER, "+CAMPO_CREATION_DATE_PHA+" TEXT ,"+CAMPO_VENDOR_NAME_PHA+" TEXT, "+CAMPO_VENDOR_ID_PHA+" INTEGER, "+CAMPO_VENDOR_SITE_ID_PHA+" INTEGER, "+CAMPO_VENDOR_SITE_CODE_PHA+" TEXT, "+CAMPO_PO_HEADER_ID_PHA+" INTEGER, "+CAMPO_SEGMENT1_PHA+" TEXT, "+CAMPO_ORG_ID_PHA+" INTEGER, "+ CAMPO_APPROVED_DATE_PHA+" TEXT, "+CAMPO_OPERATING_UNIT_PHA+" TEXT, "+CAMPO_TERMS_ID_PHA+" INTEGER, "+CAMPO_CURRENCY_CODE_PHA+" TEXT, "+CAMPO_RATE_TYPE_PHA+" TEXT, "+CAMPO_RATE_DATE_PHA+" TEXT, "+CAMPO_RATE_PHA+" TEXT, "+CAMPO_USER_ID_PHA+" INTEGER, "+CAMPO_RECEIPT_NUM_PHA+" TEXT)";

}
