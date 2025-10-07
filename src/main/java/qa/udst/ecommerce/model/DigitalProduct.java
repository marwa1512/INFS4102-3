package qa.udst.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("DIGITAL")
public class DigitalProduct extends BaseProduct {

    @NotBlank
    private String downloadUrl;

    public DigitalProduct() {
    }

    public DigitalProduct(String name, double price, ProductCategory category, String downloadUrl) {
        super(name, price, category);
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
