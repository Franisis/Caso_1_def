public class Despachador extends  Thread {

    private static boolean ejecutarRepartidores = true;
    private final Integer numProductores;
    private final  Integer numRepartidores;
    private final  Integer numProductos;
    private final Bodega bodega;
    private final Contenedor deposito;
    private Integer numDespachados;

    public Despachador (Integer numProductores, Integer numRepartidores, Integer numProductos,
                        Bodega bodega, Contenedor deposito) {

        this.numProductores = numProductores;
        this.numRepartidores = numRepartidores;
        this.numProductos = numProductos;
        this.bodega = bodega;
        this.deposito = deposito;
        this.numDespachados = 0;
    }

    @Override
    public void run() {
        while (numDespachados < numProductos) {
                while (bodega.isEmpty()){
                    condicion();
                }
                Producto producto = bodega.retrieve();
                System.out.println("Estado del producto " + producto.getId() + " : en Despacho");
                deposito.store(producto);
                numDespachados++;
        }
        ejecutarRepartidores = false;
        System.out.println("El despachador a terminado su ejecución");

    }


    public static boolean getEjecutarRepartidores(){

        return ejecutarRepartidores;
    }

    public boolean condicion (){

        return Math.random() < 0.5;
    }
}
