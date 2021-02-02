package cl.clsoft.bave.model;

public class Subinventario {
    private Long organizationId;
    private String codSubinventario;
    private String codLocalizador;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getCodSubinventario() {
        return codSubinventario;
    }

    public void setCodSubinventario(String codSubinventario) {
        this.codSubinventario = codSubinventario;
    }

    public String getCosLocalizador() {
        return codLocalizador;
    }

    public void setCodLocalizador(String cosLocalizador) {
        this.codLocalizador = cosLocalizador;
    }

    public Subinventario(){

    }
}
