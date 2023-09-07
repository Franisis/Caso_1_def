import java.util.ArrayList;

public class Contenedor {

    public boolean isFull;

    public static ArrayList<String> container;

    public Contenedor()
    {
        this.isFull = false;
        container = new ArrayList<String>();
    }

    public synchronized String sacarProd()
    {
        while(Contenedor.container.size()==0)
        { 
             
            try {
                System.out.println("Se pone en espera al repartidor");
                System.out.println("Estado del contenedor: "+ container.toString());
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String x = container.remove(0);
        isFull=!isFull;
        Boolean mew= container.size()>0;
        if (mew){notifyAll();}
        System.out.println("¿El contenedor está lleno? " + mew);
        notifyAll();
        return x+"CS";
    }

    public synchronized void insertInContainer(String prod)
    {
        while(container.size()>1)
        {
           //ESPERA ACTIVA!!!!!
           /*
            try {
                System.out.println("Se pone despachador en espera de container");
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            */ 
        }
        Boolean mew= container.size()>0;
        System.out.println("¿El contenedor está lleno y no se puede insertar nada? " + mew);
        container.add(prod+"CE");
        isFull=!isFull;
        notifyAll();

    }

}
