package cl.clsoft.bave.model;

public class DatosTransOrg {

    private String shipmentNumber;
    private String transactionSourceName;

    public DatosTransOrg() {}

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public String getTransactionSourceName() {
        return transactionSourceName;
    }

    public void setTransactionSourceName(String transactionSourceName) {
        this.transactionSourceName = transactionSourceName;
    }
}
