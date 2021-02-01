package cl.clsoft.bave.dao.catalogo;

public class RcvHeadersInterfaceCatalogo {

    public static final String TABLA = "rcv_headers_interface";
    public static final String CAMPO_HEADER_INTERFACE_ID_RHI = "header_interface_id";
    public static final String CAMPO_RECEIPT_SOURCE_CODE_RHI = "receipt_source_code";
    public static final String CAMPO_TRANSACTION_TYPE_RHI = "transaction_type";
    public static final String CAMPO_LAST_UPDATE_DATE_RHI = "last_update_date";
    public static final String CAMPO_LAST_UPDATED_BY_RHI = "last_updated_by";
    public static final String CAMPO_CREATED_BY_RHI = "created_by";
    public static final String CAMPO_RECEIPT_NUM_RHI = "receipt_num";
    public static final String CAMPO_VENDOR_ID_RHI = "vendor_id";
    public static final String CAMPO_VENDOR_SITE_CODE_RHI = "vendor_site_code";
    public static final String CAMPO_VENDOR_SITE_ID_RHI = "vendor_site_id";
    public static final String CAMPO_SHIP_TO_ORGANIZATION_CODE_RHI = "ship_to_organization_code";
    public static final String CAMPO_LOCATION_ID_RHI = "location_id";
    public static final String CAMPO_RECEIVER_ID_RHI = "receiver_id";
    public static final String CAMPO_CURRENCY_CODE_RHI = "currency_code";
    public static final String CAMPO_CONVERSION_RATE_TYPE_RHI = "conversion_rate_type";
    public static final String CAMPO_CONVERSION_RATE_RHI = "conversion_rate";
    public static final String CAMPO_CONVERSION_RATE_DATE_RHI = "conversion_rate_date";
    public static final String CAMPO_PAYMENT_TERMS_ID_RHI = "payment_terms_id";
    public static final String CAMPO_TRANSACTION_DATE_RHI = "transaction_date";
    public static final String CAMPO_COMMENTS_RHI = "comments";
    public static final String CAMPO_ORG_ID_RHI = "org_id";
    public static final String CAMPO_PROCESSING_STATUS_CODE_RHI = "processing_status_code";
    public static final String CAMPO_VALIDATION_FLAG_RHI = "validation_flag";
    public static final String CAMPO_GROUP_ID_RHI = "group_id";
    public static final String CREAR_TABLA = "CREATE TABLE "+TABLA +"("+CAMPO_HEADER_INTERFACE_ID_RHI+" TEXT, "+CAMPO_RECEIPT_SOURCE_CODE_RHI+" TEXT, "+CAMPO_TRANSACTION_TYPE_RHI+" TEXT, "+CAMPO_LAST_UPDATE_DATE_RHI+" TEXT, "+CAMPO_LAST_UPDATED_BY_RHI+" INTEGER, "+CAMPO_CREATED_BY_RHI+" INTEGER, "+CAMPO_RECEIPT_NUM_RHI+" TEXT, "+CAMPO_VENDOR_ID_RHI+" INTEGER, "+CAMPO_VENDOR_SITE_CODE_RHI+" TEXT, "+CAMPO_VENDOR_SITE_ID_RHI+" INTEGER, "+CAMPO_SHIP_TO_ORGANIZATION_CODE_RHI+" TEXT, "+CAMPO_LOCATION_ID_RHI+" INTEGER, "+CAMPO_RECEIVER_ID_RHI+" INTEGER, "+CAMPO_CURRENCY_CODE_RHI+" TEXT, "+CAMPO_CONVERSION_RATE_TYPE_RHI+" TEXT, "+CAMPO_CONVERSION_RATE_RHI+" INTEGER, "+CAMPO_CONVERSION_RATE_DATE_RHI+" TEXT, "+CAMPO_PAYMENT_TERMS_ID_RHI+" INTEGER, "+CAMPO_TRANSACTION_DATE_RHI+" TEXT, "+CAMPO_COMMENTS_RHI+" TEXT, "+CAMPO_ORG_ID_RHI+" INTEGER, "+CAMPO_PROCESSING_STATUS_CODE_RHI+" TEXT, "+CAMPO_VALIDATION_FLAG_RHI+" TEXT, "+CAMPO_GROUP_ID_RHI+" INTEGER) ";

}
