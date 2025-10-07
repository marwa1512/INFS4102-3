package qa.udst.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import qa.udst.ecommerce.model.DigitalProduct;

import java.util.List;

public interface DigitalProductRepository extends JpaRepository<DigitalProduct, Long> {
    List<DigitalProduct> findByNameContainingIgnoreCase(String q);

    List<DigitalProduct> findByPriceBetween(double min, double max);
}
