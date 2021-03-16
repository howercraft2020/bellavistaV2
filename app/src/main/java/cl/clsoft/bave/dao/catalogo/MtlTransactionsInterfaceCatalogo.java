package cl.clsoft.bave.dao.catalogo;

public class MtlTransactionsInterfaceCatalogo {

    public static final String TABLE = "mtl_transactions_interface";
    public static final String COLUMN_TRANSACTION_INTERFACE_ID = "transaction_interface_id";
    public static final String COLUMN_PROCESS_FLAG = "process_flag";
    public static final String COLUMN_TRANSACTION_MODE = "transaction_mode";
    public static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    public static final String COLUMN_LAST_UPDATED_BY = "last_updated_by";
    public static final String COLUMN_CREATION_DATE = "creation_date";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    public static final String COLUMN_ORGANIZATION_ID = "organization_id";
    public static final String COLUMN_TRANSACTION_QUANTITY = "transaction_quantity";
    public static final String COLUMN_PRIMARY_QUANTITY = "primary_quantity";
    public static final String COLUMN_TRANSACTION_UOM = "transaction_uom";
    public static final String COLUMN_TRANSACTION_DATE = "transaction_date";
    public static final String COLUMN_SUBINVENTORY_CODE = "subinventory_code";
    public static final String COLUMN_LOCATOR_ID = "locator_id";
    public static final String COLUMN_TRANSACTION_SOURCE_NAME = "transaction_source_name";
    public static final String COLUMN_TRANSACTION_SOURCE_TYPE_ID = "transaction_source_type_id";
    public static final String COLUMN_TRANSACTION_ACTION_ID = "transaction_action_id";
    public static final String COLUMN_TRANSACTION_TYPE_ID = "transaction_type_id";
    public static final String COLUMN_TRANSACTION_REFERENCE = "transaction_reference";
    public static final String COLUMN_TRANSFER_SUBINVENTORY = "transfer_subinventory";
    public static final String COLUMN_TRANSFER_ORGANIZATION = "transfer_organization";
    public static final String COLUMN_TRANSFER_LOCATOR = "transfer_locator";
    public static final String COLUMN_SOURCE_CODE = "source_code";
    public static final String COLUMN_SOURCE_LINE_ID = "source_line_id";
    public static final String COLUMN_SOURCE_HEADER_ID = "source_header_id";
    public static final String COLUMN_SHIPMENT_NUMBER = "shipment_number";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + " ( " +
                    COLUMN_TRANSACTION_INTERFACE_ID + " INTEGER, " +
                    COLUMN_PROCESS_FLAG + " INTEGER, " +
                    COLUMN_TRANSACTION_MODE + " INTEGER, " +
                    COLUMN_LAST_UPDATE_DATE + " TEXT, " +
                    COLUMN_LAST_UPDATED_BY + " INTEGER, " +
                    COLUMN_CREATION_DATE + " TEXT, " +
                    COLUMN_CREATED_BY + " INTEGER, " +
                    COLUMN_INVENTORY_ITEM_ID + " INTEGER, " +
                    COLUMN_ORGANIZATION_ID + " INTEGER, " +
                    COLUMN_TRANSACTION_QUANTITY + " INTEGER, " +
                    COLUMN_PRIMARY_QUANTITY + " INTEGER, " +
                    COLUMN_TRANSACTION_UOM + " TEXT, " +
                    COLUMN_TRANSACTION_DATE + " TEXT, " +
                    COLUMN_SUBINVENTORY_CODE + " TEXT, " +
                    COLUMN_LOCATOR_ID + " INTEGER, " +
                    COLUMN_TRANSACTION_SOURCE_NAME + " TEXT, " +
                    COLUMN_TRANSACTION_SOURCE_TYPE_ID + " INTEGER, " +
                    COLUMN_TRANSACTION_ACTION_ID + " INTEGER, " +
                    COLUMN_TRANSACTION_TYPE_ID + " INTEGER, " +
                    COLUMN_TRANSACTION_REFERENCE + " TEXT, " +
                    COLUMN_TRANSFER_SUBINVENTORY + " TEXT, " +
                    COLUMN_TRANSFER_ORGANIZATION + " INTEGER, " +
                    COLUMN_TRANSFER_LOCATOR + " INTEGER, " +
                    COLUMN_SOURCE_CODE + " TEXT, " +
                    COLUMN_SOURCE_LINE_ID + " INTEGER, " +
                    COLUMN_SOURCE_HEADER_ID + " INTEGER, " +
                    COLUMN_SHIPMENT_NUMBER + " INTEGER " +
                    " ) ";

    public static final String SELECT_ALL = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_TRANSACTION_ACTION_ID + " = 2 AND " + COLUMN_TRANSACTION_TYPE_ID + " = 2 ";

    public static final String SELECT_ALL_BY_ID = " SELECT * FROM " + TABLE + " WHERE " + COLUMN_TRANSACTION_INTERFACE_ID + " = ?";



    public static final String SELECT_LOC_OR_DES_NOT_NULL = " select count(*) from mtl_transactions_interface mti, " +
                                                            "localizador loc, localizador loc_des " +
                                                            "where mti.locator_id = loc.id_localizador " +
                                                            "and mti.transfer_locator = loc_des.id_localizador " +
                                                            "and mti.inventory_item_id = ? " +
                                                            "and mti.subinventory_code = ? " +
                                                            "and loc.cod_localizador = ? " +
                                                            "and mti.transfer_subinventory = ? " +
                                                            "and loc_des.cod_localizador = ? ";


    public static final String SELECT_LOC_OR_NULL_DEST_NOT_NULL = " select count(*) from mtl_transactions_interface mti, " +
                                                                  "localizador loc_des " +
                                                                  "where mti.transfer_locator = loc_des.id_localizador " +
                                                                  "and mti.inventory_item_id = ? " +
                                                                  "and mti.subinventory_code = ? " +
                                                                  "and mti.transfer_subinventory = ? " +
                                                                  "and loc_des.cod_localizador = ? ";


    public static final String SELECT_LOC_OR_NOT_NULL_DEST_NULL = " select count(*) from mtl_transactions_interface mti, " +
                                                                      "localizador loc " +
                                                                      "where mti.locator_id = loc.id_localizador " +
                                                                      "and mti.inventory_item_id = ? " +
                                                                      "and mti.subinventory_code = ? " +
                                                                      "and loc.cod_localizador = ? " +
                                                                      "and mti.transfer_subinventory = ?";

    public static final String SELECT_LOC_OR_NULL_DEST_NULL = " select count(*) from mtl_transactions_interface " +
                                                              "where inventory_item_id = ? " +
                                                              "and subinventory_code = ? " +
                                                              "and transfer_subinventory = ?" +
                                                              "and locator_id IS NULL " +
                                                              "and transfer_locator IS NULL ";

    public static final String SELECT_LOC_NULL = "select count(*) from mtl_transactions_interface mti, "+
                                                 "localizador loc " +
                                                 "where mti.locator_id = loc.id_localizador " +
                                                 "and mti.inventory_item_id = ? " +
                                                 "and mti.subinventory_code = ? " +
                                                 "and ifnull(loc.cod_localizador, '') = ? ";

    public static final String SELECT_TRANSFERENCIAS = "SELECT * from " + TABLE + " WHERE " + COLUMN_TRANSACTION_REFERENCE + " = ? AND " + COLUMN_TRANSACTION_SOURCE_NAME +
                                                       " = ?";

    public static final String SELECT_TRANSFERENCIAS_BY_TRASPASO = "SELECT * from " + TABLE + " WHERE " + COLUMN_TRANSACTION_REFERENCE + " = ?";
    public static final String SELECT_TRANSFERENCIAS_BY_SHIPMENT = "SELECT * from " + TABLE + " WHERE " + COLUMN_SHIPMENT_NUMBER + " = ?";


    public static final String DELETE = COLUMN_TRANSACTION_INTERFACE_ID + " = ?";


}
