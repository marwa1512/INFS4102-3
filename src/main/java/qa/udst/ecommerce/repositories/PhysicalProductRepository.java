package qa.udst.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import qa.udst.ecommerce.model.PhysicalProduct;

import java.util.List;

public interface PhysicalProductRepository extends JpaRepository<PhysicalProduct, Long> {
    List<PhysicalProduct> findByNameContainingIgnoreCase(String q);

    List<PhysicalProduct> findByPriceBetween(double min, double max);
}
