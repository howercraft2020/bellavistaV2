package cl.clsoft.bave.dao.catalogo;

public class MtlSerialNumbersInterfaceCatalogo {

    public static final String TABLE = "mtl_serial_numbers_interface";
    public static final String COLUMN_TRANSACTION_INTERFACE_ID = "transaction_interface_id";
    public static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    public static final String COLUMN_LAST_UPDATED_BY = "last_updated_by";
    public static final String COLUMN_CREATION_DATE = "creation_date";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_FM_SERIAL_NUMBER = "fm_serial_number";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + "( " +
                    COLUMN_TRANSACTION_INTERFACE_ID + " INTEGER, " +
                    COLUMN_LAST_UPDATE_DATE + " TEXT, " +
                    COLUMN_LAST_UPDATED_BY + " INTEGER, " +
                    COLUMN_CREATION_DATE + " TEXT, " +
                    COLUMN_CREATED_BY + " INTEGER, " +
                    COLUMN_FM_SERIAL_NUMBER + " TEXT " +
                    ")";

    public static final String SELECT_ALL_BY_ID = "SELECT * FROM " + TABLE + " WHERE " +COLUMN_TRANSACTION_INTERFACE_ID +
                                                  " = ?";

    public static final String SELECT_CANTIDAD_SERIES = "SELECT COUNT(*) FROM " + TABLE + " WHERE " + COLUMN_TRANSACTION_INTERFACE_ID +
                                                        " = ?";

}
