package cl.clsoft.bave.model;

public class MtlCycleCountEntries {
    private Long inventoriItemId;
    private Long subinventory;
    private String entryStatusCode;
    private Long organizationId;
    private String cycleCountHeaderId;
    private Long LocatorId;
    private String lotNumber;
    private Long segment1;
    private Long primaryUomCode;
    private String serialNumber;

    public MtlCycleCountEntries(){

    }

    public Long getInventoriItemId() {
        return inventoriItemId;
    }

    public void setInventoriItemId(Long inventoriItemId) {
        this.inventoriItemId = inventoriItemId;
    }

    public Long getSubinventory() {
        return subinventory;
    }

    public void setSubinventory(Long subinventory) {
        this.subinventory = subinventory;
    }

    public String getEntryStatusCode() {
        return entryStatusCode;
    }

    public void setEntryStatusCode(String entryStatusCode) {
        this.entryStatusCode = entryStatusCode;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getCycleCountHeaderId() {
        return cycleCountHeaderId;
    }

    public void setCycleCountHeaderId(String cycleCountHeaderId) {
        this.cycleCountHeaderId = cycleCountHeaderId;
    }

    public Long getLocatorId() {
        return LocatorId;
    }

    public void setLocatorId(Long locatorId) {
        LocatorId = locatorId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Long getSegment1() {
        return segment1;
    }

    public void setSegment1(Long segment1) {
        this.segment1 = segment1;
    }

    public Long getPrimaryUomCode() {
        return primaryUomCode;
    }

    public void setPrimaryUomCode(Long primaryUomCode) {
        this.primaryUomCode = primaryUomCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
