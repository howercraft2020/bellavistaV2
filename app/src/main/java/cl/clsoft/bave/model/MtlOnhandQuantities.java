package cl.clsoft.bave.model;

public class MtlOnhandQuantities {

    private Long inventoryItemId;
    private Long organizationId;
    private String subinventoryCode;
    private Long locatorId;
    private String lotNumber;
    private String serialNumber;
    private Long primaryTransactionQuantity;
    private Long userId;
    private Long statusId;

    public MtlOnhandQuantities() {}

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

    public String getSubinventoryCode() {
        return subinventoryCode;
    }

    public void setSubinventoryCode(String subinventoryCode) {
        this.subinventoryCode = subinventoryCode;
    }

    public Long getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(Long locatorId) {
        this.locatorId = locatorId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getPrimaryTransactionQuantity() {
        return primaryTransactionQuantity;
    }

    public void setPrimaryTransactionQuantity(Long primaryTransactionQuantity) {
        this.primaryTransactionQuantity = primaryTransactionQuantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
