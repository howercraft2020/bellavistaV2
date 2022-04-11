package cl.clsoft.bave.model;

import java.util.Date;

public class XXPDA_RCV_HEADERS_INTERFACE {


    private Long HEADER_INTERFACE_ID;
    private Long GROUP_ID;
    private String PROCESSING_STATUS_CODE;
    private String RECEIPT_SOURCE_CODE;
    private String TRANSACTION_TYPE;
    private String AUTO_TRANSACT_CODE;
    private Date LAST_UPDATE_DATE;
    private Long LAST_UPDATED_BY;
    private Long LAST_UPDATE_LOGIN;
    private Date CREATION_DATE;
    private Long CREATED_BY;
    private Long VENDOR_ID;
    private String SHIP_TO_ORGANIZATION_CODE;
    private Date EXPECTED_RECEIPT_DATE;
    private String VALIDATION_FLAG;
    private String ESTADO;

    public XXPDA_RCV_HEADERS_INTERFACE() {
    }

    public void setHEADER_INTERFACE_ID(Long HEADER_INTERFACE_ID) {
        this.HEADER_INTERFACE_ID = HEADER_INTERFACE_ID;
    }

    public Long getHEADER_INTERFACE_ID() {
        return HEADER_INTERFACE_ID;
    }



    public Long getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(Long GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    public String getPROCESSING_STATUS_CODE() {
        return PROCESSING_STATUS_CODE;
    }

    public void setPROCESSING_STATUS_CODE(String PROCESSING_STATUS_CODE) {
        this.PROCESSING_STATUS_CODE = PROCESSING_STATUS_CODE;
    }

    public String getRECEIPT_SOURCE_CODE() {
        return RECEIPT_SOURCE_CODE;
    }

    public void setRECEIPT_SOURCE_CODE(String RECEIPT_SOURCE_CODE) {
        this.RECEIPT_SOURCE_CODE = RECEIPT_SOURCE_CODE;
    }

    public String getTRANSACTION_TYPE() {
        return TRANSACTION_TYPE;
    }

    public void setTRANSACTION_TYPE(String TRANSACTION_TYPE) {
        this.TRANSACTION_TYPE = TRANSACTION_TYPE;
    }

    public String getAUTO_TRANSACT_CODE() {
        return AUTO_TRANSACT_CODE;
    }

    public void setAUTO_TRANSACT_CODE(String AUTO_TRANSACT_CODE) {
        this.AUTO_TRANSACT_CODE = AUTO_TRANSACT_CODE;
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

    public Long getLAST_UPDATE_LOGIN() {
        return LAST_UPDATE_LOGIN;
    }

    public void setLAST_UPDATE_LOGIN(Long LAST_UPDATE_LOGIN) {
        this.LAST_UPDATE_LOGIN = LAST_UPDATE_LOGIN;
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

    public Long getVENDOR_ID() {
        return VENDOR_ID;
    }

    public void setVENDOR_ID(Long VENDOR_ID) {
        this.VENDOR_ID = VENDOR_ID;
    }

    public String getSHIP_TO_ORGANIZATION_CODE() {
        return SHIP_TO_ORGANIZATION_CODE;
    }

    public void setSHIP_TO_ORGANIZATION_CODE(String SHIP_TO_ORGANIZATION_CODE) {
        this.SHIP_TO_ORGANIZATION_CODE = SHIP_TO_ORGANIZATION_CODE;
    }

    public Date getEXPECTED_RECEIPT_DATE() {
        return EXPECTED_RECEIPT_DATE;
    }

    public void setEXPECTED_RECEIPT_DATE(Date EXPECTED_RECEIPT_DATE) {
        this.EXPECTED_RECEIPT_DATE = EXPECTED_RECEIPT_DATE;
    }

    public String getVALIDATION_FLAG() {
        return VALIDATION_FLAG;
    }

    public void setVALIDATION_FLAG(String VALIDATION_FLAG) {
        this.VALIDATION_FLAG = VALIDATION_FLAG;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }


}
