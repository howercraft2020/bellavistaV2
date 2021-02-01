package cl.clsoft.bave.dao.catalogo;

public class RcvTransactionsCatalogo {

    public static final String TABLA = "rcv_transactions";
    public static final String CAMPO_TRANSACTION_ID_RT = "transaction_id";
    public static final String CAMPO_LAST_UPDATE_DATE_RT = "last_update_date";
    public static final String CAMPO_LAST_UPDATED_BY_RT = "last_updated_by";
    public static final String CAMPO_CREATION_DATE_RT = "creation_date";
    public static final String CAMPO_CREATED_BY_RT = "created_by";
    public static final String CAMPO_TRANSACTION_TYPE_RT = "transaction_type";
    public static final String CAMPO_TRANSACTION_DATE_RT = "transaction_date";
    public static final String CAMPO_QUANTITY_RT = "quantity";
    public static final String CAMPO_UNIT_OF_MEASURE_RT = "unit_of_measure";
    public static final String CAMPO_SHIPMENT_HEADER_ID_RT = "shipment_header_id";
    public static final String CAMPO_SHIPMENT_LINE_ID_RT = "shipment_line_id";
    public static final String CAMPO_SOURCE_DOCUMENT_CODE_RT = "source_document_code";
    public static final String CAMPO_DESTINATION_TYPE_CODE_RT = "destination_type_code";
    public static final String CAMPO_PRIMARY_UNIT_OF_MEASURE_RT = "primary_unit_of_measure";
    public static final String CAMPO_UOM_CODE_RT = "uom_code";
    public static final String CAMPO_EMPLOYEE_ID_RT = "employee_id";
    public static final String CAMPO_PO_HEADER_ID_RT = "po_header_id";
    public static final String CAMPO_PO_LINE_ID_RT = "po_line_id";
    public static final String CAMPO_PO_LINE_LOCATION_ID_RT = "po_line_location_id";
    public static final String CAMPO_PO_DISTRIBUTION_ID_RT = "po_distribution_id";
    public static final String CAMPO_PO_UNIT_PRICE_RT = "po_unit_price";
    public static final String CAMPO_CURRENCY_CONVERSION_TYPE_RT = "currency_conversion_type";
    public static final String CAMPO_CURRENCY_CONVERSION_RATE_RT = "currency_conversion_rate";
    public static final String CAMPO_CURRENCY_CONVERSION_DATE_RT = "currency_conversion_date";
    public static final String CAMPO_DELIVER_TO_LOCATION_ID_RT = "deliver_to_location_id";
    public static final String CAMPO_VENDOR_ID_RT = "vendor_id";
    public static final String CAMPO_VENDOR_SITE_ID_RT = "vendor_site_id";
    public static final String CAMPO_ORGANIZATION_ID_RT = "organization_id";
    public static final String CAMPO_LOCATION_ID_RT = "location_id";
    public static final String CAMPO_INSPECTION_STATUS_CODE_RT = "inspection_status_code";
    public static final String CAMPO_DESTINATION_CONTEXT_RT = "destination_context";
    public static final String CAMPO_INTERFACE_TRANSACTION_ID_RT = "interface_transaction_id";
    public static final String CAMPO_ITEM_ID_RT = "item_id";
    public static final String CREAR_TABLA = "CREATE TABLE "+ TABLA +"("+CAMPO_TRANSACTION_ID_RT+" INTEGER, "+CAMPO_LAST_UPDATE_DATE_RT+" TEXT, "+CAMPO_LAST_UPDATED_BY_RT+" INTEGER, "+CAMPO_CREATION_DATE_RT+" TEXT, "+CAMPO_CREATED_BY_RT+" INTEGER, "+CAMPO_TRANSACTION_TYPE_RT+" TEXT, "+CAMPO_TRANSACTION_DATE_RT+" TEXT, "+CAMPO_QUANTITY_RT+" INTEGER, "+CAMPO_UNIT_OF_MEASURE_RT+" TEXT, "+CAMPO_SHIPMENT_HEADER_ID_RT+" INTEGER, "+CAMPO_SHIPMENT_LINE_ID_RT+" INTEGER, "+CAMPO_SOURCE_DOCUMENT_CODE_RT+" TEXT, "+CAMPO_DESTINATION_TYPE_CODE_RT+" TEXT, "+CAMPO_PRIMARY_UNIT_OF_MEASURE_RT+" TEXT, "+CAMPO_UOM_CODE_RT+" TEXT, "+CAMPO_EMPLOYEE_ID_RT+" INTEGER, "+CAMPO_PO_HEADER_ID_RT+" INTEGER, "+CAMPO_PO_LINE_ID_RT+" INTEGER, "+CAMPO_PO_LINE_LOCATION_ID_RT+" INTEGER, "+CAMPO_PO_DISTRIBUTION_ID_RT+" INTEGER, "+CAMPO_PO_UNIT_PRICE_RT+" INTEGER, "+CAMPO_CURRENCY_CONVERSION_TYPE_RT+" TEXT, "+CAMPO_CURRENCY_CONVERSION_RATE_RT+" INTEGER, "+CAMPO_CURRENCY_CONVERSION_DATE_RT+" TEXT, "+CAMPO_DELIVER_TO_LOCATION_ID_RT+" INTEGER, "+CAMPO_VENDOR_ID_RT+" INTEGER, "+CAMPO_VENDOR_SITE_ID_RT+" INTEGER, "+CAMPO_ORGANIZATION_ID_RT+" INTEGER, "+CAMPO_LOCATION_ID_RT+" INTEGER, "+CAMPO_INSPECTION_STATUS_CODE_RT+" TEXT, "+CAMPO_DESTINATION_CONTEXT_RT+" TEXT, "+CAMPO_INTERFACE_TRANSACTION_ID_RT+" INTEGER, "+CAMPO_ITEM_ID_RT+" INTEGER) ";

}
