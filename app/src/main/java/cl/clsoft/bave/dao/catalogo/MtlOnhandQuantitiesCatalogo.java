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
                        COLUMN_PRIMARY_TRANSACTION_QUANTITY + " REAL, " +
                        COLUMN_USER_ID + " INTEGER, " +
                        COLUMN_STATUS_ID + " INTEGER " +
                        ")";

    public static final String SELECT = "SELECT moq.* FROM mtl_onhand_quantities moq, mtl_system_items msi, localizador loc " +
            "WHERE moq.inventory_item_id = msi.inventory_item_id " +
            "AND loc.id_localizador = moq.locator_id " +
            "AND msi.segment1 = ? AND ifnull(moq.lot_number,'') = ? " +
            "AND moq.subinventory_code = ? AND ifnull(loc.cod_localizador,'') = ?";

    public static final String SELECT_LOCATOR_NULL = "SELECT moq.* FROM mtl_onhand_quantities moq, mtl_system_items msi " +
            "WHERE moq.inventory_item_id = msi.inventory_item_id " +
            "AND msi.segment1 = ? AND ifnull(moq.lot_number,'') = ? " +
            "AND moq.subinventory_code = ?";

    public static final String SELECT_QUANTITY = "SELECT SUM(moq.primary_transaction_quantity) FROM mtl_onhand_quantities moq, mtl_system_items msi, localizador loc " +
            "WHERE moq.inventory_item_id = msi.inventory_item_id " +
            "AND loc.id_localizador = moq.locator_id " +
            "AND msi.segment1 = ? AND ifnull(moq.lot_number,'') = ? " +
            "AND moq.subinventory_code = ? AND ifnull(loc.cod_localizador,'') = ?";

    public static final String SELECT_QUANTITY_LOCATOR_NULL = "SELECT SUM(moq.primary_transaction_quantity) FROM mtl_onhand_quantities moq, mtl_system_items msi " +
            "WHERE moq.inventory_item_id = msi.inventory_item_id " +
            "AND msi.segment1 = ? AND ifnull(moq.lot_number,'') = ? " +
            "AND moq.subinventory_code = ?";

    public static final String SELECT_ALL = "SELECT moq.* FROM mtl_onhand_quantities moq, mtl_system_items msi, localizador loc " +
            "WHERE moq.inventory_item_id = msi.inventory_item_id " +
            "AND loc.id_localizador = moq.locator_id " +
            "AND msi.segment1 = ? AND ifnull(moq.lot_number,'') = ? " +
            "AND moq.subinventory_code = ? AND ifnull(loc.cod_localizador,'') = ?";

    public static final String SELECT_SERIE = "SELECT * FROM mtl_onhand_quantities moq, mtl_system_items msi, localizador loc " +
            "WHERE moq.inventory_item_id = msi.inventory_item_id " +
            "AND loc.id_localizador = moq.locator_id " +
            "AND msi.segment1 = ? AND ifnull(moq.lot_number,'') = ? " +
            "AND moq.subinventory_code = ? AND ifnull(loc.cod_localizador,'') = ? " +
            "AND moq.serial_number = ? ";

    public static final String SELECT_SEGMENT1_BY_INVENTORY_SUBINVENTORY =
            " SELECT " +
                    " DISTINCT(msi.segment1) " +
                    " FROM " +
                    " mtl_onhand_quantities moq, mtl_system_items msi " +
                    " WHERE moq.inventory_item_id = msi.inventory_item_id " +
                    " AND subinventory_code = ? " +
                    " ORDER BY " +
                    " segment1 ";

    public static final String SELECT_SEGMENT1_BY_INVENTORY_SUBINVENTORY_LOCATOR =
            " SELECT " +
                    " DISTINCT(msi.segment1) " +
                    " FROM " +
                    " mtl_onhand_quantities moq, mtl_system_items msi " +
                    " WHERE moq.inventory_item_id = msi.inventory_item_id " +
                    " AND subinventory_code = ? " +
                    " AND locator_id = ? " +
                    " ORDER BY " +
                    " segment1 ";

    public static final String SELECT_SEGMENT1_BY_INVENTORY_SUBINVENTORY_LOCATORNULL =
            " SELECT " +
                    " DISTINCT(msi.segment1) " +
                    " FROM " +
                    " mtl_onhand_quantities moq, mtl_system_items msi " +
                    " WHERE moq.inventory_item_id = msi.inventory_item_id " +
                    " AND moq.subinventory_code = ? " +
                    " AND moq.locator_id is null " +
                    " ORDER BY " +
                    " msi.segment1 ";

    public static final String SELECT_LOT_BY_INVENTORY_SUBINVENTORY =
            " SELECT " +
                    " DISTINCT(moq.lot_number) " +
                    " FROM " +
                    " mtl_onhand_quantities moq, mtl_system_items msi " +
                    " WHERE moq.inventory_item_id = msi.inventory_item_id " +
                    " AND moq.subinventory_code = ? " +
                    " AND msi.segment1 = ? " ;

    public static final String SELECT_LOT_BY_INVENTORY_SUBINVENTORY_LOCATOR =
            " SELECT " +
                    " DISTINCT(moq.lot_number) " +
                    " FROM " +
                    " mtl_onhand_quantities moq, mtl_system_items msi " +
                    " WHERE moq.inventory_item_id = msi.inventory_item_id " +
                    " AND moq.subinventory_code = ? " +
                    " AND moq.locator_id = ? " +
                    " AND msi.segment1 = ? ";

    public static final String SELECT_LOT_BY_INVENTORY_SUBINVENTORY_LOCATORNULL =
            " SELECT " +
                    " DISTINCT(moq.lot_number) " +
                    " FROM " +
                    " mtl_onhand_quantities moq, mtl_system_items msi " +
                    " WHERE moq.inventory_item_id = msi.inventory_item_id " +
                    " AND subinventory_code = ? " +
                    " AND locator_id is null " +
                    " AND msi.segment1 = ? " ;


}
