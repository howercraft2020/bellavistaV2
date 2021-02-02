package cl.clsoft.bave.model;

public class MtlCycleCountHeaders {
    private Long cycleCountHeaderId;
    private Long organizationId;
    private String lastUpdateDate;
    private Long lastUpdatedBy;
    private String creationDate;
    private Long createdBy;
    private String cycleCountHeaderName;
    private Long userId;
    private Long employeeId;
    private String description;

    public MtlCycleCountHeaders(){

    }

    public Long getCycleCountHeaderId() {
        return cycleCountHeaderId;
    }

    public void setCycleCountHeaderId(Long cycleCountHeaderId) {
        this.cycleCountHeaderId = cycleCountHeaderId;
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

    public String getCycleCountHeaderName() {
        return cycleCountHeaderName;
    }

    public void setCycleCountHeaderName(String cycleCountHeaderName) {
        this.cycleCountHeaderName = cycleCountHeaderName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
