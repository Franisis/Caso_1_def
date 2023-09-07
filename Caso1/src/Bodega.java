import java.util.ArrayList;

public class Bodega {

    public Integer tamBodega;

    public Integer contador;

    public ArrayList<String> arregloProds;

    public Bodega(int tamBodega)
    {
        this.tamBodega = tamBodega;
        arregloProds = new ArrayList<String>();
    }

    public synchronized void enviarprod(String prod)
    {
        while(arregloProds.size()==tamBodega)
        {
            try {
                System.out.println("Se pone en espera al productor: ");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.arregloProds.add(prod);
        System.out.println("El estado de la bodega luego de añadir es: " + App.bodega.arregloProds.toString());
        notifyAll();
        if (this.arregloProds.size()>=1){notify();}

    }

    public synchronized String sacarProd() {

        while(arregloProds.size()==0)
        {
                
                    System.out.println("Se pone a esperar al despachador");
                    System.out.println("Estado de la bodega en la espera: "+ App.bodega.arregloProds.toString());
                    System.out.println("Estado del contador en la espera: "+ App.contadorBodega); 
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
        }
        String x = arregloProds.remove(0);
        System.out.println("el despachador retira este producto retira este producto: " +x);
        notifyAll();
        return x;

    }

}
