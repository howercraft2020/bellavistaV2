package cl.clsoft.bave.dao.catalogo;

public class PoDistributionsAllCatalogo {

    public static final String TABLE = "po_distributions_all";
    public static final String COLUMN_PO_DISTRIBUTION_ID = "po_distribution_id";
    public static final String COLUMN_LINE_LOCATION_ID = "line_location_id";
    public static final String COLUMN_PO_LINE_ID = "po_line_id";
    public static final String COLUMN_PO_HEADER_ID = "po_header_id";
    public static final String COLUMN_DISTRIBUTION_NUM = "distribution_num";
    public static final String COLUMN_RATE_DATE = "rate_date";
    public static final String COLUMN_RATE = "rate";
    public static final String COLUMN_DESTINATION_SUBINVENTORY = "destination_subinventory";
    public static final String COLUMN_DELIVER_TO_LOCATION_ID = "deliver_to_location_id";
    public static final String COLUMN_QUANTITY_ORDERED = "quantity_ordered";
    public static final String COLUMN_QUANTITY_DELIVERED = "quantity_delivered";
    public static final String COLUMN_QUANTITY_BILLED = "quantity_billed";
    public static final String COLUMN_QUANTITY_CANCELLED = "quantity_cancelled";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + "( " +
                    COLUMN_PO_DISTRIBUTION_ID +" INTEGER, " +
                    COLUMN_LINE_LOCATION_ID + " INTEGER, " +
                    COLUMN_PO_LINE_ID + " INTEGER, " +
                    COLUMN_PO_HEADER_ID + " INTEGER, " +
                    COLUMN_DISTRIBUTION_NUM + " INTEGER, " +
                    COLUMN_RATE_DATE + " TEXT, " +
                    COLUMN_RATE + " TEXT, " +
                    COLUMN_DESTINATION_SUBINVENTORY +" TEXT, " +
                    COLUMN_DELIVER_TO_LOCATION_ID + " INTEGER, " +
                    COLUMN_QUANTITY_ORDERED + " INTEGER, " +
                    COLUMN_QUANTITY_DELIVERED + " INTEGER, " +
                    COLUMN_QUANTITY_BILLED + " INTEGER, " +
                    COLUMN_QUANTITY_CANCELLED + " INTEGER" +
            ")";

    public static final String DELETE = COLUMN_PO_HEADER_ID + " = ?";

    public static final String SELECT_BY_ID = "SELECT * FROM " + TABLE + " WHERE " + COLUMN_PO_DISTRIBUTION_ID + " = ?";

}
