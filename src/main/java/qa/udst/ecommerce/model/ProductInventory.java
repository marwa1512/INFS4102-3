package qa.udst.ecommerce.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;

@Embeddable
public class ProductInventory {
    @Min(0)
    private int stock = 0; // available units
    private String location; // optional warehouse/shelf

    public int getStock() {
        return stock;
    }

    public String getLocation() {
        return location;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
