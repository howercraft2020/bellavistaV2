package cl.clsoft.bave.model;

public class MtlPhysicalInventoryTags {
    private Long tagId;
    private Long physicalInventoryId;
    private Long organizationId;
    private Long inventoryItemId;
    private String subinventory;
    private Long locatorId;
    private String lotNumner;
    private String lotExpirationDate;
    private String serialNum;
    private String segment1;
    private String primaryUomCode;

    public MtlPhysicalInventoryTags(){

    }

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

    public String getLotNumner() {
        return lotNumner;
    }

    public void setLotNumner(String lotNumner) {
        this.lotNumner = lotNumner;
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
}
