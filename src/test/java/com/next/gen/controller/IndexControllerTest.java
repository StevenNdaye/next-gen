package com.next.gen.controller;

import com.next.gen.model.Product;
import com.next.gen.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    @Mock
    private ProductService productService;
    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        indexController = new IndexController(productService);
    }

    @Test
    public void testShouldDisplayAndIndexPageWithProducts() throws Exception {
        List<Product> products = new ArrayList<Product>() {{
            add(new Product("Name1", "Category1", "Description1", new BigDecimal(100), "Condition1", "Status1", 12, "Manufacturer1"));
            add(new Product("Name2", "Category2", "Description2", new BigDecimal(200), "Condition2", "Status2", 10, "Manufacturer2"));
        }};
        when(productService.getAllProducts()).thenReturn(products);

        ModelMap model = new ModelMap();

        String viewName = indexController.index(model);

        assertThat(viewName, is("index"));
        assertThat(model.get("products"), is(products));
        verify(productService, times(1)).getAllProducts();
    }
}