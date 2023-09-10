import java.util.concurrent.ThreadLocalRandom;

public class Repartidor extends Thread {

    private final Integer id;

    private final Contenedor deposito;

    public Repartidor (Integer id, Contenedor deposito){

        this.id = id;
        this.deposito = deposito;

    }

    @Override
    public void run(){

        boolean working = true;

        while (working) {

            Producto producto = deposito.retrieve();
            if (producto != null) {
            System.out.println("Estado del producto " + producto.getId() + " : en Reparto");

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            producto.realizarEntrega();
            System.out.println("Estado del producto " + producto.getId() + " : Entregado");
            }
            working = Despachador.getEjecutarRepartidores();


        }

        System.out.println("El repartidor " + id + " ha terminado su ejecución");


    }
}
