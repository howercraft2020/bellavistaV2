package cl.clsoft.bave.model;

public class MtlPhysicalSubinventories {

    private Long organizationId;
    private Long physhicalInventoryId;
    private String subinventory;

    public MtlPhysicalSubinventories(){

    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getPhyshicalInventoryId() {
        return physhicalInventoryId;
    }

    public void setPhyshicalInventoryId(Long physhicalInventoryId) {
        this.physhicalInventoryId = physhicalInventoryId;
    }

    public String getSubinventory() {
        return subinventory;
    }

    public void setSubinventory(String subinventory) {
        this.subinventory = subinventory;
    }
}
