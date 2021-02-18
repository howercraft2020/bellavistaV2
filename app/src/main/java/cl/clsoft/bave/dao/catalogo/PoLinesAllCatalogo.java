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

}
