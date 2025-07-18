package tecdesoftware.markep_app.persistence;

import tecdesoftware.markep_app.domain.Product;
import tecdesoftware.markep_app.domain.repository.ProductRepository;
import tecdesoftware.markep_app.persistence.crud.ProductoCrudRepository;
import tecdesoftware.markep_app.persistence.entity.Producto;
import tecdesoftware.markep_app.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        return mapper.toProducts((List<Producto>) productoCrudRepository.findAll());
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId)
                .map(mapper::toProduct);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        return Optional.of(mapper.toProducts(
                productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId)));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        Producto productoGuardado = productoCrudRepository.save(producto);
        return mapper.toProduct(productoGuardado);
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true)
                .map(mapper::toProducts);
    }

}