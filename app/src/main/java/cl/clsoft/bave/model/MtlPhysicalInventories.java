package cl.clsoft.bave.model;

public class MtlPhysicalInventories {
    private Long physicalInventoryId;
    private Long organizationId;
    private String lastUpdateDate;
    private Long lastUpdatedBy;
    private String creationDate;
    private Long createdBy;
    private String physicalInventoryDate;
    private String description;
    private String freezeDate;
    private String physicalInventoryName;
    private Long userId;
    private Long employeeId;
    private Long approvalRequired;
    private Long allSubinventoriesFlag;

    public MtlPhysicalInventories(){

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

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getPhysicalInventoryDate() {
        return physicalInventoryDate;
    }

    public void setPhysicalInventoryDate(String physicalInventoryDate) {
        this.physicalInventoryDate = physicalInventoryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(String freezeDate) {
        this.freezeDate = freezeDate;
    }

    public String getPhysicalInventoryName() {
        return physicalInventoryName;
    }

    public void setPhysicalInventoryName(String physicalInventoryName) {
        this.physicalInventoryName = physicalInventoryName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getApprovalRequired() {
        return approvalRequired;
    }

    public void setApprovalRequired(Long approvalRequired) {
        this.approvalRequired = approvalRequired;
    }

    public Long getAllSubinventoriesFlag() {
        return allSubinventoriesFlag;
    }

    public void setAllSubinventoriesFlag(Long allSubinventoriesFlag) {
        this.allSubinventoriesFlag = allSubinventoriesFlag;
    }
}
