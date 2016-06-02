package com.next.gen.service;

import com.next.gen.model.Product;
import com.next.gen.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService = new ProductService(productRepository);
    }

    @Test
    public void shouldGetAllProducts() throws Exception {
        List<Product> products = new ArrayList<Product>() {{
            add(new Product("Name1", "Category1", "Description1", new BigDecimal(100), "Condition1", "Status1", 12, "Manufacturer1"));
            add(new Product("Name2", "Category2", "Description2", new BigDecimal(200), "Condition2", "Status2", 10, "Manufacturer2"));
        }};

        when(productRepository.findAll()).thenReturn(products);

        List<Product> productList = productService.getAllProducts();

        assertEquals(products, productList);

        verify(productRepository, times(1)).findAll();
    }
}