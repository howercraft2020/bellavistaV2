package cl.clsoft.bave.model;

import java.util.Date;

public class XXPDA_RCV_TRANS_INTERFACE {



    private Long INTERFACE_TRANSACTION_ID;
    private Date LAST_UPDATE_DATE;
    private Long LAST_UPDATED_BY;
    private Date CREATION_DATE;
    private Long CREATED_BY;
    private String TRANSACTION_TYPE;
    private Date TRANSACTION_DATE;
    private String PROCESSING_STATUS_CODE;
    private String PROCESSING_MODE_CODE;
    private Double QUANTITY;
    private String UNIT_OF_MEASURE;
    private Long ITEM_ID;
    private String ITEM_DESCRIPTION;
    private String UOM_CODE;
    private Long EMPLOYEE_ID;
    private Long SHIPMENT_HEADER_ID;
    private Long SHIPMENT_LINE_ID;
    private Long SHIP_TO_LOCATION_ID;
    private Long VENDOR_ID;
    private Long VENDOR_SITE_ID;
    private Long TO_ORGANIZATION_ID;
    private String SOURCE_DOCUMENT_CODE;
    private Long PARENT_TRANSACTION_ID;
    private Long PO_HEADER_ID;
    private Long PO_LINE_ID;
    private Long PO_LINE_LOCATION_ID;
    private Long PO_UNIT_PRICE;
    private String CURRENCY_CODE;
    private String CURRENCY_CONVERSION_TYPE;
    private Long CURRENCY_CONVERSION_RATE;
    private Date CURRENCY_CONVERSION_DATE;
    private Long PO_DISTRIBUTION_ID;
    private String DESTINATION_TYPE_CODE;
    private Long LOCATION_ID;
    private Long DELIVER_TO_LOCATION_ID;
    private String INSPECTION_STATUS_CODE;
    private String SUBINVENTORY;
    private Long LOCATOR_ID;
    private String SHIPMENT_NUM;
    private Long USE_MTL_LOT;
    private Long USE_MTL_SERIAL;
    private Long GROUP_ID;
    private String TRANSACTION_STATUS_CODE;
    private String AUTO_TRANSACT_CODE;
    private String RECEIPT_SOURCE_CODE;
    private String VALIDATION_FLAG;
    private Long ORG_ID;
    private String ATTRIBUTE15;



    public XXPDA_RCV_TRANS_INTERFACE() {
    }

    public XXPDA_RCV_TRANS_INTERFACE(Long INTERFACE_TRANSACTION_ID, Date LAST_UPDATE_DATE, Long LAST_UPDATED_BY, Date CREATION_DATE, Long CREATED_BY, String TRANSACTION_TYPE, Date TRANSACTION_DATE, String PROCESSING_STATUS_CODE, String PROCESSING_MODE_CODE, Double QUANTITY, String UNIT_OF_MEASURE, Long ITEM_ID, String ITEM_DESCRIPTION, String UOM_CODE, Long EMPLOYEE_ID, Long SHIPMENT_HEADER_ID, Long SHIPMENT_LINE_ID, Long SHIP_TO_LOCATION_ID, Long VENDOR_ID, Long VENDOR_SITE_ID, Long TO_ORGANIZATION_ID, String SOURCE_DOCUMENT_CODE, Long PARENT_TRANSACTION_ID, Long PO_HEADER_ID, Long PO_LINE_ID, Long PO_LINE_LOCATION_ID, Long PO_UNIT_PRICE, String CURRENCY_CODE, String CURRENCY_CONVERSION_TYPE, Long CURRENCY_CONVERSION_RATE, Date CURRENCY_CONVERSION_DATE, Long PO_DISTRIBUTION_ID, String DESTINATION_TYPE_CODE, Long LOCATION_ID, Long DELIVER_TO_LOCATION_ID, String INSPECTION_STATUS_CODE, String SUBINVENTORY, Long LOCATOR_ID, String SHIPMENT_NUM, Long USE_MTL_LOT, Long USE_MTL_SERIAL, Long GROUP_ID, String TRANSACTION_STATUS_CODE, String AUTO_TRANSACT_CODE, String RECEIPT_SOURCE_CODE, String VALIDATION_FLAG, Long ORG_ID, String ATTRIBUTE15) {
        this.INTERFACE_TRANSACTION_ID = INTERFACE_TRANSACTION_ID;
        this.LAST_UPDATE_DATE = LAST_UPDATE_DATE;
        this.LAST_UPDATED_BY = LAST_UPDATED_BY;
        this.CREATION_DATE = CREATION_DATE;
        this.CREATED_BY = CREATED_BY;
        this.TRANSACTION_TYPE = TRANSACTION_TYPE;
        this.TRANSACTION_DATE = TRANSACTION_DATE;
        this.PROCESSING_STATUS_CODE = PROCESSING_STATUS_CODE;
        this.PROCESSING_MODE_CODE = PROCESSING_MODE_CODE;
        this.QUANTITY = QUANTITY;
        this.UNIT_OF_MEASURE = UNIT_OF_MEASURE;
        this.ITEM_ID = ITEM_ID;
        this.ITEM_DESCRIPTION = ITEM_DESCRIPTION;
        this.UOM_CODE = UOM_CODE;
        this.EMPLOYEE_ID = EMPLOYEE_ID;
        this.SHIPMENT_HEADER_ID = SHIPMENT_HEADER_ID;
        this.SHIPMENT_LINE_ID = SHIPMENT_LINE_ID;
        this.SHIP_TO_LOCATION_ID = SHIP_TO_LOCATION_ID;
        this.VENDOR_ID = VENDOR_ID;
        this.VENDOR_SITE_ID = VENDOR_SITE_ID;
        this.TO_ORGANIZATION_ID = TO_ORGANIZATION_ID;
        this.SOURCE_DOCUMENT_CODE = SOURCE_DOCUMENT_CODE;
        this.PARENT_TRANSACTION_ID = PARENT_TRANSACTION_ID;
        this.PO_HEADER_ID = PO_HEADER_ID;
        this.PO_LINE_ID = PO_LINE_ID;
        this.PO_LINE_LOCATION_ID = PO_LINE_LOCATION_ID;
        this.PO_UNIT_PRICE = PO_UNIT_PRICE;
        this.CURRENCY_CODE = CURRENCY_CODE;
        this.CURRENCY_CONVERSION_TYPE = CURRENCY_CONVERSION_TYPE;
        this.CURRENCY_CONVERSION_RATE = CURRENCY_CONVERSION_RATE;
        this.CURRENCY_CONVERSION_DATE = CURRENCY_CONVERSION_DATE;
        this.PO_DISTRIBUTION_ID = PO_DISTRIBUTION_ID;
        this.DESTINATION_TYPE_CODE = DESTINATION_TYPE_CODE;
        this.LOCATION_ID = LOCATION_ID;
        this.DELIVER_TO_LOCATION_ID = DELIVER_TO_LOCATION_ID;
        this.INSPECTION_STATUS_CODE = INSPECTION_STATUS_CODE;
        this.SUBINVENTORY = SUBINVENTORY;
        this.LOCATOR_ID = LOCATOR_ID;
        this.SHIPMENT_NUM = SHIPMENT_NUM;
        this.USE_MTL_LOT = USE_MTL_LOT;
        this.USE_MTL_SERIAL = USE_MTL_SERIAL;
        this.GROUP_ID = GROUP_ID;
        this.TRANSACTION_STATUS_CODE = TRANSACTION_STATUS_CODE;
        this.AUTO_TRANSACT_CODE = AUTO_TRANSACT_CODE;
        this.RECEIPT_SOURCE_CODE = RECEIPT_SOURCE_CODE;
        this.VALIDATION_FLAG = VALIDATION_FLAG;
        this.ORG_ID = ORG_ID;
        this.ATTRIBUTE15 = ATTRIBUTE15;
    }


    public Long getINTERFACE_TRANSACTION_ID() {
        return INTERFACE_TRANSACTION_ID;
    }

    public void setINTERFACE_TRANSACTION_ID(Long INTERFACE_TRANSACTION_ID) {
        this.INTERFACE_TRANSACTION_ID = INTERFACE_TRANSACTION_ID;
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

    public String getTRANSACTION_TYPE() {
        return TRANSACTION_TYPE;
    }

    public void setTRANSACTION_TYPE(String TRANSACTION_TYPE) {
        this.TRANSACTION_TYPE = TRANSACTION_TYPE;
    }

    public Date getTRANSACTION_DATE() {
        return TRANSACTION_DATE;
    }

    public void setTRANSACTION_DATE(Date TRANSACTION_DATE) {
        this.TRANSACTION_DATE = TRANSACTION_DATE;
    }

    public String getPROCESSING_STATUS_CODE() {
        return PROCESSING_STATUS_CODE;
    }

    public void setPROCESSING_STATUS_CODE(String PROCESSING_STATUS_CODE) {
        this.PROCESSING_STATUS_CODE = PROCESSING_STATUS_CODE;
    }

    public String getPROCESSING_MODE_CODE() {
        return PROCESSING_MODE_CODE;
    }

    public void setPROCESSING_MODE_CODE(String PROCESSING_MODE_CODE) {
        this.PROCESSING_MODE_CODE = PROCESSING_MODE_CODE;
    }

    public Double getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(Double QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public String getUNIT_OF_MEASURE() {
        return UNIT_OF_MEASURE;
    }

    public void setUNIT_OF_MEASURE(String UNIT_OF_MEASURE) {
        this.UNIT_OF_MEASURE = UNIT_OF_MEASURE;
    }

    public Long getITEM_ID() {
        return ITEM_ID;
    }

    public void setITEM_ID(Long ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public String getITEM_DESCRIPTION() {
        return ITEM_DESCRIPTION;
    }

    public void setITEM_DESCRIPTION(String ITEM_DESCRIPTION) {
        this.ITEM_DESCRIPTION = ITEM_DESCRIPTION;
    }

    public String getUOM_CODE() {
        return UOM_CODE;
    }

    public void setUOM_CODE(String UOM_CODE) {
        this.UOM_CODE = UOM_CODE;
    }

    public Long getEMPLOYEE_ID() {
        return EMPLOYEE_ID;
    }

    public void setEMPLOYEE_ID(Long EMPLOYEE_ID) {
        this.EMPLOYEE_ID = EMPLOYEE_ID;
    }

    public Long getSHIPMENT_HEADER_ID() {
        return SHIPMENT_HEADER_ID;
    }

    public void setSHIPMENT_HEADER_ID(Long SHIPMENT_HEADER_ID) {
        this.SHIPMENT_HEADER_ID = SHIPMENT_HEADER_ID;
    }

    public Long getSHIPMENT_LINE_ID() {
        return SHIPMENT_LINE_ID;
    }

    public void setSHIPMENT_LINE_ID(Long SHIPMENT_LINE_ID) {
        this.SHIPMENT_LINE_ID = SHIPMENT_LINE_ID;
    }

    public Long getSHIP_TO_LOCATION_ID() {
        return SHIP_TO_LOCATION_ID;
    }

    public void setSHIP_TO_LOCATION_ID(Long SHIP_TO_LOCATION_ID) {
        this.SHIP_TO_LOCATION_ID = SHIP_TO_LOCATION_ID;
    }

    public Long getVENDOR_ID() {
        return VENDOR_ID;
    }

    public void setVENDOR_ID(Long VENDOR_ID) {
        this.VENDOR_ID = VENDOR_ID;
    }

    public Long getVENDOR_SITE_ID() {
        return VENDOR_SITE_ID;
    }

    public void setVENDOR_SITE_ID(Long VENDOR_SITE_ID) {
        this.VENDOR_SITE_ID = VENDOR_SITE_ID;
    }

    public Long getTO_ORGANIZATION_ID() {
        return TO_ORGANIZATION_ID;
    }

    public void setTO_ORGANIZATION_ID(Long TO_ORGANIZATION_ID) {
        this.TO_ORGANIZATION_ID = TO_ORGANIZATION_ID;
    }

    public String getSOURCE_DOCUMENT_CODE() {
        return SOURCE_DOCUMENT_CODE;
    }

    public void setSOURCE_DOCUMENT_CODE(String SOURCE_DOCUMENT_CODE) {
        this.SOURCE_DOCUMENT_CODE = SOURCE_DOCUMENT_CODE;
    }

    public Long getPARENT_TRANSACTION_ID() {
        return PARENT_TRANSACTION_ID;
    }

    public void setPARENT_TRANSACTION_ID(Long PARENT_TRANSACTION_ID) {
        this.PARENT_TRANSACTION_ID = PARENT_TRANSACTION_ID;
    }

    public Long getPO_HEADER_ID() {
        return PO_HEADER_ID;
    }

    public void setPO_HEADER_ID(Long PO_HEADER_ID) {
        this.PO_HEADER_ID = PO_HEADER_ID;
    }

    public Long getPO_LINE_ID() {
        return PO_LINE_ID;
    }

    public void setPO_LINE_ID(Long PO_LINE_ID) {
        this.PO_LINE_ID = PO_LINE_ID;
    }

    public Long getPO_LINE_LOCATION_ID() {
        return PO_LINE_LOCATION_ID;
    }

    public void setPO_LINE_LOCATION_ID(Long PO_LINE_LOCATION_ID) {
        this.PO_LINE_LOCATION_ID = PO_LINE_LOCATION_ID;
    }

    public Long getPO_UNIT_PRICE() {
        return PO_UNIT_PRICE;
    }

    public void setPO_UNIT_PRICE(Long PO_UNIT_PRICE) {
        this.PO_UNIT_PRICE = PO_UNIT_PRICE;
    }

    public String getCURRENCY_CODE() {
        return CURRENCY_CODE;
    }

    public void setCURRENCY_CODE(String CURRENCY_CODE) {
        this.CURRENCY_CODE = CURRENCY_CODE;
    }

    public String getCURRENCY_CONVERSION_TYPE() {
        return CURRENCY_CONVERSION_TYPE;
    }

    public void setCURRENCY_CONVERSION_TYPE(String CURRENCY_CONVERSION_TYPE) {
        this.CURRENCY_CONVERSION_TYPE = CURRENCY_CONVERSION_TYPE;
    }

    public Long getCURRENCY_CONVERSION_RATE() {
        return CURRENCY_CONVERSION_RATE;
    }

    public void setCURRENCY_CONVERSION_RATE(Long CURRENCY_CONVERSION_RATE) {
        this.CURRENCY_CONVERSION_RATE = CURRENCY_CONVERSION_RATE;
    }

    public Date getCURRENCY_CONVERSION_DATE() {
        return CURRENCY_CONVERSION_DATE;
    }

    public void setCURRENCY_CONVERSION_DATE(Date CURRENCY_CONVERSION_DATE) {
        this.CURRENCY_CONVERSION_DATE = CURRENCY_CONVERSION_DATE;
    }

    public Long getPO_DISTRIBUTION_ID() {
        return PO_DISTRIBUTION_ID;
    }

    public void setPO_DISTRIBUTION_ID(Long PO_DISTRIBUTION_ID) {
        this.PO_DISTRIBUTION_ID = PO_DISTRIBUTION_ID;
    }

    public String getDESTINATION_TYPE_CODE() {
        return DESTINATION_TYPE_CODE;
    }

    public void setDESTINATION_TYPE_CODE(String DESTINATION_TYPE_CODE) {
        this.DESTINATION_TYPE_CODE = DESTINATION_TYPE_CODE;
    }

    public Long getLOCATION_ID() {
        return LOCATION_ID;
    }

    public void setLOCATION_ID(Long LOCATION_ID) {
        this.LOCATION_ID = LOCATION_ID;
    }

    public Long getDELIVER_TO_LOCATION_ID() {
        return DELIVER_TO_LOCATION_ID;
    }

    public void setDELIVER_TO_LOCATION_ID(Long DELIVER_TO_LOCATION_ID) {
        this.DELIVER_TO_LOCATION_ID = DELIVER_TO_LOCATION_ID;
    }

    public String getINSPECTION_STATUS_CODE() {
        return INSPECTION_STATUS_CODE;
    }

    public void setINSPECTION_STATUS_CODE(String INSPECTION_STATUS_CODE) {
        this.INSPECTION_STATUS_CODE = INSPECTION_STATUS_CODE;
    }

    public String getSUBINVENTORY() {
        return SUBINVENTORY;
    }

    public void setSUBINVENTORY(String SUBINVENTORY) {
        this.SUBINVENTORY = SUBINVENTORY;
    }

    public Long getLOCATOR_ID() {
        return LOCATOR_ID;
    }

    public void setLOCATOR_ID(Long LOCATOR_ID) {
        this.LOCATOR_ID = LOCATOR_ID;
    }

    public String getSHIPMENT_NUM() {
        return SHIPMENT_NUM;
    }

    public void setSHIPMENT_NUM(String SHIPMENT_NUM) {
        this.SHIPMENT_NUM = SHIPMENT_NUM;
    }

    public Long getUSE_MTL_LOT() {
        return USE_MTL_LOT;
    }

    public void setUSE_MTL_LOT(Long USE_MTL_LOT) {
        this.USE_MTL_LOT = USE_MTL_LOT;
    }

    public Long getUSE_MTL_SERIAL() {
        return USE_MTL_SERIAL;
    }

    public void setUSE_MTL_SERIAL(Long USE_MTL_SERIAL) {
        this.USE_MTL_SERIAL = USE_MTL_SERIAL;
    }

    public Long getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(Long GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    public String getTRANSACTION_STATUS_CODE() {
        return TRANSACTION_STATUS_CODE;
    }

    public void setTRANSACTION_STATUS_CODE(String TRANSACTION_STATUS_CODE) {
        this.TRANSACTION_STATUS_CODE = TRANSACTION_STATUS_CODE;
    }

    public String getAUTO_TRANSACT_CODE() {
        return AUTO_TRANSACT_CODE;
    }

    public void setAUTO_TRANSACT_CODE(String AUTO_TRANSACT_CODE) {
        this.AUTO_TRANSACT_CODE = AUTO_TRANSACT_CODE;
    }

    public String getRECEIPT_SOURCE_CODE() {
        return RECEIPT_SOURCE_CODE;
    }

    public void setRECEIPT_SOURCE_CODE(String RECEIPT_SOURCE_CODE) {
        this.RECEIPT_SOURCE_CODE = RECEIPT_SOURCE_CODE;
    }

    public String getVALIDATION_FLAG() {
        return VALIDATION_FLAG;
    }

    public void setVALIDATION_FLAG(String VALIDATION_FLAG) {
        this.VALIDATION_FLAG = VALIDATION_FLAG;
    }

    public Long getORG_ID() {
        return ORG_ID;
    }

    public void setORG_ID(Long ORG_ID) {
        this.ORG_ID = ORG_ID;
    }

    public String getATTRIBUTE15() {
        return ATTRIBUTE15;
    }

    public void setATTRIBUTE15(String ATTRIBUTE15) {
        this.ATTRIBUTE15 = ATTRIBUTE15;
    }
}
