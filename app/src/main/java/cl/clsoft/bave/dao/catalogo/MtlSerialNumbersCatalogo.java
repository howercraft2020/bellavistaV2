package cl.clsoft.bave.dao.catalogo;

public class MtlSerialNumbersCatalogo {

    public static final String TABLE = "mtl_serial_numbers";
    public static final String COLUMN_INVENTORY_ITEM_ID = "inventory_item_id";
    public static final String COLUMN_SERIAL_NUMBER = "serial_number";
    public static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    public static final String COLUMN_LAST_UPDATED_BY = "last_updated_by";
    public static final String COLUMN_CREATION_DATE = "creation_date";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_LOT_NUMBER = "lot_number";
    public static final String COLUMN_CURRENT_ORGANIZATION_ID = "current_organization_id";
    public static final String COLUMN_SHIPMENT_HEADER_ID = "shipment_header_id";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + "(" +
                    COLUMN_INVENTORY_ITEM_ID + " INTEGER, " +
                    COLUMN_SERIAL_NUMBER + " TEXT, " +
                    COLUMN_LAST_UPDATE_DATE + " TEXT, " +
                    COLUMN_LAST_UPDATED_BY + " INTEGER, " +
                    COLUMN_CREATION_DATE + " TEXT, " +
                    COLUMN_CREATED_BY + " INTEGER, " +
                    COLUMN_LOT_NUMBER + " TEXT, " +
                    COLUMN_CURRENT_ORGANIZATION_ID + " INTEGER, " +
                    COLUMN_SHIPMENT_HEADER_ID + " INTEGER, " +
                    " PRIMARY KEY (" + COLUMN_INVENTORY_ITEM_ID + ", " + COLUMN_SERIAL_NUMBER + " )" +
                    ")";

    public static final String UPDATE = COLUMN_INVENTORY_ITEM_ID + " = ? AND " + COLUMN_SERIAL_NUMBER  + " = ?" ;


}
