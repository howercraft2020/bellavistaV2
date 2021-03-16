package cl.clsoft.bave.dto;

import java.util.ArrayList;
import java.util.List;

public class MtlTransactionOrgDto {

    private Long transactionInterfaceId;
    private String orgDestino;
    private Double cantidad;
    private Long inventoryItemId;
    private String subinvDesde;
    private String localizador;
    private String glosa;
    private boolean isLote = false;
    private boolean isSerie = false;
    private String lote;
    private List<String> series = new ArrayList<>();

    public MtlTransactionOrgDto(){}

    public Long getTransactionInterfaceId() {
        return transactionInterfaceId;
    }

    public void setTransactionInterfaceId(Long transactionInterfaceId) {
        this.transactionInterfaceId = transactionInterfaceId;
    }

    public String getOrgDestino() {
        return orgDestino;
    }

    public void setOrgDestino(String orgDestino) {
        this.orgDestino = orgDestino;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getSubinvDesde() {
        return subinvDesde;
    }

    public void setSubinvDesde(String subinvDesde) {
        this.subinvDesde = subinvDesde;
    }

    public String getLocalizador() {
        return localizador;
    }

    public void setLocalizador(String localizador) {
        this.localizador = localizador;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
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

    public List<String> getSeries() {
        return series;
    }

    public void setSeries(List<String> series) {
        this.series = series;
    }
}
