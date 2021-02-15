package cl.clsoft.bave.dao.catalogo;

public class DatosRecepcionCatalogo {

    public static final String COLUMN_UNIT_MEAS_LOOKUP_CODE = "unit_meas_lookup_code";
    public static final String COLUMN_ITEM_ID = "item_id";
    public static final String COLUMN_ITEM_DESCRIPTION = "item_description";
    public static final String COLUMN_BASE_UOM = "base_uom";
    public static final String COLUMN_VENDOR_ID = "vendor_id";
    public static final String COLUMN_VENDOR_SITE_ID = "vendor_site_id";
    public static final String COLUMN_PO_HEADER_ID = "po_header_id";
    public static final String COLUMN_PO_LINE_ID = "po_line_id";
    public static final String COLUMN_LINE_LOCATION_ID = "line_location_id";
    public static final String COLUMN_UNIT_PRICE = "unit_price";
    public static final String COLUMN_CURRENCY_CODE = "currency_code";
    public static final String COLUMN_RATE_TYPE = "rate_type";
    public static final String COLUMN_RATE = "rate";
    public static final String COLUMN_RATE_DATE = "rate_date";
    public static final String COLUMN_PO_DISTRIBUTION_ID = "po_distribution_id";
    public static final String COLUMN_VENDOR_SITE_CODE = "vendor_site_code";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_QUANTITY_RECEIVED = "quantity_received";
    public static final String COLUMN_QUANTITY_CANCELLED = "quantity_cancelled";
    public static final String COLUMN_QTY_RCV_TOLERANCE = "qty_rcv_tolerance";

    public static final String SELECT = "SELECT pla.unit_meas_lookup_code, pla.item_id, pla.item_description, pla.base_uom, pha.vendor_id, " +
                                        "pha.vendor_site_id, pha.po_header_id, pla.po_line_id, pll.line_location_id, pla.unit_price, " +
                                        "pha.currency_code, pha.rate_type, pha.rate, pha.rate_date, pda.po_distribution_id, pha.vendor_site_code, "+
                                        "pll.quantity, pll.quantity_received, pll.quantity_cancelled, pll.qty_rcv_tolerance "+
                                        "FROM PO_HEADERS_ALL pha, PO_LINES_ALL pla, PO_LINE_LOCATIONS_ALL pll, MTL_SYSTEM_ITEMS msi, PO_DISTRIBUTIONS_ALL pda "+
                                        "WHERE pla.ITEM_ID = msi.INVENTORY_ITEM_ID AND pha.po_header_id = pla.po_header_id AND pha.po_header_id = pll.po_header_id "+
                                        "AND pla.po_line_id = pll.po_line_id AND pll.line_location_id = pda.line_location_id AND pla.po_line_id = pda.po_line_id "+
                                        "AND pha.po_header_id = pda.po_header_id AND msi.segment1 = ? AND pha.segment1 = ? AND pha.receipt_num = ?";






}
