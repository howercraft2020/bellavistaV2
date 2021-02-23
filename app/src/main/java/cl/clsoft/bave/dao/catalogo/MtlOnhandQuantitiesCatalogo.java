package cl.clsoft.bave.dao.catalogo;

public class MtlOnhandQuantitiesCatalogo {

    public static final String TABLE = "mtl_onhand_quantities";
    public static final String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_SUBINVENTORY_CODE = "subinventory_code";
    public static final String COLUMN_LOCATOR_ID = "locator_id";
    public static final String COLUMN_LOT_NUMBER = "lot_number";
    public static final String COLUMN_SERIAL_NUMBER = "serial_number";
    public static final String COLUMN_PRIMARY_TRANSACTION_QUANTITY = "primary_transaction_quantity";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_STATUS_ID = "status_id";

    public static final String CREATE_TABLE =
                "CREATE TABLE "+ TABLE + "(" +
                        COLUMN_INVENTORY_ITEM_ID + " INTEGER, " +
                        COLUMN_ORGANIZATION_ID + " INTEGER, " +
                        COLUMN_SUBINVENTORY_CODE + " TEXT, " +
                        COLUMN_LOCATOR_ID + " INTEGER, " +
                        COLUMN_LOT_NUMBER + " TEXT, " +
                        COLUMN_SERIAL_NUMBER + " TEXT, " +
                        COLUMN_PRIMARY_TRANSACTION_QUANTITY + " INTEGER, " +
                        COLUMN_USER_ID + " INTEGER, " +
                        COLUMN_STATUS_ID + " INTEGER " +
                        ")";


        public static final String SELECT = "SELECT moq.* FROM mtl_onhand_quantities moq, mtl_system_items msi, localizador loc " +
                                        "WHERE moq.inventory_item_id = msi.inventory_item_id " +
                                        "AND loc.id_localizador = moq.locator_id " +
                                        "AND msi.segment1 = ? AND IFNULL(moq.lot_number,'') = ? " +
                                        "AND moq.subinventory_code = ?";
                                        //"AND IFNULL(loc.cod_localizador,'') = ?";



}
