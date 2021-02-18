package cl.clsoft.bave.dao.catalogo;

public class DatosCabeceraRecepcionCatalogo
{
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_RECEIPT_NUM = "receipt_num";
    public static final String COLUMN_VENDOR_ID = "vendor_id";
    public static final String COLUMN_VENDOR_SITE_CODE = "vendor_site_code";
    public static final String COLUMN_VENDOR_SITE_ID = "vendor_site_id";
    public static final String COLUMN_CURRENCY_CODE = "currency_code";
    public static final String COLUMN_RATE_TYPE = "rate_type";
    public static final String COLUMN_RATE = "rate";
    public static final String COLUMN_RATE_DATE = "rate_date";
    public static final String COLUMN_TERMS_ID = "terms_id";
    public static final String SELECT = "SELECT user_id, receipt_num, vendor_id, vendor_site_code, vendor_site_id, currency_code, rate_type, rate, rate_date, terms_id FROM po_headers_all where po_header_id = ? AND receipt_num = ?";
}