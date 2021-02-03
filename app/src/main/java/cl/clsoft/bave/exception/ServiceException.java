package cl.clsoft.bave.exception;

public class ServiceException extends BaseException {

    public ServiceException(Throwable e) {
        super(e);
    }

    public ServiceException(int codigo, String mensaje, Exception e) {
        super(e);
        this.setCodigo(codigo);
        this.setDescripcion(mensaje);
    }

    public ServiceException(int codigo, String mensaje) {
        super(codigo, mensaje);
    }

}
