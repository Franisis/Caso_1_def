public class Productor extends Thread {

    private final Integer id;
    private final Integer numProductos;
    private  Integer numProducidos;

    private final Bodega bodega;

    public Productor (Integer id, Integer numProductos, Bodega bodega){

        this.id = id;
        this.numProductos = numProductos;
        this.bodega = bodega;
        this.numProducidos = 0;
    }

    @Override
    public void run(){

        while (numProducidos < numProductos) {

            Producto producto = new Producto(id,numProducidos);
            System.out.println("El productor " + id + " ha producido el producto " + producto.getId());
            System.out.println("Estado del producto " + producto.getId() + " : Producido");
            numProducidos ++;
            bodega.store(producto);
            if (numProducidos < numProductos){
                System.out.println("El productor " + id + " se ha dormido sobre el producto " + producto.getId());
                producto.esperarEntrega();
                System.out.println("El productor " + id + " se ha despertado");
            }

        }

        System.out.println("El productor " + id + " ha terminado su ejecución");
    }
}
