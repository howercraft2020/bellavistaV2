package cl.clsoft.bave.model;

public class MtlTransactionLotNumbers {

    private Long transactionId;
    private Long inventoryItemId;
    private Long organizationId;
    private String lotNumber;
    private Long serialTransactionId;
    private String lotAttributeCategory;
    private String cAttribute1;
    private String cAttribute2;
    private String cAttribute3;
    private Long shipmentHeaderId;

    public MtlTransactionLotNumbers() {}

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Long getSerialTransactionId() {
        return serialTransactionId;
    }

    public void setSerialTransactionId(Long serialTransactionId) {
        this.serialTransactionId = serialTransactionId;
    }

    public String getLotAttributeCategory() {
        return lotAttributeCategory;
    }

    public void setLotAttributeCategory(String lotAttributeCategory) {
        this.lotAttributeCategory = lotAttributeCategory;
    }

    public String getcAttribute1() {
        return cAttribute1;
    }

    public void setcAttribute1(String cAttribute1) {
        this.cAttribute1 = cAttribute1;
    }

    public String getcAttribute2() {
        return cAttribute2;
    }

    public void setcAttribute2(String cAttribute2) {
        this.cAttribute2 = cAttribute2;
    }

    public String getcAttribute3() {
        return cAttribute3;
    }

    public void setcAttribute3(String cAttribute3) {
        this.cAttribute3 = cAttribute3;
    }

    public Long getShipmentHeaderId() {
        return shipmentHeaderId;
    }

    public void setShipmentHeaderId(Long shipmentHeaderId) {
        this.shipmentHeaderId = shipmentHeaderId;
    }
}
