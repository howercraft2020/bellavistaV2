package cl.clsoft.bave.model;

public class MtlSerialNumbers {

    private Long inventoryItemId;
    private String serialNumber;
    private String lastUpdateDate;
    private Long lastUpdateBy;
    private String creationDate;
    private Long createdBy;
    private String lotNumber;
    private Long currentOrganizationId;
    private Long shipmentHeaderId;
    private Long transactionInterfaceId;
    private String entregaCreationDate;

    public MtlSerialNumbers() {}

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Long lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Long getCurrentOrganizationId() {
        return currentOrganizationId;
    }

    public void setCurrentOrganizationId(Long currentOrganizationId) {
        this.currentOrganizationId = currentOrganizationId;
    }

    public Long getShipmentHeaderId() {
        return shipmentHeaderId;
    }

    public void setShipmentHeaderId(Long shipmentHeaderId) {
        this.shipmentHeaderId = shipmentHeaderId;
    }

    public Long getTransactionInterfaceId() {
        return transactionInterfaceId;
    }

    public void setTransactionInterfaceId(Long transactionInterfaceId) {
        this.transactionInterfaceId = transactionInterfaceId;
    }

    public String getEntregaCreationDate() {
        return entregaCreationDate;
    }

    public void setEntregaCreationDate(String entregaCreationDate) {
        this.entregaCreationDate = entregaCreationDate;
    }
}
