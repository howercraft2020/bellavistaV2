package cl.clsoft.bave.exception;

public class DaoException extends BaseException {

    public DaoException(Throwable e) {
        super(e);
    }

    public DaoException(int codigo, String mensaje, Exception e) {
        super(e);
        this.setCodigo(codigo);
        this.setDescripcion(mensaje);
    }

    public DaoException(int codigo, String mensaje) {
        super(codigo, mensaje);
    }

}
