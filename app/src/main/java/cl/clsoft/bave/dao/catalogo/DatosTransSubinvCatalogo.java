package cl.clsoft.bave.dao.catalogo;

public class DatosTransSubinvCatalogo {

    public static final String COLUMN_TRANSACTION_REFERENCE = "transaction_reference";
    public static final String COLUMN_TRANSACTION_SOURCE_NAME = "transaction_source_name";

    public static final String SELECT = "SELECT transaction_reference, transaction_source_name FROM mtl_transactions_interface WHERE transaction_action_id = 2 AND transaction_type_id = 2 group by transaction_reference, transaction_source_name";
}
