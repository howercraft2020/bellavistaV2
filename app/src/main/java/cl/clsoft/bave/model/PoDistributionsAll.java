package cl.clsoft.bave.model;

public class PoDistributionsAll {
    private Long poDistributionId;
    private Long lineLocationId;
    private Long poLineId;
    private Long poHeaderId;
    private Long distributionNum;
    private String rateDate;
    private String rate;
    private String destinationSubinventory;
    private Long deliverToLocationId;
    private Long quantityOrder;
    private Long quantityDelivered;
    private Long quantityBilled;
    private Long quantityCancelled;

    public PoDistributionsAll(){

    }

    public Long getPoDistributionId() {
        return poDistributionId;
    }

    public void setPoDistributionId(Long poDistributionId) {
        this.poDistributionId = poDistributionId;
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

    public Long getDistributionNum() {
        return distributionNum;
    }

    public void setDistributionNum(Long distributionNum) {
        this.distributionNum = distributionNum;
    }

    public String getRateDate() {
        return rateDate;
    }

    public void setRateDate(String rateDate) {
        this.rateDate = rateDate;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDestinationSubinventory() {
        return destinationSubinventory;
    }

    public void setDestinationSubinventory(String destinationSubinventory) {
        this.destinationSubinventory = destinationSubinventory;
    }

    public Long getDeliverToLocationId() {
        return deliverToLocationId;
    }

    public void setDeliverToLocationId(Long deliverToLocationId) {
        this.deliverToLocationId = deliverToLocationId;
    }

    public Long getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(Long quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public Long getQuantityDelivered() {
        return quantityDelivered;
    }

    public void setQuantityDelivered(Long quantityDelivered) {
        this.quantityDelivered = quantityDelivered;
    }

    public Long getQuantityBilled() {
        return quantityBilled;
    }

    public void setQuantityBilled(Long quantityBilled) {
        this.quantityBilled = quantityBilled;
    }

    public Long getQuantityCancelled() {
        return quantityCancelled;
    }

    public void setQuantityCancelled(Long quantityCancelled) {
        this.quantityCancelled = quantityCancelled;
    }
}
