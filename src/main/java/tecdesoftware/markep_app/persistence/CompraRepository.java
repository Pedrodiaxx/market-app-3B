package tecdesoftware.markep_app.persistence;

import tecdesoftware.markep_app.domain.Purchase;
import tecdesoftware.markep_app.domain.repository.PurchaseRepository;
import tecdesoftware.markep_app.persistence.crud.CompraCrudRepository;
import tecdesoftware.markep_app.persistence.entity.Compra;
import tecdesoftware.markep_app.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());

    }

    @Override
    public Optional<List<Purchase>> getByClient(String clienteId) {
        return compraCrudRepository.findByIdCliente(clienteId)
                .map(compra -> mapper.toPurchases(compra));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        mapper.toPurchase(compraCrudRepository.save(compra));
        return null;
    }
}