package tecdesoftware.markep_app.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // <-- ¡IMPORTANTE!
import tecdesoftware.markep_app.domain.Purchase;
import tecdesoftware.markep_app.domain.repository.PurchaseRepository;

import java.util.List;
import java.util.Optional;

@Service // <-- Esto permite que Spring la registre como un componente
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll() {
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(Integer clientId) {
        return purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
