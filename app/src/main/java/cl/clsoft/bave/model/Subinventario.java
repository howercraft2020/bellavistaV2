package cl.clsoft.bave.model;

public class Subinventario {
    private Long organizationId;
    private String codSubinventario;
    private String description;
    private String codLocalizador;

    public Subinventario() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodLocalizador() {
        return codLocalizador;
    }

    public void setCodLocalizador(String codLocalizador) {
        this.codLocalizador = codLocalizador;
    }

}
