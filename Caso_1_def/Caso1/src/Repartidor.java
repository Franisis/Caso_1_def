
public class Repartidor extends Thread {

    public Integer idRepartidor;

    public Repartidor(Integer id)
    {
        this.idRepartidor=id;
    }


    public void run()
    {
        System.out.println("Hola, soy el repartidor: " + idRepartidor);
         
        while(Despachador.marcaDespachador())
        {
            
            System.out.println("Repartidor mira contador de reparticiones hechas: "+ App.contadorRepartidores);
            sacarProdContainer();
            descontarRepartidor();

            
        }
        if (!Despachador.marcaDespachador())
            {
                System.out.println("Yo el repartidor: "+ idRepartidor +" .Termino el proceso. ");
                System.exit(0);
            }
        

    }

    


    public void sacarProdContainer()
    {
        String x = App.contenedor.sacarProd()+"R"+ this.idRepartidor;
        System.out.println("El repartidor "+ idRepartidor+ " saca el producto: "+ x);
        System.out.println("Estado del contenedor cuando se REPARTE el producto: "+ Contenedor.container.toString());
        
        
    }

    public void descontarRepartidor()
    {
        App.descontarRepartidor();
        System.out.println("Se descuenta el numero de productos");
    }   
    


}