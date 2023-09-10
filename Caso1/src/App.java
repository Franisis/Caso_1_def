import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class App {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserte el numero de productores a generar");
        Integer numProductores=scan.nextInt();

        System.out.println("Inserte el numero de repartidores a generar");
        Integer numRepartidores = scan.nextInt();

        System.out.println("Inserte el numero de productos total");
        Integer numProductos=scan.nextInt();

        System.out.println("Inserte el tamaño de la bodega");
        Integer tamanoBodega=scan.nextInt();


        Contenedor deposito = new Contenedor();
        ArrayList<Productor> productores = new ArrayList<>();
        ArrayList<Repartidor> repartidores = new ArrayList<>();
        Bodega bodega = new Bodega(tamanoBodega);
        Despachador despachador = new Despachador(numProductores, numRepartidores,
                                        numProductos, bodega, deposito);
        Integer numProducir = 1;


        if (numProductos <= numProductores) {
            for (int i = 0; i < numProductores; i++) {
                if (i < numProductos){
                productores.add(new Productor(i,numProducir,bodega));
                } else {
                    productores.add(new Productor(i,0,bodega));
                }
            }
        } else {
            numProducir = Math.round((float) numProductos / numProductores);
            for (int i = 0; i < numProductores; i++) {

                if (i == numProductores-1) {
                    productores.add(new Productor(i, numProductos-((numProductores-1)*numProducir), bodega));
                } else {
                    productores.add(new Productor(i, numProducir, bodega));
                }
            }
        }
        for (int i = 0; i < numRepartidores; i++) {
            repartidores.add(new Repartidor(i,deposito));
        }

        for (int i = 0; i < numProductores; i++) {
            productores.get(i).start();
        }
        for (int i = 0; i < numRepartidores; i++) {
            repartidores.get(i).start();
        }
        despachador.start();
        }
}