package tecdesoftware.markep_app.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import tecdesoftware.markep_app.persistence.entity.Producto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //Query methods
    //SQL : Select*
    //FROM: Categoria
    //where Id_categoria = ?
    //ORDER BY Nombre ASC
    List<Producto> findByCategoriaOrderByNombreAsc(int idcategoria);


    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}

