package cl.clsoft.bave.dao.catalogo;

public class MtlPhysicalInventoryTagsCatalogo {

    public static final String TABLE = "mtl_physical_inventory_tags";
    public static final String COLUMN_ID = "tag_id";
    public static final String COLUMN_PHYSICAL_INVENTORY_ID = "physical_inventory_id";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    public static final String COLUMN_SUBINVENTORY = "subinventory";
    public static final String COLUMN_LOCATOR_ID = "locator_id";
    public static final String COLUMN_LOT_NUMBER = "lot_number";
    public static final String COLUMN_LOT_EXPIRATION_DATE = "lot_expiration_date";
    public static final String COLUMN_SERIAL_NUM = "serial_num";
    public static final String COLUMN_SEGMENT1 = "segment1";
    public static final String COLUMN_PRIMARY_UOM_CODE = "primary_uom_code";
    public static final String COLUMN_COUNT = "primary_count";
    public static final String COLUMN_LAST_UPDATED = "last_updated";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LONG_DESCRIPTION = "long_description";
    public static final String COLUMN_LOCATOR_CODE = "cod_localizador";

    public static final String CREATE_TABLE =
            "CREATE TABLE "+ TABLE + "(" +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_PHYSICAL_INVENTORY_ID + " INTEGER, " +
                    COLUMN_ORGANIZATION_ID + " INTEGER, " +
                    COLUMN_INVENTORY_ITEM_ID + " INTEGER, " +
                    COLUMN_SUBINVENTORY + " TEXT, " +
                    COLUMN_LOCATOR_ID + " INTEGER, " +
                    COLUMN_LOT_NUMBER + " TEXT, " +
                    COLUMN_LOT_EXPIRATION_DATE + " TEXT, " +
                    COLUMN_SERIAL_NUM + " TEXT, " +
                    COLUMN_SEGMENT1 + " TEXT, " +
                    COLUMN_PRIMARY_UOM_CODE + " TEXT, " +
                    COLUMN_COUNT + " REAL, " +
                    COLUMN_LAST_UPDATED + " TEXT " +
                    ")";

    public static final String UPDATE = COLUMN_ID + " = ?";
    public static final String DELETE = COLUMN_ID + " = ?";
    public static final String DELETE_BY_PHYSICAL_INVENTORY_ID = COLUMN_PHYSICAL_INVENTORY_ID + " = ?";

    public static final String SELECT =
            " SELECT " +
                    "     a.*, " +
                    "     b.description, " +
                    "     b.long_description, " +
                    "     c.cod_localizador " +
                    " FROM " +
                    "     mtl_physical_inventory_tags a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id " +
                    "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
                    " WHERE " +
                    "     a.tag_id = ?";

    public static final String SELECT_ALL_BY_PHYSICAL_INVENTORY_ID =
            " SELECT " +
                    "     a.*, " +
                    "     b.description, " +
                    "     b.long_description, " +
                    "     c.cod_localizador " +
                    " FROM " +
                    "     mtl_physical_inventory_tags a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id " +
                    "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
                    " WHERE " +
                    "     a.physical_inventory_id = ?";

    public static final String SELECT_ALL_BY_PHYSICAL_INVENTORY_ID_SUBINVENTORY =
            " SELECT " +
            "     a.*, " +
            "     b.description, " +
            "     b.long_description, " +
            "     c.cod_localizador " +
            " FROM " +
            "     mtl_physical_inventory_tags a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id " +
            "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
            " WHERE " +
            "     a.physical_inventory_id = ?" +
            "     AND a.subinventory = ?";

    public static final String SELECT_ALL_INVENTARIADOS_BY_INVENTORY_SUBINVENTORY =
            " SELECT " +
                    "     a.*, " +
                    "     b.description, " +
                    "     b.long_description, " +
                    "     c.cod_localizador " +
                    " FROM " +
                    "     mtl_physical_inventory_tags a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id " +
                    "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
                    " WHERE " +
                    "     a.physical_inventory_id = ?" +
                    "     AND a.subinventory = ?" +
                    "     AND  a.primary_count is not null " +
                    "     AND  a.last_updated is not null ";

    public static final String SELECT_ALL_INVENTARIADOS_BY_INVENTORY =
            " SELECT " +
                    "     a.*, " +
                    "     b.description, " +
                    "     b.long_description, " +
                    "     c.cod_localizador " +
                    " FROM " +
                    "     mtl_physical_inventory_tags a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id " +
                    "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
                    " WHERE " +
                    "     a.physical_inventory_id = ?" +
                    "     AND  a.primary_count is not null " +
                    "     AND  a.last_updated is not null ";

    public static final String SELECT_ALL_NOINVENTARIADOS_BY_INVENTORY_SUBINVENTORY =
            " SELECT " +
            "     a.*, " +
            "     b.description, " +
            "     b.long_description, " +
            "     c.cod_localizador " +
            " FROM " +
            "     mtl_physical_inventory_tags a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id " +
            "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
            " WHERE " +
            "     a.physical_inventory_id = ?" +
            "     AND a.subinventory = ?" +
            "     AND  a.primary_count is null " +
            "     AND  a.last_updated is null ";

    public static final String SELECT_LOCATOR_BY_INVENTORY_SUBINVENTORY =
            " SELECT " +
            "     distinct(c.cod_localizador) " +
            " FROM " +
            "     mtl_physical_inventory_tags a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id " +
            "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
            " WHERE " +
            "     a.locator_id is not null " +
            "     AND a.physical_inventory_id = ?" +
            "     AND a.subinventory = ?";

    public static final String SELECT_SEGMENT1_BY_INVENTORY_SUBINVENTORY =
            " SELECT " +
            "     DISTINCT(segment1) " +
            " FROM " +
            "     MTL_PHYSICAL_INVENTORY_TAGS " +
            " WHERE " +
            "     physical_inventory_id = ? " +
            "     AND subinventory = ? " +
            " ORDER BY " +
            "     segment1 ";

    public static final String SELECT_SEGMENT1_BY_INVENTORY_SUBINVENTORY_LOCATOR =
            " SELECT " +
                    "     DISTINCT(segment1) " +
                    " FROM " +
                    "     MTL_PHYSICAL_INVENTORY_TAGS " +
                    " WHERE " +
                    "     physical_inventory_id = ? " +
                    "     AND subinventory = ? " +
                    "     AND locator_id = ? " +
                    " ORDER BY " +
                    "     segment1 ";

    public static final String SELECT_SEGMENT1_BY_INVENTORY_SUBINVENTORY_LOCATORNULL =
            " SELECT " +
                    "     DISTINCT(segment1) " +
                    " FROM " +
                    "     MTL_PHYSICAL_INVENTORY_TAGS " +
                    " WHERE " +
                    "     physical_inventory_id = ? " +
                    "     AND subinventory = ? " +
                    "     AND locator_id is null " +
                    " ORDER BY " +
                    "     segment1 ";

    public static final String SELECT_SERIES_BY_INVENTORY_SUBINVENTORY =
            " SELECT " +
            "     DISTINCT(serial_num) " +
            " FROM " +
            "     MTL_PHYSICAL_INVENTORY_TAGS " +
            " WHERE " +
            "     physical_inventory_id = ? " +
            "     AND subinventory = ? " +
            " ORDER BY " +
            "     serial_num ";

    public static final String SELECT_SERIES_BY_INVENTORY_SUBINVENTORY_LOCATOR =
            " SELECT " +
                    "     DISTINCT(serial_num) " +
                    " FROM " +
                    "     MTL_PHYSICAL_INVENTORY_TAGS " +
                    " WHERE " +
                    "     physical_inventory_id = ? " +
                    "     AND subinventory = ? " +
                    "     AND locator_id = ? " +
                    " ORDER BY " +
                    "     serial_num ";

    public static final String SELECT_SERIES_BY_INVENTORY_SUBINVENTORY_LOCATOR_SEGMENT =
            " SELECT " +
                    "     DISTINCT(serial_num) " +
                    " FROM " +
                    "     MTL_PHYSICAL_INVENTORY_TAGS " +
                    " WHERE " +
                    "     physical_inventory_id = ? " +
                    "     AND subinventory = ? " +
                    "     AND locator_id = ? " +
                    "     AND segment1 = ? " +
                    " ORDER BY " +
                    "     serial_num ";

    public static final String SELECT_LOTES_BY_INVENTORY_SUBINVENTORY =
            " SELECT " +
            "     DISTINCT(lot_number) " +
            " FROM " +
            "     MTL_PHYSICAL_INVENTORY_TAGS " +
            " WHERE " +
            "     physical_inventory_id = ? " +
            "     AND subinventory = ? " +
            " ORDER BY " +
            "     lot_number ";

    public static final String SELECT_LOTES_BY_INVENTORY_SUBINVENTORY_LOCATOR_SEGMENT =
            " SELECT " +
            "     DISTINCT(lot_number) " +
            " FROM " +
            "     MTL_PHYSICAL_INVENTORY_TAGS " +
            " WHERE " +
            "     physical_inventory_id = ? " +
            "     AND subinventory = ? " +
            "     AND locator_id = ? " +
            "     AND segment1 = ? " +
            " ORDER BY " +
            "     lot_number ";

    public static final String SELECT_VENCIMIENTO_BY_INVENTORY_SUBINVENTORY_LOCATOR_SEGMENT =
            " SELECT " +
                    "     DISTINCT(lot_expiration_date) " +
                    " FROM " +
                    "     MTL_PHYSICAL_INVENTORY_TAGS " +
                    " WHERE " +
                    "     physical_inventory_id = ? " +
                    "     AND subinventory = ? " +
                    "     AND locator_id = ? " +
                    "     AND segment1 = ? " +
                    " ORDER BY " +
                    "     lot_expiration_date ";

    public static final String SELECT_ALL_BY_INVENTORY_SUBINVENTORY_SEGMENT_SERIE_LOTE =
            " SELECT " +
                    "     a.*, " +
                    "     b.description, " +
                    "     b.long_description, " +
                    "     c.cod_localizador " +
                    " FROM " +
                    "     mtl_physical_inventory_tags a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id " +
                    "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
                    " WHERE " +
                    "     a.physical_inventory_id = ?" +
                    "     AND a.subinventory = ?" +
                    "     AND  a.locator_id = ?" +
                    "     AND a.segment1 = ? " +
                    "     AND ifnull(a.serial_num, '') = ? " +
                    "     AND ifnull(a.lot_number, '') = ?" +
                    "     AND ifnull(a.lot_expiration_date, '') = ? ";

    public static final String SELECT_ALL_BY_INVENTORY_SUBINVENTORY_SEGMENT_SERIE_LOTE_LOCATOR_NULL =
            " SELECT " +
                    "     a.*, " +
                    "     b.description, " +
                    "     b.long_description, " +
                    "     c.cod_localizador " +
                    " FROM " +
                    "     mtl_physical_inventory_tags a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id " +
                    "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
                    " WHERE " +
                    "     a.physical_inventory_id = ?" +
                    "     AND a.subinventory = ?" +
                    "     AND a.segment1 = ? " +
                    "     AND ifnull(a.serial_num, '') = ? " +
                    "     AND ifnull(a.lot_number, '') = ?" +
                    "     AND ifnull(a.lot_expiration_date, '') = ? ";

}
