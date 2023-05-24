package com.example.demo.controller;

import com.example.demo.entity.Image_model;
import com.example.demo.entity.Product;
import com.example.demo.servicies.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class Product_controller {

    private ProductService productService;

    public Product_controller(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = {"/product/add"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> add_new_product(@RequestPart("product") Product product,
                                                   @RequestPart("image_file") MultipartFile[] files) {
        try {
            Set<Image_model> images1 = upload_image(files);
            product.setProduct_images(images1);
            Product savedProduct = productService.addNewProduct(product);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    public Set<Image_model> upload_image(MultipartFile[]multipleFile) throws IOException {
        Set<Image_model>image_models=new HashSet<>();

        for (MultipartFile file : multipleFile) {
            Image_model image = new Image_model(
                    file.getOriginalFilename(),
            file.getContentType(),
            file.getBytes()
            );
            image_models.add(image);
        }
           return image_models;
    }

    @GetMapping("/get/products/by/page")
    public ResponseEntity<List<Product>> getProductByPage(@RequestParam(defaultValue = "0") int pageNumber,
                                                          @RequestParam(defaultValue = "0") String search_key) {
        List<Product> products = productService.getProductByPage(pageNumber, search_key);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header-name", "header-value");

        return ResponseEntity.ok().headers(httpHeaders).body(products);
    }

  @GetMapping("/get/product/by/id")
    public ResponseEntity<Product> get_product_by_id(@PathVariable("product_id") long product_id) {
        Product product = productService.product_by_id(product_id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header-name", "header-value");

        return ResponseEntity.ok().headers(httpHeaders).body(product);
    }


    @DeleteMapping("/delete/product/id")
    public ResponseEntity Delete_product(@PathVariable("product_id")long id) {
        productService.delete_product_by_id(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("delete-product", "delete-product-value");
        return ResponseEntity.ok().headers(httpHeaders).build();
    }



    @PreAuthorize("hasRole('User1')")
    @GetMapping({"/get/product/details/single_product_checkout/product/id"})
    public ResponseEntity<List<Product>> getProductDetails(@PathVariable (name="isSingleProductCheckout")boolean single_product_checkout,
                                                          @PathVariable (name="product_id" ) long product_id ) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header-name", "header-value");
        return new ResponseEntity<>(productService.get_product_details(single_product_checkout, product_id),httpHeaders, 200);
    }

}
