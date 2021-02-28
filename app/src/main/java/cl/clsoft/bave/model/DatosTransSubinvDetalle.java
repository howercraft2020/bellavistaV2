package cl.clsoft.bave.model;

public class DatosTransSubinvDetalle {

    private String segment1;
    private String subinventoryCode;
    private String localizador;
    private String subinventarioHasta;
    private String localizadorHasta;
    private Long transactionQuantity;

    public DatosTransSubinvDetalle() {
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

    public String getSubinventarioHasta() {
        return subinventarioHasta;
    }

    public void setSubinventarioHasta(String subinventarioHasta) {
        this.subinventarioHasta = subinventarioHasta;
    }

    public String getLocalizadorHasta() {
        return localizadorHasta;
    }

    public void setLocalizadorHasta(String localizadorHasta) {
        this.localizadorHasta = localizadorHasta;
    }

    public Long getTransactionQuantity() {
        return transactionQuantity;
    }

    public void setTransactionQuantity(Long transactionQuantity) {
        this.transactionQuantity = transactionQuantity;
    }
}