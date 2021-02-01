package cl.clsoft.bave.dao.catalogo;

public class MtlSystemItemsCatalogo {

    public static final String TABLA = "mtl_system_items";
    public static final String CAMPO_INVENTORY_ITEM_ID_MSI = "inventory_item_id";
    public static final String CAMPO_DESCRIPTION_MSI = "description";
    public static final String CAMPO_LONG_DESCRIPTION_MSI = "long_description";
    public static final String CAMPO_SEGMENT1_MSI = "segment1";
    public static final String CAMPO_PRIMARY_UOM_CODE_MSI = "primary_uom_code";
    public static final String CAMPO_LOT_CONTROL_CODE_MSI = "lot_control_code";
    public static final String CAMPO_SHELF_LIFE_CODE_MSI = "shelf_life_code";
    public static final String CAMPO_SERIAL_NUMBER_CONTROL_CODE_MSI = "serial_number_control_code";
    public static final String CREAR_TABLA = "CREATE TABLE "+TABLA +"("+CAMPO_INVENTORY_ITEM_ID_MSI+" INTEGER, "+CAMPO_DESCRIPTION_MSI+" TEXT, "+CAMPO_LONG_DESCRIPTION_MSI+" TEXT, "+CAMPO_SEGMENT1_MSI+" TEXT, "+CAMPO_PRIMARY_UOM_CODE_MSI+" TEXT, "+CAMPO_LOT_CONTROL_CODE_MSI+" TEXT, "+CAMPO_SHELF_LIFE_CODE_MSI+" TEXT, "+CAMPO_SERIAL_NUMBER_CONTROL_CODE_MSI+" TEXT) ";

}
