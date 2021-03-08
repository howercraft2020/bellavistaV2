package cl.clsoft.bave.model;

public class MtlSerialNumbersInterface {

    private Long transactionInterfaceId;
    private String lastUpdateDate;
    private Long lastUpdatedBy;
    private String creationDate;
    private Long createdBy;
    private Long lastUpdateLogin;
    private String fmSerialNumber;
    private String toSerialNumber;
    private String productCode;
    private Long productTransactionId;

    public MtlSerialNumbersInterface() {
    }

    public Long getTransactionInterfaceId() {
        return transactionInterfaceId;
    }

    public void setTransactionInterfaceId(Long transactionInterfaceId) {
        this.transactionInterfaceId = transactionInterfaceId;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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

    public Long getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Long lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String getFmSerialNumber() {
        return fmSerialNumber;
    }

    public void setFmSerialNumber(String fmSerialNumber) {
        this.fmSerialNumber = fmSerialNumber;
    }

    public String getToSerialNumber() {
        return toSerialNumber;
    }

    public void setToSerialNumber(String toSerialNumber) {
        this.toSerialNumber = toSerialNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getProductTransactionId() {
        return productTransactionId;
    }

    public void setProductTransactionId(Long productTransactionId) {
        this.productTransactionId = productTransactionId;
    }
}
