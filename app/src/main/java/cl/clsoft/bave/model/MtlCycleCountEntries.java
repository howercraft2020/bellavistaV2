package cl.clsoft.bave.model;

public class MtlCycleCountEntries {
    private Long cycleCountEntryId;
    private Long inventoriItemId;
    private String subinventory;
    private String entryStatusCode;
    private Long organizationId;
    private Long cycleCountHeaderId;
    private Long LocatorId;
    private String lotNumber;
    private String segment1;        //sigle
    private String primaryUomCode;
    private String serialNumber;

    public MtlCycleCountEntries(){

    }

    public Long getCycleCountEntryId() {
        return cycleCountEntryId;
    }

    public void setCycleCountEntryId(Long cycleCountEntryId) {
        this.cycleCountEntryId = cycleCountEntryId;
    }

    public Long getInventoriItemId() {
        return inventoriItemId;
    }

    public void setInventoriItemId(Long inventoriItemId) {
        this.inventoriItemId = inventoriItemId;
    }

    public String getSubinventory() {
        return subinventory;
    }

    public void setSubinventory(String subinventory) {
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

    public Long getCycleCountHeaderId() {
        return cycleCountHeaderId;
    }

    public void setCycleCountHeaderId(Long cycleCountHeaderId) {
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
