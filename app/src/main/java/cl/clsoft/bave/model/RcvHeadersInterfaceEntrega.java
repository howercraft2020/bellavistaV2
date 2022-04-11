package cl.clsoft.bave.model;


import java.util.Date;

public class RcvHeadersInterfaceEntrega {




    private Long headerInterfaceId;

    private Long groupId;

    private String processingStatusCode;

    private String reciptSourceCode;

    private String transactionType;

    private String autoTransactCode;

    private String lastUpdateDate;

    private Long lastUpdateBy;

    private Long lastUpdateLogin;

    private Date creationDate;

    private Long createdBy;

    private Long vendorId;

    private Long shipToOrganizationId;

    private Date expectedReceiptDate;

    private String validationFlag;

    private String estado;


    public RcvHeadersInterfaceEntrega() { }

    public RcvHeadersInterfaceEntrega(Long headerInterfaceId, Long groupId, String processingStatusCode, String reciptSourceCode, String transactionType, String autoTransactCode, String lastUpdateDate, Long lastUpdateBy, Long lastUpdateLogin, Date creationDate, Long createdBy, Long vendorId, Long shipToOrganizationId, Date expectedReceiptDate, String validationFlag, String estado) {
        this.headerInterfaceId = headerInterfaceId;
        this.groupId = groupId;
        this.processingStatusCode = processingStatusCode;
        this.reciptSourceCode = reciptSourceCode;
        this.transactionType = transactionType;
        this.autoTransactCode = autoTransactCode;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateLogin = lastUpdateLogin;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.vendorId = vendorId;
        this.shipToOrganizationId = shipToOrganizationId;
        this.expectedReceiptDate = expectedReceiptDate;
        this.validationFlag = validationFlag;
        this.estado = estado;
    }

    public Long getHeaderInterfaceId() {
        return headerInterfaceId;
    }

    public void setHeaderInterfaceId(Long headerInterfaceId) {
        this.headerInterfaceId = headerInterfaceId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getProcessingStatusCode() {
        return processingStatusCode;
    }

    public void setProcessingStatusCode(String processingStatusCode) {
        this.processingStatusCode = processingStatusCode;
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

    public String getAutoTransactCode() {
        return autoTransactCode;
    }

    public void setAutoTransactCode(String autoTransactCode) {
        this.autoTransactCode = autoTransactCode;
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

    public Long getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Long lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getShipToOrganizationId() {
        return shipToOrganizationId;
    }

    public void setShipToOrganizationId(Long shipToOrganizationId) {
        this.shipToOrganizationId = shipToOrganizationId;
    }

    public Date getExpectedReceiptDate() {
        return expectedReceiptDate;
    }

    public void setExpectedReceiptDate(Date expectedReceiptDate) {
        this.expectedReceiptDate = expectedReceiptDate;
    }

    public String getValidationFlag() {
        return validationFlag;
    }

    public void setValidationFlag(String validationFlag) {
        this.validationFlag = validationFlag;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
