package tecdesoftware.markep_app.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import tecdesoftware.markep_app.persistence.entity.Cliente;
import tecdesoftware.markep_app.persistence.entity.Compra;

import java.util.List;
import java.util.Optional;

public interface  CompraCrudRepository extends CrudRepository<Compra,Integer> {
    Optional<List<Compra>> findByIdCliente(Integer idCliente);
}
