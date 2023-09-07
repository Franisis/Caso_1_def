
public class Despachador extends Thread{

    public Despachador()
    {

    }

    public void run ()
    {
        System.out.println("Hola, soy el despachador");
        while( (App.contadorBodega>0) ){
            System.out.println("Estado del contador antes de sacar producto: "+ App.contadorBodega);
            if (App.contadorBodega == 0)
            {
                break;
            }
            String x=sacarProducto();
            insertContainer(x);
            descontarBodega();
            System.out.println(App.contadorBodega+ " products remaining");   
        }
        
    }

    public synchronized String sacarProducto()
    {
        String x = App.bodega.sacarProd();
        System.out.println("El despachador retira este producto: "+ x);
        return x;
    }

    public synchronized void descontarBodega()
    {
        App.descontarBodega();
    }

    public synchronized void insertContainer(String x)
    {
        App.contenedor.insertInContainer(x);
    }

}
