package cl.clsoft.bave.dao.catalogo;

public class PoDistributionsAllCatalogo {

    public static final String TABLA = "po_distributions_all";
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
    public static final String CREAR_TABLA = "CREATE TABLE "+TABLA +"("+CAMPO_PO_DISTRIBUTION_ID_PDA+" INTEGER, "+CAMPO_LINE_LOCATION_ID_PDA+" INTEGER, "+CAMPO_PO_LINE_ID_PDA+" INTEGER, "+CAMPO_PO_HEADER_ID_PDA+" INTEGER, "+CAMPO_DISTRIBUTION_NUM_PDA+" INTEGER, "+CAMPO_RATE_DATE_PDA+" TEXT, "+CAMPO_RATE_PDA+" TEXT, "+CAMPO_DESTINATION_SUBINVENTORY_PDA+" TEXT, "+CAMPO_DELIVER_TO_LOCATION_ID_PDA+" INTEGER, "+ CAMPO_QUANTITY_ORDERED_PDA+" INTEGER, "+CAMPO_QUANTITY_DELIVERED_PDA+" INTEGER, "+CAMPO_QUANTITY_BILLED_PDA+" INTEGER, "+CAMPO_QUANTITY_CANCELLED_PDA+" INTEGER) ";

}
