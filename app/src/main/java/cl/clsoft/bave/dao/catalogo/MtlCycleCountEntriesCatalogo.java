package cl.clsoft.bave.dao.catalogo;

public class MtlCycleCountEntriesCatalogo {

    public static final String TABLE = "mtl_cycle_count_entries";
    public static final String COLUMN_ID = "cycle_count_entry_id";
    public static final String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    public static final String COLUMN_SUBINVENTORY = "subinventory";
    public static final String COLUMN_ENTRY_STATUS_CODE = "entry_status_code";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_CYCLE_COUNT_HEADER_ID = "cycle_count_header_id";
    public static final String COLUMN_LOCATOR_ID = "locator_id";
    public static final String COLUMN_LOT_NUMBER = "lot_number";
    public static final String COLUMN_SEGMENT1 = "segment1";
    public static final String COLUMN_PRIMARY_UOM_CODE = "primary_uom_code";
    public static final String COLUMN_SERIAL_NUMBER = "serial_number";
    public static final String COLUMN_COUNT = "count";
    public static final String COLUMN_LAST_UPDATED = "last_updated";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LONG_DESCRIPTION = "long_description";
    public static final String COLUMN_LOCATOR_CODE = "cod_localizador";

    public static final String CREATE_TABLE =
            "CREATE TABLE "+ TABLE + "(" +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_INVENTORY_ITEM_ID + " INTEGER, " +
                    COLUMN_SUBINVENTORY + " INTEGER, " +
                    COLUMN_ENTRY_STATUS_CODE + " TEXT, " +
                    COLUMN_ORGANIZATION_ID + " INTEGER, " +
                    COLUMN_CYCLE_COUNT_HEADER_ID + " TEXT, " +
                    COLUMN_LOCATOR_ID + " INTEGER, " +
                    COLUMN_LOT_NUMBER + " TEXT, " +
                    COLUMN_SEGMENT1 + " INTEGER, " +
                    COLUMN_PRIMARY_UOM_CODE + " INTEGER, " +
                    COLUMN_SERIAL_NUMBER + " TEXT, " +
                    COLUMN_COUNT + " REAL, " +
                    COLUMN_LAST_UPDATED + " TEXT " +
                    ")";

    public static final String UPDATE = COLUMN_ID + " = ?";
    public static final String DELETE = COLUMN_ID + " = ?";
    public static final String DELETE_BY_HEADER_ID = COLUMN_CYCLE_COUNT_HEADER_ID + " = ?";
    public static final String SELECT =
            " SELECT " +
            "     a.*, " +
            "     b.description, " +
            "     b.long_description, " +
            "     c.cod_localizador " +
            " FROM " +
            "     mtl_cycle_count_entries a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id  " +
            "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
            " WHERE " +
            "     a.cycle_count_entry_id = ? ";

    public static final String SELECT_ALL = " SELECT * FROM " + TABLE;
    public static final String SELECT_BY_HEADER_COUNT_HEADER_ID = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_CYCLE_COUNT_HEADER_ID + " = ?";

    public static final String SELECT_ALL_INVENTARIADOS_BY_CYCLECOUNT =
            " SELECT " +
                    "     a.*, " +
                    "     b.description, " +
                    "     b.long_description, " +
                    "     c.cod_localizador " +
                    " FROM " +
                    "     mtl_cycle_count_entries a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id  " +
                    "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
                    " WHERE " +
                    "     a.cycle_count_header_id = ? " +
                    "     AND a.count is not null " +
                    "     AND a.last_updated is not null ";

    public static final String SELECT_ALL_INVENTARIADOS_BY_CYCLECOUNT_SUBINVENTORY =
            " SELECT " +
            "     a.*, " +
            "     b.description, " +
            "     b.long_description, " +
            "     c.cod_localizador " +
            " FROM " +
            "     mtl_cycle_count_entries a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id  " +
            "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
            " WHERE " +
            "     a.cycle_count_header_id = ? " +
            "     AND a.subinventory = ? " +
            "     AND a.count is not null " +
            "     AND a.last_updated is not null ";

    public static final String SELECT_ALL_BY_CYCLECOUNT_SUBINVENTORY_LOCATOR_SEGMENT_LOTE_SERIE =
            " SELECT " +
            "     a.*, " +
            "     b.description, " +
            "     b.long_description, " +
            "     c.cod_localizador " +
            " FROM " +
            "     mtl_cycle_count_entries a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id  " +
            "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
            " WHERE " +
            "     a.cycle_count_header_id = ? " +
            "     AND a.subinventory = ? " +
            "     AND a.locator_id = ? " +
            "     AND a.segment1 = ? " +
            "     AND ifnull(a.lot_number, '') = ? " +
            "     AND ifnull(a.serial_number, '') = ? ";

    public static final String SELECT_ALL_BY_CYCLECOUNT_SUBINVENTORY_SEGMENT_LOTE_SERIE =
            " SELECT " +
                    "     a.*, " +
                    "     b.description, " +
                    "     b.long_description, " +
                    "     c.cod_localizador " +
                    " FROM " +
                    "     mtl_cycle_count_entries a LEFT JOIN mtl_system_items b  ON a.inventory_item_id = b.inventory_item_id  " +
                    "     LEFT JOIN localizador c ON a.locator_id = c.id_localizador " +
                    " WHERE " +
                    "     a.cycle_count_header_id = ? " +
                    "     AND a.subinventory = ? " +
                    "     AND a.segment1 = ? " +
                    "     AND ifnull(a.lot_number, '') = ? " +
                    "     AND ifnull(a.serial_number, '') = ? ";

    public static final String SELECT_SEGMENT_BY_CYCLECOUNTEHEADER_LOCATOR =
            " SELECT " +
            "     segment1 " +
            " FROM " +
            "     mtl_system_items " +
            " WHERE " +
            "     inventory_item_id in ( SELECT distinct(inventory_item_id) FROM mtl_cycle_count_entries WHERE cycle_count_header_id = ? and locator_id = ? ) " +
            " ORDER BY " +
            "     segment1 ";

    public static final String SELECT_SEGMENT_BY_CYCLECOUNTEHEADER_SUBINVENTORY =
            " SELECT " +
                    "     segment1 " +
                    " FROM " +
                    "     mtl_system_items " +
                    " WHERE " +
                    "     inventory_item_id in ( SELECT distinct(inventory_item_id) FROM mtl_cycle_count_entries WHERE cycle_count_header_id = ? and subinventory = ? ) " +
                    " ORDER BY " +
                    "     segment1 ";

    public static final String SELECT_SEGMENT_BY_CYCLECOUNTEHEADER_SUBINVENTORY_LOCATOR =
            " SELECT " +
                    "     segment1 " +
                    " FROM " +
                    "     mtl_system_items " +
                    " WHERE " +
                    "     inventory_item_id in ( SELECT distinct(inventory_item_id) FROM mtl_cycle_count_entries WHERE cycle_count_header_id = ? and subinventory = ? and locator_id = ? ) " +
                    " ORDER BY " +
                    "     segment1 ";

    public static final String SELECT_LOTE_BY_CYCLECOUNTEHEADER_LOCATOR_SEGMENT =
            " SELECT " +
                    "     lot_number " +
                    " FROM " +
                    "     mtl_cycle_count_entries " +
                    " WHERE " +
                    "     cycle_count_header_id = ? " +
                    "     AND locator_id = ?  " +
                    "     AND segment1 = ? " +
                    " ORDER BY " +
                    "     segment1 ";

    public static final String SELECT_LOTE_BY_CYCLECOUNTEHEADER_SUBINVENTORY_LOCATOR_SEGMENT =
            " SELECT " +
                    "     lot_number " +
                    " FROM " +
                    "     mtl_cycle_count_entries " +
                    " WHERE " +
                    "     cycle_count_header_id = ? " +
                    "     AND subinventory = ? " +
                    "     AND locator_id = ?  " +
                    "     AND segment1 = ? " +
                    " ORDER BY " +
                    "     segment1 ";

    public static final String SELECT_LOTE_BY_CYCLECOUNTEHEADER_SUBINVENTORY_SEGMENT =
            " SELECT " +
                    "     lot_number " +
                    " FROM " +
                    "     mtl_cycle_count_entries " +
                    " WHERE " +
                    "     cycle_count_header_id = ? " +
                    "     AND subinventory = ? " +
                    "     AND segment1 = ? " +
                    " ORDER BY " +
                    "     segment1 ";

    public static final String SELECT_SERIAL_BY_CYCLECOUNTEHEADER_SUBINVENTORY_LOCATOR_SEGMENT =
            " SELECT " +
                    "     serial_number " +
                    " FROM " +
                    "     mtl_cycle_count_entries " +
                    " WHERE " +
                    "     cycle_count_header_id = ? " +
                    "     AND subinventory = ? " +
                    "     AND locator_id = ?  " +
                    "     AND segment1 = ? " +
                    " ORDER BY " +
                    "     segment1 ";

    public static final String SELECT_SERIAL_BY_CYCLECOUNTEHEADER_SUBINVENTORY_SEGMENT =
            " SELECT " +
                    "     serial_number " +
                    " FROM " +
                    "     mtl_cycle_count_entries " +
                    " WHERE " +
                    "     cycle_count_header_id = ? " +
                    "     AND subinventory = ? " +
                    "     AND segment1 = ? " +
                    " ORDER BY " +
                    "     segment1 ";

}