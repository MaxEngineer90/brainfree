package eu.brainfree.controller;

import eu.brainfree.domain.product.ProductResponseTO;
import eu.brainfree.domain.product.UpdateProductTO;
import eu.brainfree.logic.service.product.ProductControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * @author Max on 11.12.2021
 * @project brainfree
 * @package controller
 **/

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "${rest.base-path}/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductControllerService productService;


    @GetMapping
    public ResponseEntity<List<ProductResponseTO>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/page={pageSize}/size={size}")
    public ResponseEntity<Page<ProductResponseTO>> getPageniation(@PathVariable int pageSize,
                                                                  @PathVariable int size) {
        PageRequest pageRequest = PageRequest.of(pageSize, size, Sort.by("name"));

        return ResponseEntity.ok(productService.getByPageRequest(pageRequest));
    }

    @GetMapping("/{articleNumber}")
    public ResponseEntity<ProductResponseTO> getProduct(@PathVariable String articleNumber) {
        ProductResponseTO productResponseTO = productService.findProductByArticleNumber(articleNumber);
        return ResponseEntity.ok(productResponseTO);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('product_manager')")
    public ResponseEntity<ProductResponseTO> create(@RequestBody UpdateProductTO productDto) {
        ProductResponseTO productResponseTO = productService.createProduct(productDto);
        URI location = URI.create("/brainfree/products/");
        return ResponseEntity.created(location).body(productResponseTO);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('product_manager')")
    public ResponseEntity<ProductResponseTO> update(@RequestBody UpdateProductTO productDto) {
        ProductResponseTO productResponseTO = productService.updateProduct(productDto);
        return ResponseEntity.ok(productResponseTO);
    }
}
