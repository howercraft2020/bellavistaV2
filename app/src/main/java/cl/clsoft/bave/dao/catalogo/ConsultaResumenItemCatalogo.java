package cl.clsoft.bave.dao.catalogo;

public class ConsultaResumenItemCatalogo {

    public static final String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    public static final String COLUMN_SEGMENT1 = "segment1";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_UNIDAD = "unidad";

    public static final String SELECT_ALL_BY_SUBINVENTORY =
            " SELECT "
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
                    + "     a.inventory_item_id "
                    + " ORDER BY "
                    + "     c.description ";

}
