package cl.clsoft.bave.model;

public class MtlSystemItems {
    private Long inventoryItemId;
    private String description;
    private String longDescription;
    private String segment1;
    private String primaryUomCode;
    private String lotControlCode;
    private String shelfLifeCode;
    private String serialNumberControlCode;

    public MtlSystemItems(){

    }

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
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

    public String getLotControlCode() {
        return lotControlCode;
    }

    public void setLotControlCode(String lotControlCode) {
        this.lotControlCode = lotControlCode;
    }

    public String getShelfLifeCode() {
        return shelfLifeCode;
    }

    public void setShelfLifeCode(String shelfLifeCode) {
        this.shelfLifeCode = shelfLifeCode;
    }

    public String getSerialNumberControlCode() {
        return serialNumberControlCode;
    }

    public void setSerialNumberControlCode(String serialNumberControlCode) {
        this.serialNumberControlCode = serialNumberControlCode;
    }
}
