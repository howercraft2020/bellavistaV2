package cl.clsoft.bave.exception;

public abstract class BaseException extends Exception {

    private int codigo;
    private String descripcion;

    public BaseException(Throwable e) {
        super(e);
    }

    public BaseException(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public BaseException(int codigo, String descripcion, Throwable t) {
        super(t);
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
