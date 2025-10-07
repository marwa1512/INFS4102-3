package qa.udst.ecommerce.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("PHYSICAL")
public class PhysicalProduct extends BaseProduct implements Shippable, Trackable {

    @Min(0)
    private double weightKg;

    @NotBlank
    private String dimensions;

    private String trackingNumber; // optional

    // Optional embedded inventory (safe to keep even if unused in endpoints)
    @Embedded
    private ProductInventory inventory;

    public PhysicalProduct() {
    }

    public PhysicalProduct(String name, double price, ProductCategory category,
            double weightKg, String dimensions) {
        super(name, price, category);
        this.weightKg = weightKg;
        this.dimensions = dimensions;
    }

    @Override
    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    @Override
    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public String getTrackingNumber() {
        return trackingNumber;
    }

    @Override
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public ProductInventory getInventory() {
        return inventory;
    }

    public void setInventory(ProductInventory inventory) {
        this.inventory = inventory;
    }
}
