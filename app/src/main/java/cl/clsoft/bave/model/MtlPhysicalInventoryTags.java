package cl.clsoft.bave.model;

public class MtlPhysicalInventoryTags {
    private Long tagId;
    private Long physicalInventoryId;
    private Long organizationId;
    private Long inventoryItemId;
    private String subinventory;
    private Long locatorId;
    private String locatorCode;
    private String lotNumber;           //numero de lote
    private String lotExpirationDate;
    private String serialNum;           //numero de serie
    private String segment1;            //sigle
    private String primaryUomCode;      //UMD STOCK
    private Double count;                 //cantidad
    private String lastUpdated;
    private String description;
    private String longDescription;

    public MtlPhysicalInventoryTags(){  }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getPhysicalInventoryId() {
        return physicalInventoryId;
    }

    public void setPhysicalInventoryId(Long physicalInventoryId) {
        this.physicalInventoryId = physicalInventoryId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
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

    public String getLocatorCode() {
        return locatorCode;
    }

    public void setLocatorCode(String locatorCode) {
        this.locatorCode = locatorCode;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getLotExpirationDate() {
        return lotExpirationDate;
    }

    public void setLotExpirationDate(String lotExpirationDate) {
        this.lotExpirationDate = lotExpirationDate;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public String getPrimaryUomCode() {
        return primaryUomCode;
    }

    public void setPrimaryUomCode(String primaryUomCode) {
        this.primaryUomCode = primaryUomCode;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
}
