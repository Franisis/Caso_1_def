import java.util.Scanner;

public class App {

    public static Integer contadorRepartidores;
    public static Integer contadorBodega;
    public static Bodega bodega;
    public static Despachador despachador;
    public static Productor[] productors;
    public static Repartidor[] repartidores;
    public static Contenedor contenedor;

    public static void main(String[] args) throws Exception {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Inserte el numero de productores");
            Integer numPro=scan.nextInt();

            System.out.println("Inserte el numero de repartidores");
            Integer numRepa = scan.nextInt();

            System.out.println("Inserte el tamaño de la bodega");
            Integer tamBodega=scan.nextInt();

            
            contenedor = new Contenedor();
            contadorBodega=numPro;
            contadorRepartidores=numPro;
            repartidores = new Repartidor[numRepa];
            productors= new Productor[numPro];
            bodega = new Bodega(tamBodega);
            despachador = new Despachador();
            despachador.start();
        }

        for (int i = 0; i < productors.length; i++) {
            productors[i] = new Productor(i+1);
        }
        for (int i = 0; i < repartidores.length; i++) {
            repartidores[i] = new Repartidor(i+1);
        }
        
        

        for (Productor productor : productors) {
            productor.start();
        }

        for (Repartidor repartidor : repartidores) {
            repartidor.start();
        }
        

    }

    public synchronized static void descontarBodega()
    {
        contadorBodega-=1;
    }

    public synchronized static void descontarRepartidor() {
        contadorRepartidores-=1;
    }

    
}
