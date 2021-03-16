package cl.clsoft.bave.model;

public class DatosTransOrgDetalle {

    private String OrganizationCode;
    private String segment1;
    private String subinventoryCode;
    private String localizador;
    private Long transactionQuantity;
    private Long transactionInterfaceId;

    public DatosTransOrgDetalle() {
    }

    public String getOrganizationCode() {
        return OrganizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        OrganizationCode = organizationCode;
    }

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public String getSubinventoryCode() {
        return subinventoryCode;
    }

    public void setSubinventoryCode(String subinventoryCode) {
        this.subinventoryCode = subinventoryCode;
    }

    public String getLocalizador() {
        return localizador;
    }

    public void setLocalizador(String localizador) {
        this.localizador = localizador;
    }

    public Long getTransactionQuantity() {
        return transactionQuantity;
    }

    public void setTransactionQuantity(Long transactionQuantity) {
        this.transactionQuantity = transactionQuantity;
    }

    public Long getTransactionInterfaceId() {
        return transactionInterfaceId;
    }

    public void setTransactionInterfaceId(Long transactionInterfaceId) {
        this.transactionInterfaceId = transactionInterfaceId;
    }
}
