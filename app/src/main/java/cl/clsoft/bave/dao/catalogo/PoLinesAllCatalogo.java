package cl.clsoft.bave.dao.catalogo;

public class PoLinesAllCatalogo {

    public static final String TABLE = "po_lines_all";
    public static final String CAMPO_PO_LINE_ID_PLA = "po_line_id";
    public static final String CAMPO_PO_HEADER_ID_PLA = "po_header_id";
    public static final String CAMPO_LINE_NUM_PLA = "line_num";
    public static final String CAMPO_ITEM_ID_PLA = "item_id";
    public static final String CAMPO_ITEM_DESCRIPTION_PLA = "item_description";
    public static final String CAMPO_UNIT_PRICE_PLA = "unit_price";
    public static final String CAMPO_QUANTITY_PLA = "quantity_pla";
    public static final String CAMPO_UNIT_MEAS_LOOKUP_CODE_PLA = "unit_meas_lookup_code";
    public static final String CAMPO_BASE_UOM_PLA = "base_uom";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ( " +
                    CAMPO_PO_LINE_ID_PLA + " INTEGER, " +
                    CAMPO_PO_HEADER_ID_PLA + " INTEGER ," +
                    CAMPO_LINE_NUM_PLA + " TEXT, " +
                    CAMPO_ITEM_ID_PLA + " INTEGER, " +
                    CAMPO_ITEM_DESCRIPTION_PLA + " TEXT, " +
                    CAMPO_UNIT_PRICE_PLA + " INTEGER, " +
                    CAMPO_QUANTITY_PLA + " INTEGER, " +
                    CAMPO_UNIT_MEAS_LOOKUP_CODE_PLA + " TEXT, " +
                    CAMPO_BASE_UOM_PLA+" TEXT " +
            " ) ";

}
