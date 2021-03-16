package cl.clsoft.bave.dao.catalogo;

public class DatosTransOrgDetalleCatalogo {

    public static final String COLUMN_ORG_DESTINO = "transfer_organization";
    public static final String COLUMN_SEGMENT1 = "segment1";
    public static final String COLUMN_SUBINVENTORY_CODE = "subinventory_code";
    public static final String COLUMN_LOCALIZADOR = "localizador";
    public static final String COLUMN_TRANSACTION_QUANTITY = "transaction_quantity";
    public static final String COLUMN_TRANSACTION_INTERFACE_ID = "transaction_interface_id";

    public static final String SELECT = "select msi.segment1, mti.subinventory_code, mti.transaction_interface_id, " +
            "(select loc.cod_localizador from localizador loc where loc.id_localizador = mti.locator_id) localizador, " +
            "(select org.code from organizacion org where org.id_organizacion = mti.transfer_organization and transferencia = 'HACIA' ) transfer_organization, " +
            "mti.transaction_quantity " +
            "from mtl_transactions_interface mti, " +
            "mtl_system_items msi " +
            "where mti.inventory_item_id = msi.inventory_item_id " +
            "and mti.shipment_number = ? ";
}
