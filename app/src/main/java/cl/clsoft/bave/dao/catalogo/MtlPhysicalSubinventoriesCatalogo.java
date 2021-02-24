package cl.clsoft.bave.dao.catalogo;

public class MtlPhysicalSubinventoriesCatalogo {

    public static final String TABLE = "mtl_physical_subinventories";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_PHYSICAL_INVENTORY_ID = "physical_inventory_id";
    public static final String COLUMN_SUBINVENTORY = "subinventory";
    public static final String COLUMN_SUBINVENTORY_DESCRIPTION = "description";

    public static final String CREATE_TABLE =
            "CREATE TABLE "+ TABLE + "(" +
                    COLUMN_ORGANIZATION_ID + " INTEGER, " +
                    COLUMN_PHYSICAL_INVENTORY_ID + " INTEGER, " +
                    COLUMN_SUBINVENTORY + " TEXT " +
                    ")";

    public static final String DELETE_BY_PHYSICAL_INVENTORY_ID = COLUMN_PHYSICAL_INVENTORY_ID + " = ?";
    public static final String SELECT =
            " SELECT " +
            "     a.*, " +
            "     b.description " +
            " FROM " +
            "     mtl_physical_subinventories a LEFT JOIN subinventario b ON a.subinventory = b.cod_subinventario " +
            " WHERE " +
            "     physical_inventory_id = ? ";


}
