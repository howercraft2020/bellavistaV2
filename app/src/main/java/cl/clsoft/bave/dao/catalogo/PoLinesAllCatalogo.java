package cl.clsoft.bave.dao.catalogo;

public class PoLinesAllCatalogo {

    public static final String TABLE = "po_lines_all";
    public static final String CAMPO_PO_LINE_ID = "po_line_id";
    public static final String CAMPO_PO_HEADER_ID = "po_header_id";
    public static final String CAMPO_LINE_NUM = "line_num";
    public static final String CAMPO_ITEM_ID = "item_id";
    public static final String CAMPO_ITEM_DESCRIPTION = "item_description";
    public static final String CAMPO_UNIT_PRICE = "unit_price";
    public static final String CAMPO_QUANTITY = "quantity_pla";
    public static final String CAMPO_UNIT_MEAS_LOOKUP_CODE = "unit_meas_lookup_code";
    public static final String CAMPO_BASE_UOM = "base_uom";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ( " +
                    CAMPO_PO_LINE_ID + " INTEGER, " +
                    CAMPO_PO_HEADER_ID + " INTEGER ," +
                    CAMPO_LINE_NUM + " TEXT, " +
                    CAMPO_ITEM_ID + " INTEGER, " +
                    CAMPO_ITEM_DESCRIPTION + " TEXT, " +
                    CAMPO_UNIT_PRICE + " INTEGER, " +
                    CAMPO_QUANTITY + " INTEGER, " +
                    CAMPO_UNIT_MEAS_LOOKUP_CODE + " TEXT, " +
                    CAMPO_BASE_UOM+" TEXT " +
            " ) ";

    public static final String DELETE = CAMPO_PO_HEADER_ID + " = ?";

    public static final String SELECT_BY_ID = "SELECT * FROM " + TABLE + " WHERE " + CAMPO_PO_LINE_ID + " = ?";

    public static String SELECT_LINES = "SELECT pla.* FROM po_lines_all pla, mtl_system_items msi " +
                                        "WHERE pla.item_id = msi.inventory_item_id " +
                                        "AND msi.inventory_item_id = ? "+
                                        "AND pla.po_header_id = ?";

    public static String SELECT_VALIDA_LINEA = "SELECT pla.* FROM po_lines_all pla, mtl_system_items msi " +
            "WHERE pla.item_id = msi.inventory_item_id " +
            "AND msi.inventory_item_id = ? "+
            "AND pla.po_header_id = ? "+
            "AND pla.line_num = ?";

}
