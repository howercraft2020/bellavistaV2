package cl.clsoft.bave.model;

import java.util.Date;

public class XXPDA_MTL_TRANS_LOTS_IFACE {



    private Long TRANSACTION_INTERFACE_ID;
    private Date LAST_UPDATE_DATE;
    private Long LAST_UPDATED_BY;
    private Date CREATION_DATE;
    private Long CREATED_BY;
    private Long LAST_UPDATE_LOGIN;
    private String LOT_NUMBER;
    private Double TRANSACTION_QUANTITY;
    private Double PRIMARY_QUANTITY;
    private Long SERIAL_TRANSACTION_TEMP_ID;
    private String PRODUCT_CODE;
    private Long PRODUCT_TRANSACTION_ID;
    private String SUPPLIER_LOT_NUMBER;
    private Date LOT_EXPIRATION_DATE;
    private String ATTRIBUTE_CATEGORY;
    private String ATTRIBUTE1;
    private String ATTRIBUTE2;
    private String ATTRIBUTE3;


    public XXPDA_MTL_TRANS_LOTS_IFACE() {
    }

    public XXPDA_MTL_TRANS_LOTS_IFACE(Long TRANSACTION_INTERFACE_ID, Date LAST_UPDATE_DATE, Long LAST_UPDATED_BY, Date CREATION_DATE, Long CREATED_BY, Long LAST_UPDATE_LOGIN, String LOT_NUMBER, Double TRANSACTION_QUANTITY, Double PRIMARY_QUANTITY, Long SERIAL_TRANSACTION_TEMP_ID, String PRODUCT_CODE, Long PRODUCT_TRANSACTION_ID, String SUPPLIER_LOT_NUMBER, Date LOT_EXPIRATION_DATE, String ATTRIBUTE_CATEGORY, String ATTRIBUTE1, String ATTRIBUTE2, String ATTRIBUTE3) {
        this.TRANSACTION_INTERFACE_ID = TRANSACTION_INTERFACE_ID;
        this.LAST_UPDATE_DATE = LAST_UPDATE_DATE;
        this.LAST_UPDATED_BY = LAST_UPDATED_BY;
        this.CREATION_DATE = CREATION_DATE;
        this.CREATED_BY = CREATED_BY;
        this.LAST_UPDATE_LOGIN = LAST_UPDATE_LOGIN;
        this.LOT_NUMBER = LOT_NUMBER;
        this.TRANSACTION_QUANTITY = TRANSACTION_QUANTITY;
        this.PRIMARY_QUANTITY = PRIMARY_QUANTITY;
        this.SERIAL_TRANSACTION_TEMP_ID = SERIAL_TRANSACTION_TEMP_ID;
        this.PRODUCT_CODE = PRODUCT_CODE;
        this.PRODUCT_TRANSACTION_ID = PRODUCT_TRANSACTION_ID;
        this.SUPPLIER_LOT_NUMBER = SUPPLIER_LOT_NUMBER;
        this.LOT_EXPIRATION_DATE = LOT_EXPIRATION_DATE;
        this.ATTRIBUTE_CATEGORY = ATTRIBUTE_CATEGORY;
        this.ATTRIBUTE1 = ATTRIBUTE1;
        this.ATTRIBUTE2 = ATTRIBUTE2;
        this.ATTRIBUTE3 = ATTRIBUTE3;
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

    public String getLOT_NUMBER() {
        return LOT_NUMBER;
    }

    public void setLOT_NUMBER(String LOT_NUMBER) {
        this.LOT_NUMBER = LOT_NUMBER;
    }

    public Double getTRANSACTION_QUANTITY() {
        return TRANSACTION_QUANTITY;
    }

    public void setTRANSACTION_QUANTITY(Double TRANSACTION_QUANTITY) {
        this.TRANSACTION_QUANTITY = TRANSACTION_QUANTITY;
    }

    public Double getPRIMARY_QUANTITY() {
        return PRIMARY_QUANTITY;
    }

    public void setPRIMARY_QUANTITY(Double PRIMARY_QUANTITY) {
        this.PRIMARY_QUANTITY = PRIMARY_QUANTITY;
    }

    public Long getSERIAL_TRANSACTION_TEMP_ID() {
        return SERIAL_TRANSACTION_TEMP_ID;
    }

    public void setSERIAL_TRANSACTION_TEMP_ID(Long SERIAL_TRANSACTION_TEMP_ID) {
        this.SERIAL_TRANSACTION_TEMP_ID = SERIAL_TRANSACTION_TEMP_ID;
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

    public String getSUPPLIER_LOT_NUMBER() {
        return SUPPLIER_LOT_NUMBER;
    }

    public void setSUPPLIER_LOT_NUMBER(String SUPPLIER_LOT_NUMBER) {
        this.SUPPLIER_LOT_NUMBER = SUPPLIER_LOT_NUMBER;
    }

    public Date getLOT_EXPIRATION_DATE() {
        return LOT_EXPIRATION_DATE;
    }

    public void setLOT_EXPIRATION_DATE(Date LOT_EXPIRATION_DATE) {
        this.LOT_EXPIRATION_DATE = LOT_EXPIRATION_DATE;
    }

    public String getATTRIBUTE_CATEGORY() {
        return ATTRIBUTE_CATEGORY;
    }

    public void setATTRIBUTE_CATEGORY(String ATTRIBUTE_CATEGORY) {
        this.ATTRIBUTE_CATEGORY = ATTRIBUTE_CATEGORY;
    }

    public String getATTRIBUTE1() {
        return ATTRIBUTE1;
    }

    public void setATTRIBUTE1(String ATTRIBUTE1) {
        this.ATTRIBUTE1 = ATTRIBUTE1;
    }

    public String getATTRIBUTE2() {
        return ATTRIBUTE2;
    }

    public void setATTRIBUTE2(String ATTRIBUTE2) {
        this.ATTRIBUTE2 = ATTRIBUTE2;
    }

    public String getATTRIBUTE3() {
        return ATTRIBUTE3;
    }

    public void setATTRIBUTE3(String ATTRIBUTE3) {
        this.ATTRIBUTE3 = ATTRIBUTE3;
    }
}
