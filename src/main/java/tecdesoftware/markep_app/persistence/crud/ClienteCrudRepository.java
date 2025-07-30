package tecdesoftware.markep_app.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import tecdesoftware.markep_app.persistence.entity.Cliente;

import java.util.Optional;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Long> {
    Optional<Cliente> findByCorreoElectronico(String correoElectronico);
}
