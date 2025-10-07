package qa.udst.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import qa.udst.ecommerce.model.*;
import qa.udst.ecommerce.repositories.DigitalProductRepository;
import qa.udst.ecommerce.repositories.PhysicalProductRepository;

@Component
public class EcommerceRunner implements CommandLineRunner {

    private final DigitalProductRepository digitalRepo;
    private final PhysicalProductRepository physicalRepo;

    public EcommerceRunner(DigitalProductRepository digitalRepo,
            PhysicalProductRepository physicalRepo) {
        this.digitalRepo = digitalRepo;
        this.physicalRepo = physicalRepo;
    }

    @Override
    public void run(String... args) {
        if (digitalRepo.count() == 0 && physicalRepo.count() == 0) {
            DigitalProduct ebook = new DigitalProduct(
                    "E-Book Clean Architecture", 19.90, ProductCategory.BOOKS,
                    "https://example.com/clean-arch.pdf");
            PhysicalProduct keyboard = new PhysicalProduct(
                    "Mechanical Keyboard", 129.00, ProductCategory.ELECTRONICS,
                    0.85, "44x13x3cm");
            keyboard.setTrackingNumber("TRACK-2025-0001");

            digitalRepo.save(ebook);
            physicalRepo.save(keyboard);
        }
    }
}
