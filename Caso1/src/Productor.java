public class Productor extends Thread {
    
    public Integer idProductor;

    private String producto;

    public Productor(Integer idProductor)
    {
        this.idProductor = idProductor;
        this.producto = idProductor+"m";
    }

    public void run()
    {
        System.out.println("¡Hola soy el productor: " + idProductor);
        enviar();
        
    }

    public synchronized void enviar()
    {
        System.out.println("El productor: "+ idProductor+" inserta en bodega el producto: "+ producto);
        App.bodega.enviarprod(producto);
        
    }

}
