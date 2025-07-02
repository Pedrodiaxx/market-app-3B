package tecdesoftware.markep_app.persistence;

import jdk.jfr.Registered;
import tecdesoftware.markep_app.persistence.crud.ProductoCrudRepository;
import tecdesoftware.markep_app.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

//Le dice a Spring que este repositorio se conecta con la BD
@Registered
public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    //Este ejemplo me va a dar todos los productos de mi BD
    public List<Producto> getALL() {
        //convirtiendo un iterable<T> a una lista de productos List <Producto>
        return (List<Producto>) productoCrudRepository.findAll();
    }

    //obtiener los productos por categoria ascedente
    public List<Producto> findByCategoriaOrderByNombreAsc(int idcategoria) {
        return productoCrudRepository.findByCategoriaOrderByNombreAsc(idcategoria);
    }

    public Optional<List<Producto>> getEscasos (int cantidad) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    //obtener un metodo dado el id
    public Optional<Producto> getProductoById(int idProducto) {
        return productoCrudRepository.findById(idProducto);
    }

    //Guardar un producto
    public Producto saveProducto(Producto producto) {
        return productoCrudRepository.save(producto);
    }

    //Borrar un producto
    public void delete(Producto producto) {
        productoCrudRepository.delete(producto);
    }



}
