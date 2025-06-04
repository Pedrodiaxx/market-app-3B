package tecdesoftware.markep_app.persistence;

import tecdesoftware.markep_app.persistence.crud.ProductoCrudRepository;
import tecdesoftware.markep_app.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    //Este ejemplo me va a dar todos los productos de mi BD
    public List<Producto> getALL() {
        //convirtiendo un iterable<T> a una lista de productos List <Producto>
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
