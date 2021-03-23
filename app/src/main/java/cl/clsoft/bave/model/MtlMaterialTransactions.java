package cl.clsoft.bave.model;

public class MtlMaterialTransactions {

    private Long transactionId;
    private Long inventoryItemId;
    private Long organizationId;
    private Long transactionTypeId;
    private Long transactionActionId;
    private Long transactionSourceTypeId;
    private String transactionSourceName;
    private Double transactionQuantity;
    private String transactionUom;
    private Double primaryQuantity;
    private String transactionDate;
    private Double actualCost;
    private Long transferOrganizationId;
    private Long shipToLocationId;
    private String receipNum;
    private Long userId;
    private String shipmentNumber;
    private Long shipmentHeaderId;
    private Long shipmentLineId;
    private String entregaCreationDate;
    private Double entregaQuantity;
    private String subinventory;
    private Long locatorId;
    private Long useMtlLot;
    private Long UseMtlSerial;

    public MtlMaterialTransactions() {}

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

    public Long getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Long transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public Long getTransactionActionId() {
        return transactionActionId;
    }

    public void setTransactionActionId(Long transactionActionId) {
        this.transactionActionId = transactionActionId;
    }

    public Long getTransactionSourceTypeId() {
        return transactionSourceTypeId;
    }

    public void setTransactionSourceTypeId(Long transactionSourceTypeId) {
        this.transactionSourceTypeId = transactionSourceTypeId;
    }

    public String getTransactionSourceName() {
        return transactionSourceName;
    }

    public void setTransactionSourceName(String transactionSourceName) {
        this.transactionSourceName = transactionSourceName;
    }

    public Double getTransactionQuantity() {
        return transactionQuantity;
    }

    public void setTransactionQuantity(Double transactionQuantity) {
        this.transactionQuantity = transactionQuantity;
    }

    public String getTransactionUom() {
        return transactionUom;
    }

    public void setTransactionUom(String transactionUom) {
        this.transactionUom = transactionUom;
    }

    public Double getPrimaryQuantity() {
        return primaryQuantity;
    }

    public void setPrimaryQuantity(Double primaryQuantity) {
        this.primaryQuantity = primaryQuantity;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getActualCost() {
        return actualCost;
    }

    public void setActualCost(Double actualCost) {
        this.actualCost = actualCost;
    }

    public Long getTransferOrganizationId() {
        return transferOrganizationId;
    }

    public void setTransferOrganizationId(Long transferOrganizationId) {
        this.transferOrganizationId = transferOrganizationId;
    }

    public Long getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(Long shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public String getReceipNum() {
        return receipNum;
    }

    public void setReceipNum(String receipNum) {
        this.receipNum = receipNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public Long getShipmentHeaderId() {
        return shipmentHeaderId;
    }

    public void setShipmentHeaderId(Long shipmentHeaderId) {
        this.shipmentHeaderId = shipmentHeaderId;
    }

    public Long getShipmentLineId() {
        return shipmentLineId;
    }

    public void setShipmentLineId(Long shipmentLineId) {
        this.shipmentLineId = shipmentLineId;
    }

    public String getEntregaCreationDate() {
        return entregaCreationDate;
    }

    public void setEntregaCreationDate(String entregaCreationDate) {
        this.entregaCreationDate = entregaCreationDate;
    }

    public Double getEntregaQuantity() {
        return entregaQuantity;
    }

    public void setEntregaQuantity(Double entregaQuantity) {
        this.entregaQuantity = entregaQuantity;
    }

    public String getSubinventory() {
        return subinventory;
    }

    public void setSubinventory(String subinventory) {
        this.subinventory = subinventory;
    }

    public Long getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(Long locatorId) {
        this.locatorId = locatorId;
    }

    public Long getUseMtlLot() {
        return useMtlLot;
    }

    public void setUseMtlLot(Long useMtlLot) {
        this.useMtlLot = useMtlLot;
    }

    public Long getUseMtlSerial() {
        return UseMtlSerial;
    }

    public void setUseMtlSerial(Long useMtlSerial) {
        UseMtlSerial = useMtlSerial;
    }

}
