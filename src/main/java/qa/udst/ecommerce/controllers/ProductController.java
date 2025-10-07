package qa.udst.ecommerce.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qa.udst.ecommerce.model.BaseProduct;
import qa.udst.ecommerce.model.DigitalProduct;
import qa.udst.ecommerce.model.PhysicalProduct;
import qa.udst.ecommerce.service.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all products")
    public List<BaseProduct> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one product by id")
    public BaseProduct one(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/{id}/tracking")
    @Operation(summary = "Get tracking info (physical only)")
    public String tracking(@PathVariable Long id) {
        return service.trackingInfo(id);
    }

    @PostMapping("/digital")
    @Operation(summary = "Create a digital product")
    @ApiResponse(responseCode = "201", description = "Digital product created")
    public ResponseEntity<BaseProduct> createDigital(@Valid @RequestBody DigitalProduct p) {
        BaseProduct saved = service.addDigital(p);
        return ResponseEntity.created(URI.create("/api/products/" + saved.getId())).body(saved);
    }

    @PostMapping("/physical")
    @Operation(summary = "Create a physical product")
    @ApiResponse(responseCode = "201", description = "Physical product created")
    public ResponseEntity<BaseProduct> createPhysical(@Valid @RequestBody PhysicalProduct p) {
        BaseProduct saved = service.addPhysical(p);
        return ResponseEntity.created(URI.create("/api/products/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}/digital")
    @Operation(summary = "Update a digital product")
    public BaseProduct updateDigital(@PathVariable Long id, @Valid @RequestBody DigitalProduct p) {
        return service.updateDigital(id, p);
    }

    @PutMapping("/{id}/physical")
    @Operation(summary = "Update a physical product")
    public BaseProduct updatePhysical(@PathVariable Long id, @Valid @RequestBody PhysicalProduct p) {
        return service.updatePhysical(id, p);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
