package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IProductService;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.models.Tag;
import edu.fontys.horecarobot.databaselibrary.repositories.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        Product product = new Product();
        List<Tag> tagList = new ArrayList<Tag>();
        Tag tag = new Tag();
        tag.setName("Alcohol");
        tagList.add(tag);
        product.setTags(tagList);

        Example<Product> example = Example.of(product);
        return productRepository.findAll(example);

//        return productRepository.findAll();
    }

    public List<Product> getAllProducts(List<Integer> tagIds) {
        Product product = new Product();
        List<Tag> tagList = new ArrayList<Tag>();
        tagList.add(new Tag(null, "DONKERS"));
        product.setTags(tagList);

        Example<Product> example = Example.of(product);
        return productRepository.findAll(example);
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id).get();
    }

    public Product getProductByName(String name){return productRepository.getProductByName(name);}

}


