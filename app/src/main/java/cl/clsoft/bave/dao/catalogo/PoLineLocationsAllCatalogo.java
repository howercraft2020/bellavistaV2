package cl.clsoft.bave.dao.catalogo;

public class PoLineLocationsAllCatalogo {

    public static final String TABLA = "po_line_locations_all";
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
    public static final String CREAR_TABLA = "CREATE TABLE "+ TABLA +"("+CAMPO_LINE_LOCATION_ID_PLL+" INTEGER, "+CAMPO_PO_LINE_ID_PLL+" INTEGER, "+CAMPO_PO_HEADER_ID_PLL+" INTEGER, "+CAMPO_QUANTITY_PLL+" INTEGER, "+CAMPO_QUANTITY_RECEIVED_PLL+" INTEGER, "+CAMPO_QUANTITY_CANCELLED_PLL+" INTEGER, "+CAMPO_QUANTITY_BILLED_PLL+" INTEGER, "+CAMPO_SHIPMENT_NUM_PLL+" INTEGER, "+CAMPO_SHIP_TO_LOCATION_ID_PLL+" INTEGER, "+CAMPO_QTY_RCV_TOLERANCE_PLL+" INTEGER, "+CAMPO_ORG_ID_PLL+" INTEGER) ";

}
