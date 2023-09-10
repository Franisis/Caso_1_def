
public class Producto {

    private final String id;

    public Producto (Integer idProductor, Integer idProducto) {

        this.id = Integer.toString(idProductor) + Integer.toString(idProducto);
    }

    public synchronized void esperarEntrega () {
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized void realizarEntrega () {

        notify();

    }

    public String getId(){
        return id;
    }
}
