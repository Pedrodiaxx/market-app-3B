package tecdesoftware.markep_app.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import tecdesoftware.markep_app.persistence.entity.Producto;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
}
