package cl.clsoft.bave.model;

public class PoLineLocationsAll {
    private Long lineLocationId;
    private Long poLineId;
    private Long poHeaderId;
    private Long quantity;
    private Long quantityRecived;
    private Long quantityCancelled;
    private Long quantityBilled;
    private Long shipmentNum;
    private Long shipToLocationId;
    private Long qtyRcvTolerance;
    private Long orgId;

    public PoLineLocationsAll(){

    }

    public Long getLineLocationId() {
        return lineLocationId;
    }

    public void setLineLocationId(Long lineLocationId) {
        this.lineLocationId = lineLocationId;
    }

    public Long getPoLineId() {
        return poLineId;
    }

    public void setPoLineId(Long poLineId) {
        this.poLineId = poLineId;
    }

    public Long getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(Long poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantityRecived() {
        return quantityRecived;
    }

    public void setQuantityRecived(Long quantityRecived) {
        this.quantityRecived = quantityRecived;
    }

    public Long getQuantityCancelled() {
        return quantityCancelled;
    }

    public void setQuantityCancelled(Long quantityCancelled) {
        this.quantityCancelled = quantityCancelled;
    }

    public Long getQuantityBilled() {
        return quantityBilled;
    }

    public void setQuantityBilled(Long quantityBilled) {
        this.quantityBilled = quantityBilled;
    }

    public Long getShipmentNum() {
        return shipmentNum;
    }

    public void setShipmentNum(Long shipmentNum) {
        this.shipmentNum = shipmentNum;
    }

    public Long getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(Long shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public Long getQtyRcvTolerance() {
        return qtyRcvTolerance;
    }

    public void setQtyRcvTolerance(Long qtyRcvTolerance) {
        this.qtyRcvTolerance = qtyRcvTolerance;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
