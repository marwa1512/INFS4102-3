package qa.udst.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import qa.udst.ecommerce.model.BaseProduct;

import java.util.List;

public interface ProductRepository extends JpaRepository<BaseProduct, Long> {
    List<BaseProduct> findByNameContainingIgnoreCase(String q);

    List<BaseProduct> findByPriceBetween(double min, double max);
}
