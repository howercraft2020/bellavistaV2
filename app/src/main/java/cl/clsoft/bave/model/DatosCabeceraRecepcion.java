package cl.clsoft.bave.model;

public class DatosCabeceraRecepcion {

    private Long userId;
    private Long receiptNum;
    private Long vendorId;
    private String vendorSiteCode;
    private Long vendorSiteId;
    private String currencyCode;
    private String rateType;
    private Long rate;
    private String rateDate;
    private Long termsId;

    public DatosCabeceraRecepcion() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(Long receiptNum) {
        this.receiptNum = receiptNum;
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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currrencyCode) {
        this.currencyCode = currrencyCode;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public String getRateDate() {
        return rateDate;
    }

    public void setRateDate(String rateDate) {
        this.rateDate = rateDate;
    }

    public Long getTermsId() {
        return termsId;
    }

    public void setTermsId(Long termsId) {
        this.termsId = termsId;
    }
}
