import java.util.ArrayList;

public class Contenedor {

    private final ArrayList<Producto> buffer; // where the products are stored
    private final int tamano; // buffer size


    public Contenedor () {

        this.buffer = new ArrayList<>();
        this.tamano = 1;
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
        System.out.println("Estado de producto " + producto.getId() + " : Despachado");
        notify();
    }

    public synchronized Producto retrieve(){
        while (buffer.isEmpty() && Despachador.getEjecutarRepartidores()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (!buffer.isEmpty()){
            Producto producto = buffer.remove(0);
            notifyAll();
            return producto;
        } else {
            return null;
        }
    }


}
