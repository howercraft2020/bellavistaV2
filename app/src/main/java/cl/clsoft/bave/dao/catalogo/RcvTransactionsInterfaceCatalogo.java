package cl.clsoft.bave.dao.catalogo;

import java.security.interfaces.DSAKey;

public class RcvTransactionsInterfaceCatalogo {

    public static final String TABLE = "rcv_transactions_interface";
    public static final String COLUMN_INTERFACE_TRANSACTION_ID = "interface_transaction_id";
    public static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    public static final String COLUMN_LAST_UPDATED_BY = "last_updated_by";
    public static final String COLUMN_CREATION_DATE = "creation_date";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_TRANSACTION_TYPE = "transaction_type";
    public static final String COLUMN_TRANSACTION_DATE = "transaction_date";
    public static final String COLUMN_PROCESSING_STATUS_CODE = "processing_status_code";
    public static final String COLUMN_PROCESSING_MODE_CODE = "processing_mode_code";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_UNIT_OF_MEASURE = "unit_of_measure";
    public static final String COLUMN_ITEM_ID = "item_id";
    public static final String COLUMN_ITEM_DESCRIPTION = "item_description";
    public static final String COLUMN_UOM_CODE = "uom_code";
    public static final String COLUMN_EMPLOYEE_ID = "employee_id";
    public static final String COLUMN_SHIPMENT_HEADER_ID = "shipment_header_id";
    public static final String COLUMN_SHIPMENT_LINE_ID = "shipment_line_id";
    public static final String COLUMN_SHIP_TO_LOCATION_ID = "ship_to_location_id";
    public static final String COLUMN_VENDOR_ID = "vendor_id";
    public static final String COLUMN_VENDOR_SITE_ID = "vendor_site_id";
    public static final String COLUMN_TO_ORGANIZATION_ID = "to_organization_id";
    public static final String COLUMN_SOURCE_DOCUMENT_CODE = "source_document_code";
    public static final String COLUMN_PARENT_TRANSACTION_ID = "parent_transaction_id";
    public static final String COLUMN_PO_HEADER_ID = "po_header_id";
    public static final String COLUMN_PO_LINE_ID = "po_line_id";
    public static final String COLUMN_PO_LINE_LOCATION_ID = "po_line_location_id";
    public static final String COLUMN_PO_UNIT_PRICE = "po_unit_price";
    public static final String COLUMN_CURRENCY_CODE = "currency_code";
    public static final String COLUMN_CURRENCY_CONVERSION_TYPE = "currency_conversion_type";
    public static final String COLUMN_CURRENCY_CONVERSION_RATE = "currency_conversion_rate";
    public static final String COLUMN_CURRENCY_CONVERSION_DATE = "currency_conversion_date";
    public static final String COLUMN_PO_DISTRIBUTION_ID = "po_distribution_id";
    public static final String COLUMN_DESTINATION_TYPE_CODE = "destination_type_code";
    public static final String COLUMN_LOCATION_ID = "location_id";
    public static final String COLUMN_DELIVER_TO_LOCATION_ID = "deliver_to_location_id";
    public static final String COLUMN_INSPECTION_STATUS_CODE = "inspection_status_code";
    public static final String COLUMN_SUBINVENTORY ="subinventory";
    public static final String COLUMN_LOCATOR_ID ="locator_id";
    public static final String COLUMN_SHIPMENT_NUM ="shipment_num";
    public static final String COLUMN_USE_MTL_LOT = "use_mtl_lot";
    public static final String COLUMN_USE_MTL_SERIAL = "use_mtl_serial";
    public static final String COLUMN_GROUP_ID = "group_id";
    public static final String COLUMN_TRANSACTION_STATUS_CODE = "transaction_status_code";
    public static final String COLUMN_AUTO_TRANSACT_CODE = "auto_transact_code";
    public static final String COLUMN_RECEIPT_SOURCE_CODE = "receipt_source_code";
    public static final String COLUMN_VALIDATION_FLAG = "validation_flag";
    public static final String COLUMN_ORG_ID = "org_id";
    public static final String COLUMN_PRIMARY_QUANTITY = "primary_quantity";
    public static final String COLUMN_HEADER_INTERFACE_ID = "header_interface_id";
    public static final String COLUMN_VENDOR_SITE_CODE = "vendor_site_code";
    public static final String COLUMN_COMMENTS ="comments";
    public static final String COLUMN_SEGMENT1 = "segment1";



    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + " ( " +
                    COLUMN_INTERFACE_TRANSACTION_ID + " INTEGER, " +
                    COLUMN_LAST_UPDATE_DATE + " TEXT, " +
                    COLUMN_LAST_UPDATED_BY + " INTEGER, " +
                    COLUMN_CREATION_DATE + " TEXT, " +
                    COLUMN_CREATED_BY + " INTEGER, " +
                    COLUMN_TRANSACTION_TYPE + " TEXT, " +
                    COLUMN_TRANSACTION_DATE + " TEXT, " +
                    COLUMN_PROCESSING_STATUS_CODE + " TEXT, " +
                    COLUMN_PROCESSING_MODE_CODE + " TEXT, " +
                    COLUMN_QUANTITY + " REAL, " +
                    COLUMN_UNIT_OF_MEASURE + " TEXT, " +
                    COLUMN_ITEM_ID + " INTEGER, " +
                    COLUMN_ITEM_DESCRIPTION + " TEXT, " +
                    COLUMN_UOM_CODE + " TEXT, " +
                    COLUMN_EMPLOYEE_ID + " INTEGER, " +
                    COLUMN_SHIPMENT_HEADER_ID + " INTEGER, " +
                    COLUMN_SHIPMENT_LINE_ID + " INTEGER, " +
                    COLUMN_SHIP_TO_LOCATION_ID + " INTEGER, " +
                    COLUMN_VENDOR_ID + " INTEGER, " +
                    COLUMN_VENDOR_SITE_ID + " INTEGER, " +
                    COLUMN_TO_ORGANIZATION_ID + " INTEGER, " +
                    COLUMN_SOURCE_DOCUMENT_CODE + " TEXT, " +
                    COLUMN_PARENT_TRANSACTION_ID + " INTEGER, " +
                    COLUMN_PO_HEADER_ID + " INTEGER, " +
                    COLUMN_PO_LINE_ID + " INTEGER, " +
                    COLUMN_PO_LINE_LOCATION_ID + " INTEGER, " +
                    COLUMN_PO_UNIT_PRICE + " INTEGER, " +
                    COLUMN_CURRENCY_CODE + " TEXT, " +
                    COLUMN_CURRENCY_CONVERSION_TYPE + " TEXT, " +
                    COLUMN_CURRENCY_CONVERSION_RATE + " INTEGER, " +
                    COLUMN_CURRENCY_CONVERSION_DATE + " TEXT, " +
                    COLUMN_PO_DISTRIBUTION_ID + " INTEGER, " +
                    COLUMN_DESTINATION_TYPE_CODE + " TEXT, " +
                    COLUMN_LOCATION_ID + " INTEGER, " +
                    COLUMN_DELIVER_TO_LOCATION_ID + " INTEGER, " +
                    COLUMN_INSPECTION_STATUS_CODE + " TEXT, " +
                    COLUMN_SUBINVENTORY + " TEXT, " +
                    COLUMN_LOCATOR_ID + " INTEGER, " +
                    COLUMN_SHIPMENT_NUM + " TEXT, " +
                    COLUMN_USE_MTL_LOT + " INTEGER, " +
                    COLUMN_USE_MTL_SERIAL + " INTEGER, " +
                    COLUMN_GROUP_ID + " INTEGER, " +
                    COLUMN_TRANSACTION_STATUS_CODE + " TEXT, " +
                    COLUMN_AUTO_TRANSACT_CODE + " TEXT, " +
                    COLUMN_RECEIPT_SOURCE_CODE + " TEXT, " +
                    COLUMN_VALIDATION_FLAG + " TEXT, " +
                    COLUMN_ORG_ID + " INTEGER, " +
                    COLUMN_PRIMARY_QUANTITY + " INTEGER, " +
                    COLUMN_HEADER_INTERFACE_ID + " INTEGER, " +
                    COLUMN_VENDOR_SITE_CODE + " TEXT, " +
                    COLUMN_COMMENTS + " TEXT, " +
                    " PRIMARY KEY (" + COLUMN_INTERFACE_TRANSACTION_ID + " )" +
                    " ) ";

    public static final String UPDATE = COLUMN_INTERFACE_TRANSACTION_ID + " = ?";
    public static final String DELETE = COLUMN_INTERFACE_TRANSACTION_ID + " = ?";
    public static final String DELETE_HEADER_INTERFACE = COLUMN_HEADER_INTERFACE_ID + " = ?";

    public static final String SELECT = " SELECT * FROM rcv_transactions_interface rti, mtl_system_items msi where rti.item_id = msi.inventory_item_id AND rti.header_interface_id = ? ";
    public static final String SELECT_EXISTE_LINEA = " SELECT * FROM rcv_transactions_interface rti, mtl_system_items msi where  rti.item_id = msi.inventory_item_id and rti.header_interface_id = ?  and msi.segment1 = ? ";

    public static final String SELECT_BY_PARENT_TRANSACTION_ID =
            " SELECT * "
            + " FROM "
            + "     rcv_transactions_interface rti, "
            + "     mtl_system_items msi "
            + " WHERE "
            + "     rti.item_id = msi.inventory_item_id "
            + "     AND rti.parent_transaction_id = ? ";

    public static final String SELECT_BY_INTERFACE_TRANSACTION_ID =
            " SELECT * "
            + " FROM "
            + "     rcv_transactions_interface rti, "
            + "     mtl_system_items msi "
            + " WHERE "
            + "     rti.item_id = msi.inventory_item_id "
            + "     AND rti.interface_transaction_id = ? ";

    public static final String SELECT_ALL_BY_HEADER_INTERFACE_ID =
            " SELECT * "
            + " FROM "
            + "     rcv_transactions_interface rti, "
            + "     mtl_system_items msi "
            + " WHERE "
            + "     rti.item_id = msi.inventory_item_id "
            + "     AND rti.header_interface_id = ? ";

}
