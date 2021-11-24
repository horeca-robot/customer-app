package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.IProductService;
import com.customerapp.CustomerAppApi.models.ProductDto;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product/")
public class ProductController {

    private final ModelMapper modelMapper;
    private final IProductService productService;

    @Autowired
    public ProductController(ModelMapper modelMapper, IProductService productService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
    }


    @CrossOrigin(origins = "*")
    @GetMapping("all")
    public List<ProductDto> getProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("byid")
    public ProductDto getProductById(@RequestParam String id) {
        return convertToDTO(productService.getProductById(UUID.fromString(id)));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("byname")
    public ProductDto getProductByName(@RequestBody String name) {
        return convertToDTO(productService.getProductByName(name));
    }




    //mappers
    private Product convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
    private ProductDto convertToDTO(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
