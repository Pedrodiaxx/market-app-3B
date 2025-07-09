package tecdesoftware.markep_app.domain.repository;

import tecdesoftware.markep_app.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String client);
    Purchase save(Purchase purchase);
}
