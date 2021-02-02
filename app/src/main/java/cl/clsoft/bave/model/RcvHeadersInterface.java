package cl.clsoft.bave.model;

public class RcvHeadersInterface {
    private String headerInterfaceId;
    private String reciptSourceCode;
    private String transactionType;
    private String lastUpdateDate;
    private Long lastUpdateBy;
    private Long createdBy;
    private String reciptNum;
    private Long vendorId;
    private String vendorSiteCode;
    private Long vendorSiteId;
    private String shipToOrganizationCode;
    private Long locationId;
    private Long receiverId;
    private String currencyCode;
    private String conversionRateType;
    private Long conversionRate;
    private String conversionRateDate;
    private Long paymentTermsId;
    private String transactionDate;
    private String comments;
    private Long orgId;
    private String processingStatusCode;
    private String validationFlag;
    private Long groupId;

    public String getHeaderInterfaceId() {
        return headerInterfaceId;
    }

    public void setHeaderInterfaceId(String headerInterfaceId) {
        this.headerInterfaceId = headerInterfaceId;
    }

    public String getReciptSourceCode() {
        return reciptSourceCode;
    }

    public void setReciptSourceCode(String reciptSourceCode) {
        this.reciptSourceCode = reciptSourceCode;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getReciptNum() {
        return reciptNum;
    }

    public void setReciptNum(String reciptNum) {
        this.reciptNum = reciptNum;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorSiteCode() {
        return vendorSiteCode;
    }

    public void setVendorSiteCode(String vendorSiteCode) {
        this.vendorSiteCode = vendorSiteCode;
    }

    public Long getVendorSiteId() {
        return vendorSiteId;
    }

    public void setVendorSiteId(Long vendorSiteId) {
        this.vendorSiteId = vendorSiteId;
    }

    public String getShipToOrganizationCode() {
        return shipToOrganizationCode;
    }

    public void setShipToOrganizationCode(String shipToOrganizationCode) {
        this.shipToOrganizationCode = shipToOrganizationCode;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getConversionRateType() {
        return conversionRateType;
    }

    public void setConversionRateType(String conversionRateType) {
        this.conversionRateType = conversionRateType;
    }

    public Long getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Long conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getConversionRateDate() {
        return conversionRateDate;
    }

    public void setConversionRateDate(String conversionRateDate) {
        this.conversionRateDate = conversionRateDate;
    }

    public Long getPaymentTermsId() {
        return paymentTermsId;
    }

    public void setPaymentTermsId(Long paymentTermsId) {
        this.paymentTermsId = paymentTermsId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getProcessingStatusCode() {
        return processingStatusCode;
    }

    public void setProcessingStatusCode(String processingStatusCode) {
        this.processingStatusCode = processingStatusCode;
    }

    public String getValidationFlag() {
        return validationFlag;
    }

    public void setValidationFlag(String validationFlag) {
        this.validationFlag = validationFlag;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public RcvHeadersInterface(){

    }
}
