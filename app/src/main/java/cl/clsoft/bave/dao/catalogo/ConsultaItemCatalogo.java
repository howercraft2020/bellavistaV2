package cl.clsoft.bave.dao.catalogo;

public class ConsultaItemCatalogo {

    public static final String COLUMN_SUBINVENTORY_CODE = "subinventory_code";
    public static final String COLUMN_LOCATOR_CODE = "cod_localizador";
    public static final String COLUMN_LOT_NUMBER = "lot_number";
    public static final String COLUMN_SERIAL_NUMBER = "serial_number";
    public static final String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    public static final String COLUMN_SEGMENT1 = "segment1";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_UNIDAD = "unidad";

    public static final String SELECT_ALL_BY_INVENTORY_ITEM_ID =
            " SELECT "
            + "     a.subinventory_code, "
            + "     b.cod_localizador, "
            + "     a.lot_number, "
            + "     a.serial_number, "
            + "     a.inventory_item_id, "
            + "     c.segment1, "
            + "     c.description, "
            + "     c.primary_uom_code as unidad, "
            + "     sum(primary_transaction_quantity) as quantity "
            + " FROM "
            + "     mtl_onhand_quantities a LEFT JOIN localizador b ON a.locator_id = b.id_localizador "
            + "     JOIN mtl_system_items c ON a.inventory_item_id = c.inventory_item_id "
            + " WHERE "
            + "     a.inventory_item_id = ? "
            + " GROUP BY "
            + "     a.subinventory_code, "
            + "     a.locator_id, "
            + "     a.lot_number, "
            + "     a.serial_number"
            + " ORDER BY "
            + "     a.subinventory_code, "
            + "     b.cod_localizador";

    public static final String SELECT_ALL_BY_SUBINVENTORY =
            " SELECT "
            + "     a.subinventory_code, "
            + "     b.cod_localizador, "
            + "     a.lot_number, "
            + "     a.serial_number, "
            + "     a.inventory_item_id, "
            + "     c.segment1, "
            + "     c.description, "
            + "     c.primary_uom_code as unidad, "
            + "     sum(primary_transaction_quantity) as quantity "
            + " FROM "
            + "     mtl_onhand_quantities a LEFT JOIN localizador b ON a.locator_id = b.id_localizador "
            + "     JOIN mtl_system_items c ON a.inventory_item_id = c.inventory_item_id "
            + " WHERE "
            + "     a.subinventory_code = ? "
            + " GROUP BY "
            + "     a.subinventory_code, "
            + "     a.locator_id, "
            + "     a.lot_number, "
            + "     a.serial_number"
            + " ORDER BY "
            + "     a.subinventory_code, "
            + "     b.cod_localizador";

    public static final String SELECT_ALL_BY_SUBINVENTORY_ITEM =
            " SELECT "
                    + "     a.subinventory_code, "
                    + "     b.cod_localizador, "
                    + "     a.lot_number, "
                    + "     a.serial_number, "
                    + "     a.inventory_item_id, "
                    + "     c.segment1, "
                    + "     c.description, "
                    + "     c.primary_uom_code as unidad, "
                    + "     sum(primary_transaction_quantity) as quantity "
                    + " FROM "
                    + "     mtl_onhand_quantities a LEFT JOIN localizador b ON a.locator_id = b.id_localizador "
                    + "     JOIN mtl_system_items c ON a.inventory_item_id = c.inventory_item_id "
                    + " WHERE "
                    + "     a.subinventory_code = ? "
                    + "     AND a.inventory_item_id = ? "
                    + " GROUP BY "
                    + "     a.subinventory_code, "
                    + "     a.locator_id, "
                    + "     a.lot_number, "
                    + "     a.serial_number"
                    + " ORDER BY "
                    + "     a.subinventory_code, "
                    + "     b.cod_localizador";

}
