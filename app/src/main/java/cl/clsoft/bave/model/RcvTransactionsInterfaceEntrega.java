package cl.clsoft.bave.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RcvTransactionsInterfaceEntrega {

    private Long transactionInterfaceId;

    private Date lastUpdatedDate;

    private Long lastUpdatedBy;

    private String creationDate;

    private Long createdBy;

    private String transactionType;

    private String transactionDate;

    private String processingStatusCode;

    private String processingModeCode;

    private Double quantity;

    private String unitOfMeasure;

    private Long itemId;

    private String itemDescription;
    @SerializedName("uomCode")
    private String UomCode;

    private Long employeeId;

    private Long shipmentHeaderId;

    private Long shipmentLineId;

    private Long shipToLocationId;

    private Long vendorId;

    private Long vendorSiteId;

    private Long toOrganizationId;

    private String sourceDocumentCode;

    private Long parentTransactionId;

    private Long poHeaderId;

    private Long poLineId;

    private Long poLineLocation;

    private Long poUnitPrice;

    private String currencyCode;

    private String currencyConversionType;

    private Long currencyConversionRate;

    private String currencyConversionDate;

    private Long poDistributionId;

    private String destinationTypeCode;

    private Long locationId;

    private Long deliverToLocationId;

    private String inspectionStatusCode;

    private String subinventory;

    private Long locatorId;

    private String shipmentNum;

    private Long useMtlLot;

    private Long useMtlSerial;

    private Long groupId;

    private String transactionStatusCode;

    private String autoTransactCode;

    private String receiptSourceCode;

    private String validationFlag;

    private Long orgId;

    private Double primaryQuantity;

    private String estado;

    private String attribute15;

    public RcvTransactionsInterfaceEntrega() {
    }

    public RcvTransactionsInterfaceEntrega(Long transactionInterfaceId, Date lastUpdatedDate, Long lastUpdatedBy, String creationDate, Long createdBy, String transactionType, String transactionDate, String processingStatusCode, String processingModeCode, Double quantity, String unitOfMeasure, Long itemId, String itemDescription, String uomCode, Long employeeId, Long shipmentHeaderId, Long shipmentLineId, Long shipToLocationId, Long vendorId, Long vendorSiteId, Long toOrganizationId, String sourceDocumentCode, Long parentTransactionId, Long poHeaderId, Long poLineId, Long poLineLocation, Long poUnitPrice, String currencyCode, String currencyConversionType, Long currencyConversionRate, String currencyConversionDate, Long poDistributionId, String destinationTypeCode, Long locationId, Long deliverToLocationId, String inspectionStatusCode, String subinventory, Long locatorId, String shipmentNum, Long useMtlLot, Long useMtlSerial, Long groupId, String transactionStatusCode, String autoTransactCode, String receiptSourceCode, String validationFlag, Long orgId, Double primaryQuantity, String estado, String attribute15) {
        this.transactionInterfaceId = transactionInterfaceId;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.processingStatusCode = processingStatusCode;
        this.processingModeCode = processingModeCode;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
        this.itemId = itemId;
        this.itemDescription = itemDescription;
        this.UomCode = uomCode;
        this.employeeId = employeeId;
        this.shipmentHeaderId = shipmentHeaderId;
        this.shipmentLineId = shipmentLineId;
        this.shipToLocationId = shipToLocationId;
        this.vendorId = vendorId;
        this.vendorSiteId = vendorSiteId;
        this.toOrganizationId = toOrganizationId;
        this.sourceDocumentCode = sourceDocumentCode;
        this.parentTransactionId = parentTransactionId;
        this.poHeaderId = poHeaderId;
        this.poLineId = poLineId;
        this.poLineLocation = poLineLocation;
        this.poUnitPrice = poUnitPrice;
        this.currencyCode = currencyCode;
        this.currencyConversionType = currencyConversionType;
        this.currencyConversionRate = currencyConversionRate;
        this.currencyConversionDate = currencyConversionDate;
        this.poDistributionId = poDistributionId;
        this.destinationTypeCode = destinationTypeCode;
        this.locationId = locationId;
        this.deliverToLocationId = deliverToLocationId;
        this.inspectionStatusCode = inspectionStatusCode;
        this.subinventory = subinventory;
        this.locatorId = locatorId;
        this.shipmentNum = shipmentNum;
        this.useMtlLot = useMtlLot;
        this.useMtlSerial = useMtlSerial;
        this.groupId = groupId;
        this.transactionStatusCode = transactionStatusCode;
        this.autoTransactCode = autoTransactCode;
        this.receiptSourceCode = receiptSourceCode;
        this.validationFlag = validationFlag;
        this.orgId = orgId;
        this.primaryQuantity = primaryQuantity;
        this.estado = estado;
        this.attribute15 = attribute15;
    }

    public Long getTransactionInterfaceId() {
        return transactionInterfaceId;
    }

    public void setTransactionInterfaceId(Long transactionInterfaceId) {
        this.transactionInterfaceId = transactionInterfaceId;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getProcessingStatusCode() {
        return processingStatusCode;
    }

    public void setProcessingStatusCode(String processingStatusCode) {
        this.processingStatusCode = processingStatusCode;
    }

    public String getProcessingModeCode() {
        return processingModeCode;
    }

    public void setProcessingModeCode(String processingModeCode) {
        this.processingModeCode = processingModeCode;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getUomCode() {
        return UomCode;
    }

    public void setUomCode(String uomCode) {
        UomCode = uomCode;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public Long getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(Long shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getVendorSiteId() {
        return vendorSiteId;
    }

    public void setVendorSiteId(Long vendorSiteId) {
        this.vendorSiteId = vendorSiteId;
    }

    public Long getToOrganizationId() {
        return toOrganizationId;
    }

    public void setToOrganizationId(Long toOrganizationId) {
        this.toOrganizationId = toOrganizationId;
    }

    public String getSourceDocumentCode() {
        return sourceDocumentCode;
    }

    public void setSourceDocumentCode(String sourceDocumentCode) {
        this.sourceDocumentCode = sourceDocumentCode;
    }

    public Long getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(Long parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    public Long getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(Long poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public Long getPoLineId() {
        return poLineId;
    }

    public void setPoLineId(Long poLineId) {
        this.poLineId = poLineId;
    }

    public Long getPoLineLocation() {
        return poLineLocation;
    }

    public void setPoLineLocation(Long poLineLocation) {
        this.poLineLocation = poLineLocation;
    }

    public Long getPoUnitPrice() {
        return poUnitPrice;
    }

    public void setPoUnitPrice(Long poUnitPrice) {
        this.poUnitPrice = poUnitPrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyConversionType() {
        return currencyConversionType;
    }

    public void setCurrencyConversionType(String currencyConversionType) {
        this.currencyConversionType = currencyConversionType;
    }

    public Long getCurrencyConversionRate() {
        return currencyConversionRate;
    }

    public void setCurrencyConversionRate(Long currencyConversionRate) {
        this.currencyConversionRate = currencyConversionRate;
    }

    public String getCurrencyConversionDate() {
        return currencyConversionDate;
    }

    public void setCurrencyConversionDate(String currencyConversionDate) {
        this.currencyConversionDate = currencyConversionDate;
    }

    public Long getPoDistributionId() {
        return poDistributionId;
    }

    public void setPoDistributionId(Long poDistributionId) {
        this.poDistributionId = poDistributionId;
    }

    public String getDestinationTypeCode() {
        return destinationTypeCode;
    }

    public void setDestinationTypeCode(String destinationTypeCode) {
        this.destinationTypeCode = destinationTypeCode;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getDeliverToLocationId() {
        return deliverToLocationId;
    }

    public void setDeliverToLocationId(Long deliverToLocationId) {
        this.deliverToLocationId = deliverToLocationId;
    }

    public String getInspectionStatusCode() {
        return inspectionStatusCode;
    }

    public void setInspectionStatusCode(String inspectionStatusCode) {
        this.inspectionStatusCode = inspectionStatusCode;
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

    public String getShipmentNum() {
        return shipmentNum;
    }

    public void setShipmentNum(String shipmentNum) {
        this.shipmentNum = shipmentNum;
    }

    public Long getUseMtlLot() {
        return useMtlLot;
    }

    public void setUseMtlLot(Long useMtlLot) {
        this.useMtlLot = useMtlLot;
    }

    public Long getUseMtlSerial() {
        return useMtlSerial;
    }

    public void setUseMtlSerial(Long useMtlSerial) {
        this.useMtlSerial = useMtlSerial;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getTransactionStatusCode() {
        return transactionStatusCode;
    }

    public void setTransactionStatusCode(String transactionStatusCode) {
        this.transactionStatusCode = transactionStatusCode;
    }

    public String getAutoTransactCode() {
        return autoTransactCode;
    }

    public void setAutoTransactCode(String autoTransactCode) {
        this.autoTransactCode = autoTransactCode;
    }

    public String getReceiptSourceCode() {
        return receiptSourceCode;
    }

    public void setReceiptSourceCode(String receiptSourceCode) {
        this.receiptSourceCode = receiptSourceCode;
    }

    public String getValidationFlag() {
        return validationFlag;
    }

    public void setValidationFlag(String validationFlag) {
        this.validationFlag = validationFlag;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Double getPrimaryQuantity() {
        return primaryQuantity;
    }

    public void setPrimaryQuantity(Double primaryQuantity) {
        this.primaryQuantity = primaryQuantity;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAttribute15() {
        return attribute15;
    }

    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }
}
