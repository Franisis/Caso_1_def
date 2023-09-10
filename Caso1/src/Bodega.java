import java.util.ArrayList;

public class Bodega {

    private final ArrayList<Producto> buffer; // where the products are stored
    private final int tamano; // buffer size


    public Bodega (int tamano) {

        this.buffer = new ArrayList<>();
        this.tamano = tamano;
    }

    public synchronized void store(Producto producto){
        while(buffer.size() == tamano){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        buffer.add(producto);
        System.out.println("Estado del producto " + producto.getId() + " : en Bodega");
    }

    public synchronized Producto retrieve(){

        Producto producto = buffer.remove(0);
        notify();
        return producto;
    }

    public boolean isEmpty() {

        return buffer.isEmpty();
    }
}
