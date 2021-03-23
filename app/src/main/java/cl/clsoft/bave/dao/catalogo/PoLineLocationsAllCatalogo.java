package cl.clsoft.bave.dao.catalogo;

public class PoLineLocationsAllCatalogo {

    public static final String TABLE = "po_line_locations_all";
    public static final String COLUMN_LINE_LOCATION_ID = "line_location_id";
    public static final String COLUMN_PO_LINE_ID = "po_line_id";
    public static final String COLUMN_PO_HEADER_ID = "po_header_id";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_QUANTITY_RECEIVED = "quantity_received";
    public static final String COLUMN_QUANTITY_CANCELLED = "quantity_cancelled";
    public static final String COLUMN_QUANTITY_BILLED = "quantity_billed";
    public static final String COLUMN_SHIPMENT_NUM = "shipment_num";
    public static final String COLUMN_SHIP_TO_LOCATION_ID = "ship_to_location_id";
    public static final String COLUMN_QTY_RCV_TOLERANCE = "qty_rcv_tolerance";
    public static final String COLUMN_ORG_ID = "org_id";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + " ( " +
                    COLUMN_LINE_LOCATION_ID + " INTEGER, " +
                    COLUMN_PO_LINE_ID + " INTEGER, " +
                    COLUMN_PO_HEADER_ID + " INTEGER, " +
                    COLUMN_QUANTITY + " INTEGER, " +
                    COLUMN_QUANTITY_RECEIVED + " INTEGER, " +
                    COLUMN_QUANTITY_CANCELLED + " INTEGER, " +
                    COLUMN_QUANTITY_BILLED + " INTEGER, " +
                    COLUMN_SHIPMENT_NUM + " INTEGER, " +
                    COLUMN_SHIP_TO_LOCATION_ID + " INTEGER, " +
                    COLUMN_QTY_RCV_TOLERANCE + " INTEGER, " +
                    COLUMN_ORG_ID +" INTEGER" +
            ")";

    public static final String DELETE = COLUMN_PO_HEADER_ID + " = ?";

    public static final String SELECT_BY_ID = "SELECT * FROM " + TABLE + " WHERE " + COLUMN_LINE_LOCATION_ID + " = ?";

}
