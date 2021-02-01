package cl.clsoft.bave.dao.catalogo;

public class RcvShipmentHeadersCatalogo {

    public static final String TABLA = "rcv_shipment_headers";
    public static final String CAMPO_SHIPMENT_HEADER_ID_RSH = "shipment_header_id";
    public static final String CAMPO_LAST_UPDATE_DATE_RSH = "last_update_date";
    public static final String CAMPO_LAST_UPDATED_BY_RSH = "last_updated_by";
    public static final String CAMPO_CREATION_DATE_RSH = "creation_date";
    public static final String CAMPO_CREATED_BY_RSH = "created_by";
    public static final String CAMPO_USER_ID_RSH = "user_id";
    public static final String CAMPO_VENDOR_ID_RSH = "vendor_id";
    public static final String CAMPO_VENDOR_SITE_ID_RSH = "vendor_site_id";
    public static final String CAMPO_ORGANIZATION_ID_RSH = "organization_id";
    public static final String CAMPO_SHIPMENT_NUM_RSH = "shipment_num";
    public static final String CAMPO_RECEIPT_NUM_RSH = "receipt_num";
    public static final String CAMPO_EMPLOYEE_ID_RSH = "employee_id";
    public static final String CAMPO_SHIP_TO_ORG_ID_RSH = "ship_to_org_id";
    public static final String CREAR_TABLA = "CREATE TABLE "+TABLA +"("+CAMPO_SHIPMENT_HEADER_ID_RSH+" INTEGER, "+CAMPO_LAST_UPDATE_DATE_RSH+" TEXT, "+CAMPO_LAST_UPDATED_BY_RSH+" INTEGER, "+CAMPO_CREATION_DATE_RSH+" TEXT, "+CAMPO_CREATED_BY_RSH+" INTEGER, "+CAMPO_USER_ID_RSH+" INTEGER, "+CAMPO_VENDOR_ID_RSH+" INTEGER, "+CAMPO_VENDOR_SITE_ID_RSH+" INTEGER, "+CAMPO_ORGANIZATION_ID_RSH+" INTEGER, "+CAMPO_SHIPMENT_NUM_RSH+" TEXT, "+CAMPO_RECEIPT_NUM_RSH+" TEXT, "+CAMPO_EMPLOYEE_ID_RSH+" INTEGER, "+CAMPO_SHIP_TO_ORG_ID_RSH+" INTEGER) ";

}
