package service;

import com.springboot.jasypt.model.Product;
import com.springboot.jasypt.repository.ProductRepository;
import com.springboot.jasypt.service.LogService;
import com.springboot.jasypt.service.ProductServiceUsingCons;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceConsTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private LogService logService;

    private ProductServiceUsingCons productService;

    @Before
    public void setUp() {
        productService = new ProductServiceUsingCons(productRepository, logService);
    }

    @Test
    public void test() {
        when(productRepository.findAll()).thenReturn(null);
        doNothing().when(logService).addLog(any());
        List<Product> products = productService.getProducts();

        Assert.assertNull(products);
    }

//    @Test
//    public void test2() {
//        LogService logService1 = new LogService();
//        ProductServiceUsingCons productServiceUsingCons =
//                new ProductServiceUsingCons(logService1);
////        doNothing().when(logService).addLog(any());
//
//        List<Product> products = productServiceUsingCons.getProducts();
//
//        Assert.assertNull(products);
//    }
}
