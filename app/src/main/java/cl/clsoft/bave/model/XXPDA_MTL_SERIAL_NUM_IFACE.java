package cl.clsoft.bave.model;

import java.util.Date;

public class XXPDA_MTL_SERIAL_NUM_IFACE {



    private Long TRANSACTION_INTERFACE_ID;
    private Date LAST_UPDATE_DATE;
    private Long LAST_UPDATED_BY;
    private Date CREATION_DATE;
    private Long CREATED_BY;
    private Long LAST_UPDATE_LOGIN;
    private String FM_SERIAL_NUMBER;
    private String TO_SERIAL_NUMBER;
    private String PRODUCT_CODE;
    private Long PRODUCT_TRANSACTION_ID;

    public XXPDA_MTL_SERIAL_NUM_IFACE() {
    }

    public XXPDA_MTL_SERIAL_NUM_IFACE(Long TRANSACTION_INTERFACE_ID, Date LAST_UPDATE_DATE, Long LAST_UPDATED_BY, Date CREATION_DATE, Long CREATED_BY, Long LAST_UPDATE_LOGIN, String FM_SERIAL_NUMBER, String TO_SERIAL_NUMBER, String PRODUCT_CODE, Long PRODUCT_TRANSACTION_ID) {
        this.TRANSACTION_INTERFACE_ID = TRANSACTION_INTERFACE_ID;
        this.LAST_UPDATE_DATE = LAST_UPDATE_DATE;
        this.LAST_UPDATED_BY = LAST_UPDATED_BY;
        this.CREATION_DATE = CREATION_DATE;
        this.CREATED_BY = CREATED_BY;
        this.LAST_UPDATE_LOGIN = LAST_UPDATE_LOGIN;
        this.FM_SERIAL_NUMBER = FM_SERIAL_NUMBER;
        this.TO_SERIAL_NUMBER = TO_SERIAL_NUMBER;
        this.PRODUCT_CODE = PRODUCT_CODE;
        this.PRODUCT_TRANSACTION_ID = PRODUCT_TRANSACTION_ID;
    }

    public Long getTRANSACTION_INTERFACE_ID() {
        return TRANSACTION_INTERFACE_ID;
    }

    public void setTRANSACTION_INTERFACE_ID(Long TRANSACTION_INTERFACE_ID) {
        this.TRANSACTION_INTERFACE_ID = TRANSACTION_INTERFACE_ID;
    }

    public Date getLAST_UPDATE_DATE() {
        return LAST_UPDATE_DATE;
    }

    public void setLAST_UPDATE_DATE(Date LAST_UPDATE_DATE) {
        this.LAST_UPDATE_DATE = LAST_UPDATE_DATE;
    }

    public Long getLAST_UPDATED_BY() {
        return LAST_UPDATED_BY;
    }

    public void setLAST_UPDATED_BY(Long LAST_UPDATED_BY) {
        this.LAST_UPDATED_BY = LAST_UPDATED_BY;
    }

    public Date getCREATION_DATE() {
        return CREATION_DATE;
    }

    public void setCREATION_DATE(Date CREATION_DATE) {
        this.CREATION_DATE = CREATION_DATE;
    }

    public Long getCREATED_BY() {
        return CREATED_BY;
    }

    public void setCREATED_BY(Long CREATED_BY) {
        this.CREATED_BY = CREATED_BY;
    }

    public Long getLAST_UPDATE_LOGIN() {
        return LAST_UPDATE_LOGIN;
    }

    public void setLAST_UPDATE_LOGIN(Long LAST_UPDATE_LOGIN) {
        this.LAST_UPDATE_LOGIN = LAST_UPDATE_LOGIN;
    }

    public String getFM_SERIAL_NUMBER() {
        return FM_SERIAL_NUMBER;
    }

    public void setFM_SERIAL_NUMBER(String FM_SERIAL_NUMBER) {
        this.FM_SERIAL_NUMBER = FM_SERIAL_NUMBER;
    }

    public String getTO_SERIAL_NUMBER() {
        return TO_SERIAL_NUMBER;
    }

    public void setTO_SERIAL_NUMBER(String TO_SERIAL_NUMBER) {
        this.TO_SERIAL_NUMBER = TO_SERIAL_NUMBER;
    }

    public String getPRODUCT_CODE() {
        return PRODUCT_CODE;
    }

    public void setPRODUCT_CODE(String PRODUCT_CODE) {
        this.PRODUCT_CODE = PRODUCT_CODE;
    }

    public Long getPRODUCT_TRANSACTION_ID() {
        return PRODUCT_TRANSACTION_ID;
    }

    public void setPRODUCT_TRANSACTION_ID(Long PRODUCT_TRANSACTION_ID) {
        this.PRODUCT_TRANSACTION_ID = PRODUCT_TRANSACTION_ID;
    }

}
