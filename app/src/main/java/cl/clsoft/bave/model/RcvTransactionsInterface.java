package cl.clsoft.bave.model;

public class RcvTransactionsInterface {
    private Long interfaceTransactionId;
    private String lastUpdatedDate;
    private Long lastUpdatedBy;
    private String creationDate;
    private Long createdBy;
    private String transactionType;
    private String transactionDate;
    private Long quantity;
    private String unitOfMeasure;
    private Long itemId;
    private String itemDescription;
    private String UomCode;
    private Long shipToLocationId;
    private Long primaryQuantity;
    private String receiptSourceCode;
    private Long vendorId;
    private Long vendorSiteId;
    private Long toOrganizationId;
    private Long poHeaderId;
    private Long poLineId;
    private Long poLineLocation;
    private Long poUnitPrice;
    private String currencyCode;
    private String sourceDocumentCode;
    private String currencyConversionType;
    private Long currencyConversionRate;
    private String currencyConversionDate;
    private Long poDistributionId;
    private String destinationTypeCode;
    private Long locationId;
    private Long deliverToLocationId;
    private String inspectionStatusCode;
    private Long headerInterfaceId;
    private String vendorSiteCode;
    private String processingStatusCode;
    private String comments;
    private Long useMtlLot;
    private Long UseMtlSerial;
    private String transactionStatusCode;
    private String validationFlag;
    private Long orgId;
    private Long groupId;
    private String autoTransactCode;
    private String segment1;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public Long getInterfaceTransactionId() {
        return interfaceTransactionId;
    }

    public void setInterfaceTransactionId(Long interfaceTransactionId) {
        this.interfaceTransactionId = interfaceTransactionId;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
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

    public Long getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(Long shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public Long getPrimaryQuantity() {
        return primaryQuantity;
    }

    public void setPrimaryQuantity(Long primaryQuantity) {
        this.primaryQuantity = primaryQuantity;
    }

    public String getReceiptSourceCode() {
        return receiptSourceCode;
    }

    public void setReceiptSourceCode(String receiptSourceCode) {
        this.receiptSourceCode = receiptSourceCode;
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

    public void setCurrencyCode(String currencyCod) {
        this.currencyCode = currencyCod;
    }

    public String getSourceDocumentCode() {
        return sourceDocumentCode;
    }

    public void setSourceDocumentCode(String sourceDocumentCode) {
        this.sourceDocumentCode = sourceDocumentCode;
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

    public Long getHeaderInterfaceId() {
        return headerInterfaceId;
    }

    public void setHeaderInterfaceId(Long headerInterfaceId) {
        this.headerInterfaceId = headerInterfaceId;
    }

    public String getVendorSiteCode() {
        return vendorSiteCode;
    }

    public void setVendorSiteCode(String vendorSiteCode) {
        this.vendorSiteCode = vendorSiteCode;
    }

    public String getProcessingStatusCode() {
        return processingStatusCode;
    }

    public void setProcessingStatusCode(String processingStatusCode) {
        this.processingStatusCode = processingStatusCode;
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

    public String getTransactionStatusCode() {
        return transactionStatusCode;
    }

    public void setTransactionStatusCode(String transactionStatusCode) {
        this.transactionStatusCode = transactionStatusCode;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getAutoTransactCode() {
        return autoTransactCode;
    }

    public void setAutoTransactCode(String autoTransactCode) {
        this.autoTransactCode = autoTransactCode;
    }

    public RcvTransactionsInterface(){

    }
}
