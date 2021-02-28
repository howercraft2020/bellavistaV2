package cl.clsoft.bave.dao.catalogo;

public class DatosTransSubinvDetalleCatalogo {

    public static final String COLUMN_SEGMENT1 = "segment1";
    public static final String COLUMN_SUBINVENTORY_CODE = "subinventory_code";
    public static final String COLUMN_LOCALIZADOR = "localizador";
    public static final String COLUMN_SUBINVENTARIO_HASTA = "subinventario_hasta";
    public static final String COLUMN_LOCALIZADOR_HASTA = "localizador_hasta";
    public static final String COLUMN_TRANSACTION_QUANTITY = "transaction_quantity";

    public static final String SELECT = "select msi.segment1, mti.subinventory_code, " +
            "(select loc.cod_localizador from localizador loc where loc.id_localizador = mti.locator_id) localizador, " +
            "mti.transfer_subinventory subinventario_hasta, " +
            "(select loc.cod_localizador from localizador loc where loc.id_localizador = mti.transfer_locator) localizador_hasta, " +
            "mti.transaction_quantity " +
            "from mtl_transactions_interface mti, " +
            "mtl_system_items msi " +
            "where mti.inventory_item_id = msi.inventory_item_id " +
            "and mti.transaction_reference = ? ";
}
