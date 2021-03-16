package cl.clsoft.bave.dao.catalogo;

public class DatosTransOrgCatalogo {

    public static final String COLUMN_SHIPMENT_NUMBER = "shipment_number";
    public static final String COLUMN_TRANSACTION_SOURCE_NAME = "transaction_source_name";

    public static final String SELECT = "SELECT shipment_number, transaction_source_name FROM mtl_transactions_interface WHERE transaction_action_id = 21 AND transaction_type_id = 21 group by shipment_number, transaction_source_name";
}
