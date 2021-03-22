package cl.clsoft.bave.dao.catalogo;

public class EntregaOrgsHeaderCatalogo {

    public static final String COLUMN_SHIPMENT_NUMBER = "shipment_number";
    public static final String COLUMN_RECEIPT_NUMBER = "receipt_number";
    public static final String COLUMN_SHIPMENT_HEADER_ID = "shipment_header_id";
    public static final String COLUMN_CREATION_DATE = "creation_date";

    public static final String SELECT_ALL =
            " SELECT "
            + "     distinct(shipment_number) as shipment_number, "
            + "     max(receipt_num) as receipt_number, "
            + "     max(shipment_header_id) as shipment_header_id, "
            + "     max(transaction_date) as creation_date "
            + " FROM "
            + "     mtl_material_transactions "
            + " ORDER BY "
            + "     shipment_number ";

}
