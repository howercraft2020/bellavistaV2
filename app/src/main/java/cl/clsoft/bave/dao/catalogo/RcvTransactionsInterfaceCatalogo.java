package cl.clsoft.bave.dao.catalogo;

public class RcvTransactionsInterfaceCatalogo {

    public static final String TABLA = "rcv_transactions_interface";
    public static final String CAMPO_INTERFACE_TRANSACTION_ID_RTI = "interface_transaction_id";
    public static final String CAMPO_LAST_UPDATE_DATE_RTI = "last_update_date";
    public static final String CAMPO_LAST_UPDATED_BY_RTI = "last_updated_by";
    public static final String CAMPO_CREATION_DATE_RTI = "creation_date";
    public static final String CAMPO_CREATED_BY_RTI = "created_by";
    public static final String CAMPO_TRANSACTION_TYPE_RTI = "transaction_type";
    public static final String CAMPO_TRANSACTION_DATE_RTI = "transaction_date";
    public static final String CAMPO_QUANTITY_RTI = "quantity";
    public static final String CAMPO_UNIT_OF_MEASURE_RTI = "unit_of_measure";
    public static final String CAMPO_ITEM_ID_RTI = "item_id";
    public static final String CAMPO_ITEM_DESCRIPTION_RTI = "item_description";
    public static final String CAMPO_UOM_CODE_RTI = "uom_code";
    public static final String CAMPO_SHIP_TO_LOCATION_ID_RTI = "ship_to_location_id";
    public static final String CAMPO_PRIMARY_QUANTITY_RTI = "primary_quantity";
    public static final String CAMPO_RECEIPT_SOURCE_CODE_RTI = "receipt_source_code";
    public static final String CAMPO_VENDOR_ID_RTI = "vendor_id";
    public static final String CAMPO_VENDOR_SITE_ID_RTI = "vendor_site_id";
    public static final String CAMPO_TO_ORGANIZATION_ID_RTI = "to_organization_id";
    public static final String CAMPO_PO_HEADER_ID_RTI = "po_header_id";
    public static final String CAMPO_PO_LINE_ID_RTI = "po_line_id";
    public static final String CAMPO_PO_LINE_LOCATION_ID_RTI = "po_line_location_id";
    public static final String CAMPO_PO_UNIT_PRICE_RTI = "po_unit_price";
    public static final String CAMPO_CURRENCY_CODE_RTI = "currency_code";
    public static final String CAMPO_SOURCE_DOCUMENT_CODE_RTI = "source_document_code";
    public static final String CAMPO_CURRENCY_CONVERSION_TYPE_RTI = "currency_conversion_type";
    public static final String CAMPO_CURRENCY_CONVERSION_RATE_RTI = "currency_conversion_rate";
    public static final String CAMPO_CURRENCY_CONVERSION_DATE_RTI = "currency_conversion_date";
    public static final String CAMPO_PO_DISTRIBUTION_ID_RTI = "po_distribution_id";
    public static final String CAMPO_DESTINATION_TYPE_CODE_RTI = "destination_type_code";
    public static final String CAMPO_LOCATION_ID_RTI = "location_id";
    public static final String CAMPO_DELIVER_TO_LOCATION_ID_RTI = "deliver_to_location_id";
    public static final String CAMPO_INSPECTION_STATUS_CODE_RTI = "inspection_status_code";
    public static final String CAMPO_HEADER_INTERFACE_ID_RTI = "header_interface_id";
    public static final String CAMPO_VENDOR_SITE_CODE_RTI = "vendor_site_code";
    public static final String CAMPO_PROCESSING_STATUS_CODE_RTI = "processing_status_code";
    public static final String CAMPO_USE_MTL_LOT_RTI = "use_mtl_lot";
    public static final String CAMPO_USE_MTL_SERIAL_RTI = "use_mtl_serial";
    public static final String CAMPO_TRANSACTION_STATUS_CODE_RTI = "transaction_status_code";
    public static final String CAMPO_VALIDATION_FLAG_RTI = "validation_flag";
    public static final String CAMPO_ORG_ID_RTI = "org_id";
    public static final String CAMPO_GROUP_ID_RTI = "group_id";
    public static final String CAMPO_AUTO_TRANSACT_CODE_RTI = "auto_transact_code";
    public static final String CREAR_TABLA = "CREATE TABLE "+ TABLA +"("+CAMPO_INTERFACE_TRANSACTION_ID_RTI+" INTEGER, "+CAMPO_LAST_UPDATE_DATE_RTI+" TEXT, "+CAMPO_LAST_UPDATED_BY_RTI+" INTEGER, "+CAMPO_CREATION_DATE_RTI+" TEXT, "+CAMPO_CREATED_BY_RTI+" INTEGER, "+CAMPO_TRANSACTION_TYPE_RTI+" TEXT, "+CAMPO_TRANSACTION_DATE_RTI+" TEXT, "+CAMPO_QUANTITY_RTI+" INTEGER, "+CAMPO_UNIT_OF_MEASURE_RTI+" TEXT, "+CAMPO_ITEM_ID_RTI+" INTEGER, "+CAMPO_ITEM_DESCRIPTION_RTI+" TEXT, "+CAMPO_UOM_CODE_RTI+" TEXT, "+CAMPO_SHIP_TO_LOCATION_ID_RTI+" INTEGER, "+CAMPO_PRIMARY_QUANTITY_RTI+" INTEGER, "+CAMPO_RECEIPT_SOURCE_CODE_RTI+" TEXT, "+CAMPO_VENDOR_ID_RTI+" INTEGER, "+CAMPO_VENDOR_SITE_ID_RTI+" INTEGER, "+CAMPO_TO_ORGANIZATION_ID_RTI+" INTEGER, "+CAMPO_PO_HEADER_ID_RTI+" INTEGER, "+CAMPO_PO_LINE_ID_RTI+" INTEGER, "+CAMPO_PO_LINE_LOCATION_ID_RTI+" INTEGER, "+CAMPO_PO_UNIT_PRICE_RTI+" INTEGER, "+ CAMPO_CURRENCY_CODE_RTI+" TEXT, "+CAMPO_SOURCE_DOCUMENT_CODE_RTI+" TEXT, "+CAMPO_CURRENCY_CONVERSION_TYPE_RTI+" TEXT, "+CAMPO_CURRENCY_CONVERSION_RATE_RTI+" INTEGER, "+CAMPO_CURRENCY_CONVERSION_DATE_RTI+ " TEXT, "+CAMPO_PO_DISTRIBUTION_ID_RTI+" INTEGER, "+CAMPO_DESTINATION_TYPE_CODE_RTI+" TEXT, "+CAMPO_LOCATION_ID_RTI+" INTEGER, "+CAMPO_DELIVER_TO_LOCATION_ID_RTI+ " INTEGER, "+CAMPO_INSPECTION_STATUS_CODE_RTI+" TEXT, "+CAMPO_HEADER_INTERFACE_ID_RTI+" INTEGER, "+CAMPO_VENDOR_SITE_CODE_RTI+" TEXT, "+CAMPO_PROCESSING_STATUS_CODE_RTI+" TEXT, "+CAMPO_USE_MTL_LOT_RTI+ " INTEGER, "+CAMPO_USE_MTL_SERIAL_RTI+" INTEGER, "+CAMPO_TRANSACTION_STATUS_CODE_RTI+" TEXT, "+CAMPO_VALIDATION_FLAG_RTI+" TEXT, "+CAMPO_ORG_ID_RTI+" INTEGER, "+CAMPO_GROUP_ID_RTI+" INTEGER, "+CAMPO_AUTO_TRANSACT_CODE_RTI+" TEXT) ";

}
