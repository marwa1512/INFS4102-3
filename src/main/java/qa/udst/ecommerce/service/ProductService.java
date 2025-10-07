package qa.udst.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qa.udst.ecommerce.model.BaseProduct;
import qa.udst.ecommerce.model.DigitalProduct;
import qa.udst.ecommerce.model.PhysicalProduct;
import qa.udst.ecommerce.model.ProductNotFoundException;
import qa.udst.ecommerce.repositories.DigitalProductRepository;
import qa.udst.ecommerce.repositories.PhysicalProductRepository;
import qa.udst.ecommerce.repositories.ProductRepository;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepo;
    private final DigitalProductRepository digitalRepo;
    private final PhysicalProductRepository physicalRepo;

    public ProductService(ProductRepository productRepo,
            DigitalProductRepository digitalRepo,
            PhysicalProductRepository physicalRepo) {
        this.productRepo = productRepo;
        this.digitalRepo = digitalRepo;
        this.physicalRepo = physicalRepo;
    }

    // Reads
    public List<BaseProduct> findAll() {
        return productRepo.findAll();
    }

    public BaseProduct findById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    // Create
    public BaseProduct addDigital(DigitalProduct p) {
        return digitalRepo.save(p);
    }

    public BaseProduct addPhysical(PhysicalProduct p) {
        return physicalRepo.save(p);
    }

    // Update Digital
    public BaseProduct updateDigital(Long id, DigitalProduct data) {
        DigitalProduct existing = (DigitalProduct) productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        existing.setName(data.getName());
        existing.setPrice(data.getPrice());
        existing.setCategory(data.getCategory());
        existing.setDownloadUrl(data.getDownloadUrl());
        return digitalRepo.save(existing);
    }

    // Update Physical
    public BaseProduct updatePhysical(Long id, PhysicalProduct data) {
        PhysicalProduct existing = (PhysicalProduct) productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        existing.setName(data.getName());
        existing.setPrice(data.getPrice());
        existing.setCategory(data.getCategory());
        existing.setWeightKg(data.getWeightKg());
        existing.setDimensions(data.getDimensions());
        existing.setTrackingNumber(data.getTrackingNumber());
        // inventory is optional; copy if provided
        if (data.getInventory() != null) {
            existing.setInventory(data.getInventory());
        }
        return physicalRepo.save(existing);
    }

    // Delete
    public void delete(Long id) {
        if (!productRepo.existsById(id))
            throw new ProductNotFoundException(id);
        productRepo.deleteById(id);
    }

    // Tracking info
    public String trackingInfo(Long id) {
        BaseProduct p = findById(id);
        if (p instanceof PhysicalProduct phys) {
            return phys.getTrackingNumber() == null ? "No tracking assigned" : phys.getTrackingNumber();
        }
        return "Digital product has no shipment tracking.";
    }
}
