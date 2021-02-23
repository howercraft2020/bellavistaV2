package cl.clsoft.bave.model;

public class DatosTransSubinv {

    private String transactionReference;
    private String transactionSourceName;

    public DatosTransSubinv() {}

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getTransactionSourceName() {
        return transactionSourceName;
    }

    public void setTransactionSourceName(String transactionSourceName) {
        this.transactionSourceName = transactionSourceName;
    }
}
