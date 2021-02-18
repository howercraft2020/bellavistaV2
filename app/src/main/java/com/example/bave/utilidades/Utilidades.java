package com.example.bave.utilidades;

public class Utilidades {

    //Tablas
    public static final String TABLA_ORGANIZACION = "organizacion";
    public static final String TABLA_LOCALIZADOR = "localizador";
    public static final String TABLA_SUBINVENTARIO = "subinventario";
    public static final String TABLA_PO_HEADERS_ALL = "po_headers_all";
    public static final String TABLA_PO_LINES_ALL = "po_lines_all";
    public static final String TABLA_PO_LINE_LOCATIONS_ALL = "po_line_locations_all";
    public static final String TABLA_PO_DISTRIBUTIONS_ALL = "po_distributions_all";
    public static final String TABLA_MTL_SYSTEM_ITEMS = "mtl_system_items";
    public static final String TABLA_RCV_HEADERS_INTERFACE = "rcv_headers_interface";
    public static final String TABLA_RCV_TRANSACTIONS_INTERFACE = "rcv_transactions_interface";
    public static final String TABLA_RCV_SHIPMENT_HEADERS = "rcv_shipment_headers";
    public static final String TABLA_RCV_TRANSACTIONS = "rcv_transactions";
    public static final String TABLA_MTL_TRANSACTIONS_LOTS_IFACE = "mtl_transactions_lots_iface";
    public static final String TABLA_MTL_SERIAL_NUMBERS_INTERFACE = "mtl_serial_numbers_interface";


    //Campos Organizacion
    public static final String CAMPO_ID = "id_organizacion";
    public static final String CAMPO_CODE = "code";

    //Campos Localizador
    public static final String CAMPO_ID_LOC = "id_localizador";
    public static final String CAMPO_ORG_ID_LOC = "organization_id";
    public static final String CAMPO_COD_SUBINV_LOC = "cod_subinventario";
    public static final String CAMPO_COD_LOC_LOC = "cod_localizador";
    public static final String CAMPO_COD_SEG1_LOC = "cod_seg1";
    public static final String CAMPO_COD_SEG2_LOC = "cod_seg2";
    public static final String CAMPO_COD_SEG3_LOC = "cod_seg3";
    public static final String CAMPO_COD_SEG4_LOC = "cod_seg4";
    public static final String CAMPO_COD_SEG5_LOC = "cod_seg5";
    public static final String CAMPO_COD_SEG6_LOC = "cod_seg6";

    //Campos Subinventario
    public static final String CAMPO_ORG_ID_SUB = "organization_id";
    public static final String CAMPO_COD_SUB = "cod_subinventario";
    public static final String CAMPO_COD_LOC = "cod_localizador";

    //Campos po_headers_all
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

    //Campos po_lines_all
    public static final String CAMPO_PO_LINE_ID_PLA = "po_line_id";
    public static final String CAMPO_PO_HEADER_ID_PLA = "po_header_id";
    public static final String CAMPO_LINE_NUM_PLA = "line_num";
    public static final String CAMPO_ITEM_ID_PLA = "item_id";
    public static final String CAMPO_ITEM_DESCRIPTION_PLA = "item_description";
    public static final String CAMPO_UNIT_PRICE_PLA = "unit_price";
    public static final String CAMPO_QUANTITY_PLA = "quantity_pla";
    public static final String CAMPO_UNIT_MEAS_LOOKUP_CODE_PLA = "unit_meas_lookup_code";
    public static final String CAMPO_BASE_UOM_PLA = "base_uom";

    //Campos po_lines_locations_all
    public static final String CAMPO_LINE_LOCATION_ID_PLL = "line_location_id";
    public static final String CAMPO_PO_LINE_ID_PLL = "po_line_id";
    public static final String CAMPO_PO_HEADER_ID_PLL = "po_header_id";
    public static final String CAMPO_QUANTITY_PLL = "quantity";
    public static final String CAMPO_QUANTITY_RECEIVED_PLL = "quantity_received";
    public static final String CAMPO_QUANTITY_CANCELLED_PLL = "quantity_cancelled";
    public static final String CAMPO_QUANTITY_BILLED_PLL = "quantity_billed";
    public static final String CAMPO_SHIPMENT_NUM_PLL = "shipment_num";
    public static final String CAMPO_SHIP_TO_LOCATION_ID_PLL = "ship_to_location_id";
    public static final String CAMPO_QTY_RCV_TOLERANCE_PLL = "qty_rcv_tolerance";
    public static final String CAMPO_ORG_ID_PLL = "org_id";

    //Campos po_distributions all
    public static final String CAMPO_PO_DISTRIBUTION_ID_PDA = "po_distribution_id";
    public static final String CAMPO_LINE_LOCATION_ID_PDA = "line_location_id";
    public static final String CAMPO_PO_LINE_ID_PDA = "po_line_id";
    public static final String CAMPO_PO_HEADER_ID_PDA = "po_header_id";
    public static final String CAMPO_DISTRIBUTION_NUM_PDA = "distribution_num";
    public static final String CAMPO_RATE_DATE_PDA = "rate_date";
    public static final String CAMPO_RATE_PDA = "rate";
    public static final String CAMPO_DESTINATION_SUBINVENTORY_PDA = "destination_subinventory";
    public static final String CAMPO_DELIVER_TO_LOCATION_ID_PDA = "deliver_to_location_id";
    public static final String CAMPO_QUANTITY_ORDERED_PDA = "quantity_ordered";
    public static final String CAMPO_QUANTITY_DELIVERED_PDA = "quantity_delivered";
    public static final String CAMPO_QUANTITY_BILLED_PDA = "quantity_billed";
    public static final String CAMPO_QUANTITY_CANCELLED_PDA = "quantity_cancelled";

    //Campos mtl_system_items
    public static final String CAMPO_INVENTORY_ITEM_ID_MSI = "inventory_item_id";
    public static final String CAMPO_DESCRIPTION_MSI = "description";
    public static final String CAMPO_LONG_DESCRIPTION_MSI = "long_description";
    public static final String CAMPO_SEGMENT1_MSI = "segment1";
    public static final String CAMPO_PRIMARY_UOM_CODE_MSI = "primary_uom_code";
    public static final String CAMPO_LOT_CONTROL_CODE_MSI = "lot_control_code";
    public static final String CAMPO_SHELF_LIFE_CODE_MSI = "shelf_life_code";
    public static final String CAMPO_SERIAL_NUMBER_CONTROL_CODE_MSI = "serial_number_control_code";

    //Campos rcv_headers_interface
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

    //Campos rcv_transactions_interface
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

    //Campos rcv_shipment_headers
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

    //Campos rcv_transactions

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

    //Campos Mtl_Transactions_Lots_Iface
    public static final String CAMPO_MTL_TRANSACTION_LOTS_IFACE_MTL = "mtl_transaction_lots_iface";
    public static final String CAMPO_LAST_UPDATE_DATE_MTL = "last_update_date";
    public static final String CAMPO_LAST_UPDATE_BY_MTL = "last_update_by";
    public static final String CAMPO_CREATION_DATE_MTL = "creation_date";
    public static final String CAMPO_CREATED_BY_MTL = "created_by";
    public static final String CAMPO_PO_HEADER_ID_MTL = "po_header_id";
    public static final String CAMPO_PO_LINE_ID_MTL = "po_line_id";
    public static final String CAMPO_INVENTORY_ITEM_ID_MTL = "inventory_item_id";
    public static final String CAMPO_LAST_UPDATE_LOGIN_MTL = "last_update_login";
    public static final String CAMPO_LOT_NUMBER_MTL = "lot_number";
    public static final String CAMPO_TRANSACTION_QUANTITY_MTL = "transaction_quantity";
    public static final String CAMPO_PRIMARY_QUANTITY_MTL = "primary_quantity";
    public static final String CAMPO_SERIAL_TRANSACTION_TEMP_ID_MTL = "serial_transaction_temp_id";
    public static final String CAMPO_PRODUCT_CODE_MTL = "product_code";
    public static final String CAMPO_PRODUCT_TRANSACTION_ID_MTL = "product_transaction_id";
    public static final String CAMPO_SUPPLIER_LOT_NUMBER_MTL = "supplier_lot_number";
    public static final String CAMPO_LOT_EXPIRATION_DATE_MTL = "lot_expiration_date";
    public static final String CAMPO_ATTRIBUTE_CATEGORY_MTL = "attribute_category";
    public static final String CAMPO_ATTRIBUTE_1_MTL = "attribute1";
    public static final String CAMPO_ATTRIBUTE_2_MTL = "attribute2";
    public static final String CAMPO_ATTRIBUTE_3_MTL = "attribute3";

    //Campos Mtl_Serial_Numbers_Interface
    public static final String CAMPO_TRANSACTION_INTERFACE_ID_SNI = "transaction_interface_id";
    public static final String CAMPO_LAST_UPDATE_DATE_SNI = "last_update_date";
    public static final String CAMPO_LAST_UPDATED_BY_SNI = "last_updated_by";
    public static final String CAMPO_CREATION_DATE_SNI = "creation_date";
    public static final String CAMPO_CREATED_BY_SNI = "created_by";
    public static final String CAMPO_PO_HEADER_ID_SNI = "po_header_id";
    public static final String CAMPO_PO_LINE_ID_SNI = "po_line_id";
    public static final String CAMPO_INVENTORY_ITEM_ID_SNI = "inventory_item_id";
    public static final String CAMPO_LAST_UPDATE_LOGIN_SNI = "last_update_login";
    public static final String CAMPO_FM_SERIAL_NUMBER_SNI = "fm_serial_number";
    public static final String CAMPO_TO_SERIAL_NUMBER_SNI = "to_serial_number";
    public static final String CAMPO_PRODUCT_CODE_SNI = "product_code";
    public static final String CAMPO_PRODUCT_TRANSACTION_ID_SNI = "product_transaction_id";


    //Creación Tabla Organizacion
    public static final String CREAR_TABLA_ORGANIZACION="CREATE TABLE "+ TABLA_ORGANIZACION +" ("+CAMPO_ID+" INTEGER, "+CAMPO_CODE+" TEXT) ";

    //Creación Tabla Localizadores
    public static final String CREAR_TABLA_LOCALIZADOR="CREATE TABLE "+ TABLA_LOCALIZADOR +" ("+CAMPO_ID_LOC+" INTEGER, "+CAMPO_ORG_ID_LOC+" INTEGER, "+CAMPO_COD_SUBINV_LOC+" TEXT, "+CAMPO_COD_LOC_LOC+" TEXT, "+ CAMPO_COD_SEG1_LOC+" TEXT, "+CAMPO_COD_SEG2_LOC+" TEXT, "+CAMPO_COD_SEG3_LOC+" TEXT, "+CAMPO_COD_SEG4_LOC+" TEXT, "+CAMPO_COD_SEG5_LOC+" TEXT, "+CAMPO_COD_SEG6_LOC+" TEXT) ";

    //Creación Tabla Subinventario
    public static final String CREAR_TABLA_SUBINVENTARIO="CREATE TABLE "+ TABLA_SUBINVENTARIO +" ("+CAMPO_ORG_ID_SUB+" INTEGER, "+CAMPO_COD_SUB+" TEXT ,"+CAMPO_COD_LOC+" TEXT) ";

    //Creación Tabla Po_headers_all
    public static final String CREAR_TABLA_PO_HEADERS_ALL="CREATE TABLE "+ TABLA_PO_HEADERS_ALL +" ("+CAMPO_CREATED_BY_PHA+" INTEGER, "+CAMPO_CREATION_DATE_PHA+" TEXT ,"+CAMPO_VENDOR_NAME_PHA+" TEXT, "+CAMPO_VENDOR_ID_PHA+" INTEGER, "+CAMPO_VENDOR_SITE_ID_PHA+" INTEGER, "+CAMPO_VENDOR_SITE_CODE_PHA+" TEXT, "+CAMPO_PO_HEADER_ID_PHA+" INTEGER, "+CAMPO_SEGMENT1_PHA+" TEXT, "+CAMPO_ORG_ID_PHA+" INTEGER, "+ CAMPO_APPROVED_DATE_PHA+" TEXT, "+CAMPO_OPERATING_UNIT_PHA+" TEXT, "+CAMPO_TERMS_ID_PHA+" INTEGER, "+CAMPO_CURRENCY_CODE_PHA+" TEXT, "+CAMPO_RATE_TYPE_PHA+" TEXT, "+CAMPO_RATE_DATE_PHA+" TEXT, "+CAMPO_RATE_PHA+" TEXT, "+CAMPO_USER_ID_PHA+" INTEGER, "+CAMPO_RECEIPT_NUM_PHA+" TEXT)";

    //Creación Tabla Po_lines_all
    public static final String CREAR_TABLA_PO_LINES_ALL="CREATE TABLE "+ TABLA_PO_LINES_ALL +"("+CAMPO_PO_LINE_ID_PLA+" INTEGER, "+CAMPO_PO_HEADER_ID_PLA+" INTEGER ,"+CAMPO_LINE_NUM_PLA+" TEXT, "+CAMPO_ITEM_ID_PLA+" INTEGER, "+CAMPO_ITEM_DESCRIPTION_PLA+" TEXT, "+CAMPO_UNIT_PRICE_PLA+" INTEGER, "+CAMPO_QUANTITY_PLA+" INTEGER, "+CAMPO_UNIT_MEAS_LOOKUP_CODE_PLA+" TEXT, "+CAMPO_BASE_UOM_PLA+" TEXT) ";

    //Creación Tabla Po_line_locations_all
    public static final String CREAR_TABLA_PO_LINE_LOCATIONS_ALL="CREATE TABLE "+ TABLA_PO_LINE_LOCATIONS_ALL +"("+CAMPO_LINE_LOCATION_ID_PLL+" INTEGER, "+CAMPO_PO_LINE_ID_PLL+" INTEGER, "+CAMPO_PO_HEADER_ID_PLL+" INTEGER, "+CAMPO_QUANTITY_PLL+" INTEGER, "+CAMPO_QUANTITY_RECEIVED_PLL+" INTEGER, "+CAMPO_QUANTITY_CANCELLED_PLL+" INTEGER, "+CAMPO_QUANTITY_BILLED_PLL+" INTEGER, "+CAMPO_SHIPMENT_NUM_PLL+" INTEGER, "+CAMPO_SHIP_TO_LOCATION_ID_PLL+" INTEGER, "+CAMPO_QTY_RCV_TOLERANCE_PLL+" INTEGER, "+CAMPO_ORG_ID_PLL+" INTEGER) ";

    //Creación Tabla Po_Distributions_all
    public static final String CREAR_TABLA_PO_DISTRIBUTIONS_ALL="CREATE TABLE "+TABLA_PO_DISTRIBUTIONS_ALL +"("+CAMPO_PO_DISTRIBUTION_ID_PDA+" INTEGER, "+CAMPO_LINE_LOCATION_ID_PDA+" INTEGER, "+CAMPO_PO_LINE_ID_PDA+" INTEGER, "+CAMPO_PO_HEADER_ID_PDA+" INTEGER, "+CAMPO_DISTRIBUTION_NUM_PDA+" INTEGER, "+CAMPO_RATE_DATE_PDA+" TEXT, "+CAMPO_RATE_PDA+" TEXT, "+CAMPO_DESTINATION_SUBINVENTORY_PDA+" TEXT, "+CAMPO_DELIVER_TO_LOCATION_ID_PDA+" INTEGER, "+ CAMPO_QUANTITY_ORDERED_PDA+" INTEGER, "+CAMPO_QUANTITY_DELIVERED_PDA+" INTEGER, "+CAMPO_QUANTITY_BILLED_PDA+" INTEGER, "+CAMPO_QUANTITY_CANCELLED_PDA+" INTEGER) ";

    //Creación Tabla Mtl_System_Items
    public static final String CREAR_TABLA_MTL_SYSTEM_ITEMS="CREATE TABLE "+TABLA_MTL_SYSTEM_ITEMS +"("+CAMPO_INVENTORY_ITEM_ID_MSI+" INTEGER, "+CAMPO_DESCRIPTION_MSI+" TEXT, "+CAMPO_LONG_DESCRIPTION_MSI+" TEXT, "+CAMPO_SEGMENT1_MSI+" TEXT, "+CAMPO_PRIMARY_UOM_CODE_MSI+" TEXT, "+CAMPO_LOT_CONTROL_CODE_MSI+" TEXT, "+CAMPO_SHELF_LIFE_CODE_MSI+" TEXT, "+CAMPO_SERIAL_NUMBER_CONTROL_CODE_MSI+" TEXT) ";

    //Creación Tabla Rcv_Headers_Interface
    public static final String CREAR_TABLA_RCV_HEADERS_INTERFACE="CREATE TABLE "+TABLA_RCV_HEADERS_INTERFACE +"("+CAMPO_HEADER_INTERFACE_ID_RHI+" TEXT, "+CAMPO_RECEIPT_SOURCE_CODE_RHI+" TEXT, "+CAMPO_TRANSACTION_TYPE_RHI+" TEXT, "+CAMPO_LAST_UPDATE_DATE_RHI+" TEXT, "+CAMPO_LAST_UPDATED_BY_RHI+" INTEGER, "+CAMPO_CREATED_BY_RHI+" INTEGER, "+CAMPO_RECEIPT_NUM_RHI+" TEXT, "+CAMPO_VENDOR_ID_RHI+" INTEGER, "+CAMPO_VENDOR_SITE_CODE_RHI+" TEXT, "+CAMPO_VENDOR_SITE_ID_RHI+" INTEGER, "+CAMPO_SHIP_TO_ORGANIZATION_CODE_RHI+" TEXT, "+CAMPO_LOCATION_ID_RHI+" INTEGER, "+CAMPO_RECEIVER_ID_RHI+" INTEGER, "+CAMPO_CURRENCY_CODE_RHI+" TEXT, "+CAMPO_CONVERSION_RATE_TYPE_RHI+" TEXT, "+CAMPO_CONVERSION_RATE_RHI+" INTEGER, "+CAMPO_CONVERSION_RATE_DATE_RHI+" TEXT, "+CAMPO_PAYMENT_TERMS_ID_RHI+" INTEGER, "+CAMPO_TRANSACTION_DATE_RHI+" TEXT, "+CAMPO_COMMENTS_RHI+" TEXT, "+CAMPO_ORG_ID_RHI+" INTEGER, "+CAMPO_PROCESSING_STATUS_CODE_RHI+" TEXT, "+CAMPO_VALIDATION_FLAG_RHI+" TEXT, "+CAMPO_GROUP_ID_RHI+" INTEGER) ";

    //Creación Tabla Rcv_Transactions_Interface
    public static final String CREAR_TABLA_RCV_TRANSACTIONS_INTERFACE="CREATE TABLE "+TABLA_RCV_TRANSACTIONS_INTERFACE +"("+CAMPO_INTERFACE_TRANSACTION_ID_RTI+" INTEGER, "+CAMPO_LAST_UPDATE_DATE_RTI+" TEXT, "+CAMPO_LAST_UPDATED_BY_RTI+" INTEGER, "+CAMPO_CREATION_DATE_RTI+" TEXT, "+CAMPO_CREATED_BY_RTI+" INTEGER, "+CAMPO_TRANSACTION_TYPE_RTI+" TEXT, "+CAMPO_TRANSACTION_DATE_RTI+" TEXT, "+CAMPO_QUANTITY_RTI+" INTEGER, "+CAMPO_UNIT_OF_MEASURE_RTI+" TEXT, "+CAMPO_ITEM_ID_RTI+" INTEGER, "+CAMPO_ITEM_DESCRIPTION_RTI+" TEXT, "+CAMPO_UOM_CODE_RTI+" TEXT, "+CAMPO_SHIP_TO_LOCATION_ID_RTI+" INTEGER, "+CAMPO_PRIMARY_QUANTITY_RTI+" INTEGER, "+CAMPO_RECEIPT_SOURCE_CODE_RTI+" TEXT, "+CAMPO_VENDOR_ID_RTI+" INTEGER, "+CAMPO_VENDOR_SITE_ID_RTI+" INTEGER, "+CAMPO_TO_ORGANIZATION_ID_RTI+" INTEGER, "+CAMPO_PO_HEADER_ID_RTI+" INTEGER, "+CAMPO_PO_LINE_ID_RTI+" INTEGER, "+CAMPO_PO_LINE_LOCATION_ID_RTI+" INTEGER, "+CAMPO_PO_UNIT_PRICE_RTI+" INTEGER, "+ CAMPO_CURRENCY_CODE_RTI+" TEXT, "+CAMPO_SOURCE_DOCUMENT_CODE_RTI+" TEXT, "+CAMPO_CURRENCY_CONVERSION_TYPE_RTI+" TEXT, "+CAMPO_CURRENCY_CONVERSION_RATE_RTI+" INTEGER, "+CAMPO_CURRENCY_CONVERSION_DATE_RTI+ " TEXT, "+CAMPO_PO_DISTRIBUTION_ID_RTI+" INTEGER, "+CAMPO_DESTINATION_TYPE_CODE_RTI+" TEXT, "+CAMPO_LOCATION_ID_RTI+" INTEGER, "+CAMPO_DELIVER_TO_LOCATION_ID_RTI+ " INTEGER, "+CAMPO_INSPECTION_STATUS_CODE_RTI+" TEXT, "+CAMPO_HEADER_INTERFACE_ID_RTI+" INTEGER, "+CAMPO_VENDOR_SITE_CODE_RTI+" TEXT, "+CAMPO_PROCESSING_STATUS_CODE_RTI+" TEXT, "+CAMPO_USE_MTL_LOT_RTI+ " INTEGER, "+CAMPO_USE_MTL_SERIAL_RTI+" INTEGER, "+CAMPO_TRANSACTION_STATUS_CODE_RTI+" TEXT, "+CAMPO_VALIDATION_FLAG_RTI+" TEXT, "+CAMPO_ORG_ID_RTI+" INTEGER, "+CAMPO_GROUP_ID_RTI+" INTEGER, "+CAMPO_AUTO_TRANSACT_CODE_RTI+" TEXT) ";

    //Creación Tabla Rcv_Shipment_Headers
    public static final String CREAR_TABLA_RCV_SHIPMENT_HEADERS="CREATE TABLE "+TABLA_RCV_SHIPMENT_HEADERS +"("+CAMPO_SHIPMENT_HEADER_ID_RSH+" INTEGER, "+CAMPO_LAST_UPDATE_DATE_RSH+" TEXT, "+CAMPO_LAST_UPDATED_BY_RSH+" INTEGER, "+CAMPO_CREATION_DATE_RSH+" TEXT, "+CAMPO_CREATED_BY_RSH+" INTEGER, "+CAMPO_USER_ID_RSH+" INTEGER, "+CAMPO_VENDOR_ID_RSH+" INTEGER, "+CAMPO_VENDOR_SITE_ID_RSH+" INTEGER, "+CAMPO_ORGANIZATION_ID_RSH+" INTEGER, "+CAMPO_SHIPMENT_NUM_RSH+" TEXT, "+CAMPO_RECEIPT_NUM_RSH+" TEXT, "+CAMPO_EMPLOYEE_ID_RSH+" INTEGER, "+CAMPO_SHIP_TO_ORG_ID_RSH+" INTEGER) ";

    //Creación Tabla Rcv_Transactions
    public static final String CREAR_TABLA_RCV_TRANSACTIONS="CREATE TABLE "+TABLA_RCV_TRANSACTIONS +"("+CAMPO_TRANSACTION_ID_RT+" INTEGER, "+CAMPO_LAST_UPDATE_DATE_RT+" TEXT, "+CAMPO_LAST_UPDATED_BY_RT+" INTEGER, "+CAMPO_CREATION_DATE_RT+" TEXT, "+CAMPO_CREATED_BY_RT+" INTEGER, "+CAMPO_TRANSACTION_TYPE_RT+" TEXT, "+CAMPO_TRANSACTION_DATE_RT+" TEXT, "+CAMPO_QUANTITY_RT+" INTEGER, "+CAMPO_UNIT_OF_MEASURE_RT+" TEXT, "+CAMPO_SHIPMENT_HEADER_ID_RT+" INTEGER, "+CAMPO_SHIPMENT_LINE_ID_RT+" INTEGER, "+CAMPO_SOURCE_DOCUMENT_CODE_RT+" TEXT, "+CAMPO_DESTINATION_TYPE_CODE_RT+" TEXT, "+CAMPO_PRIMARY_UNIT_OF_MEASURE_RT+" TEXT, "+CAMPO_UOM_CODE_RT+" TEXT, "+CAMPO_EMPLOYEE_ID_RT+" INTEGER, "+CAMPO_PO_HEADER_ID_RT+" INTEGER, "+CAMPO_PO_LINE_ID_RT+" INTEGER, "+CAMPO_PO_LINE_LOCATION_ID_RT+" INTEGER, "+CAMPO_PO_DISTRIBUTION_ID_RT+" INTEGER, "+CAMPO_PO_UNIT_PRICE_RT+" INTEGER, "+CAMPO_CURRENCY_CONVERSION_TYPE_RT+" TEXT, "+CAMPO_CURRENCY_CONVERSION_RATE_RT+" INTEGER, "+CAMPO_CURRENCY_CONVERSION_DATE_RT+" TEXT, "+CAMPO_DELIVER_TO_LOCATION_ID_RT+" INTEGER, "+CAMPO_VENDOR_ID_RT+" INTEGER, "+CAMPO_VENDOR_SITE_ID_RT+" INTEGER, "+CAMPO_ORGANIZATION_ID_RT+" INTEGER, "+CAMPO_LOCATION_ID_RT+" INTEGER, "+CAMPO_INSPECTION_STATUS_CODE_RT+" TEXT, "+CAMPO_DESTINATION_CONTEXT_RT+" TEXT, "+CAMPO_INTERFACE_TRANSACTION_ID_RT+" INTEGER, "+CAMPO_ITEM_ID_RT+" INTEGER) ";

    //Creación Tabla Mtl_Transactions_Lots_Iface
    public static final String CREAR_TABLA_MTL_TRANSACTIONS_LOTS_IFACE="CREATE TABLE "+TABLA_MTL_TRANSACTIONS_LOTS_IFACE +"("+CAMPO_MTL_TRANSACTION_LOTS_IFACE_MTL+" INTEGER, "+CAMPO_LAST_UPDATE_DATE_MTL+" TEXT, "+CAMPO_LAST_UPDATE_BY_MTL+" INTEGER, "+CAMPO_CREATION_DATE_MTL+" TEXT, "+CAMPO_CREATED_BY_MTL+" INTEGER, "+CAMPO_PO_HEADER_ID_MTL+" INTEGER, "+CAMPO_PO_LINE_ID_MTL+" INTEGER, "+CAMPO_INVENTORY_ITEM_ID_MTL+" INTEGER, "+CAMPO_LAST_UPDATE_LOGIN_MTL+" INTEGER, "+CAMPO_LOT_NUMBER_MTL+" TEXT, "+CAMPO_TRANSACTION_QUANTITY_MTL+" INTEGER, "+CAMPO_PRIMARY_QUANTITY_MTL+" INTEGER, "+CAMPO_SERIAL_TRANSACTION_TEMP_ID_MTL+" INTEGER, "+CAMPO_PRODUCT_CODE_MTL+" TEXT, "+CAMPO_PRODUCT_TRANSACTION_ID_MTL+" INTEGER, "+CAMPO_SUPPLIER_LOT_NUMBER_MTL+" TEXT, "+CAMPO_LOT_EXPIRATION_DATE_MTL+" TEXT, "+CAMPO_ATTRIBUTE_CATEGORY_MTL+" TEXT, "+CAMPO_ATTRIBUTE_1_MTL+" TEXT, "+CAMPO_ATTRIBUTE_2_MTL+" TEXT, "+CAMPO_ATTRIBUTE_3_MTL+" TEXT) ";

    //Creación Tabla Mtl_Serial_Number_Interface
    public static final String CREAR_TABLA_TABLA_MTL_SERIAL_NUMBERS_INTERFACE="CREATE TABLE "+TABLA_MTL_SERIAL_NUMBERS_INTERFACE +"("+CAMPO_TRANSACTION_INTERFACE_ID_SNI+" INTEGER, "+CAMPO_LAST_UPDATE_DATE_SNI+" TEXT, "+CAMPO_LAST_UPDATED_BY_SNI+" INTEGER, "+CAMPO_CREATION_DATE_SNI+" TEXT, "+CAMPO_CREATED_BY_SNI+" INTEGER, "+CAMPO_PO_HEADER_ID_SNI+" INTEGER, "+CAMPO_PO_LINE_ID_SNI+" INTEGER, "+CAMPO_INVENTORY_ITEM_ID_SNI+" TEXT, "+CAMPO_LAST_UPDATE_LOGIN_SNI+" INTEGER, "+CAMPO_FM_SERIAL_NUMBER_SNI+" TEXT, "+CAMPO_TO_SERIAL_NUMBER_SNI+" TEXT, "+CAMPO_PRODUCT_CODE_SNI+" TEXT, "+CAMPO_PRODUCT_TRANSACTION_ID_SNI+" INTEGER) ";

}
