package cl.clsoft.bave.dto;

import java.util.ArrayList;
import java.util.List;

public class TransactionDetalleDto {

    private Long shipmentHeaderId;
    private Long transactionId;
    private Double cantidad;
    private String subinventoryCode;
    private String locatorCode;
    private boolean isLote = false;
    private boolean isSerie = false;
    private String lote;
    private String loteProveedor;
    private String vencimiento;
    private String categoria;
    private String atributo1;
    private String atributo2;
    private String atributo3;
    private List<String> series = new ArrayList<>();

    public TransactionDetalleDto() {}

    public Long getShipmentHeaderId() {
        return shipmentHeaderId;
    }

    public void setShipmentHeaderId(Long shipmentHeaderId) {
        this.shipmentHeaderId = shipmentHeaderId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getSubinventoryCode() {
        return subinventoryCode;
    }

    public void setSubinventoryCode(String subinventoryCode) {
        this.subinventoryCode = subinventoryCode;
    }

    public String getLocatorCode() {
        return locatorCode;
    }

    public void setLocatorCode(String locatorCode) {
        this.locatorCode = locatorCode;
    }

    public boolean isLote() {
        return isLote;
    }

    public void setLote(boolean lote) {
        isLote = lote;
    }

    public boolean isSerie() {
        return isSerie;
    }

    public void setSerie(boolean serie) {
        isSerie = serie;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getLoteProveedor() {
        return loteProveedor;
    }

    public void setLoteProveedor(String loteProveedor) {
        this.loteProveedor = loteProveedor;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAtributo1() {
        return atributo1;
    }

    public void setAtributo1(String atributo1) {
        this.atributo1 = atributo1;
    }

    public String getAtributo2() {
        return atributo2;
    }

    public void setAtributo2(String atributo2) {
        this.atributo2 = atributo2;
    }

    public String getAtributo3() {
        return atributo3;
    }

    public void setAtributo3(String atributo3) {
        this.atributo3 = atributo3;
    }

    public List<String> getSeries() {
        return series;
    }

    public void setSeries(List<String> series) {
        this.series = series;
    }
}
