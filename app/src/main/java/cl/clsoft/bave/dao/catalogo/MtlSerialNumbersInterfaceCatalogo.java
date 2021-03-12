package cl.clsoft.bave.dao.catalogo;

public class MtlSerialNumbersInterfaceCatalogo {

    public static final String TABLE = "mtl_serial_numbers_interface";
    public static final String COLUMN_TRANSACTION_INTERFACE_ID = "transaction_interface_id";
    public static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";
    public static final String COLUMN_LAST_UPDATED_BY = "last_updated_by";
    public static final String COLUMN_CREATION_DATE = "creation_date";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_LAST_UPDATE_LOGIN = "last_update_login";
    public static final String COLUMN_FM_SERIAL_NUMBER = "fm_serial_number";
    public static final String COLUMN_TO_SERIAL_NUMBER = "to_serial_number";
    public static final String COLUMN_PRODUCT_CODE = "product_code";
    public static final String COLUMN_PRODUCT_TRANSACTION_ID = "product_transaction_id";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE + "( " +
                    COLUMN_TRANSACTION_INTERFACE_ID + " INTEGER, " +
                    COLUMN_LAST_UPDATE_DATE + " TEXT, " +
                    COLUMN_LAST_UPDATED_BY + " INTEGER, " +
                    COLUMN_CREATION_DATE + " TEXT, " +
                    COLUMN_CREATED_BY + " INTEGER, " +
                    COLUMN_LAST_UPDATE_LOGIN + " INTEGER, " +
                    COLUMN_FM_SERIAL_NUMBER + " TEXT, " +
                    COLUMN_TO_SERIAL_NUMBER + " TEXT, " +
                    COLUMN_PRODUCT_CODE + " TEXT, " +
                    COLUMN_PRODUCT_TRANSACTION_ID + " INTEGER " +
                    ")";


    public static final String UPDATE = COLUMN_TRANSACTION_INTERFACE_ID + " = ?";
    public static final String DELETE = COLUMN_TRANSACTION_INTERFACE_ID + " = ?";
    public static final String DELETE_BY_PRODUCT_TRANSACTION_ID = COLUMN_PRODUCT_TRANSACTION_ID + " = ?";
    public static final String SELECT = "SELECT * FROM " + TABLE + " WHERE " +COLUMN_TRANSACTION_INTERFACE_ID + " = ?";

    public static final String SELECT_ALL_BY_ID = "SELECT * FROM " + TABLE + " WHERE " +COLUMN_TRANSACTION_INTERFACE_ID +
                                                  " = ?";


    public static final String SELECT_BY_PRODUCT_TRANSACTION_ID =
            " SELECT * " +
            " FROM " +
            "     mtl_serial_numbers_interface " +
            " WHERE " +
            "     product_transaction_id = ? ";

}
