package service;

import com.springboot.jasypt.model.Product;
import com.springboot.jasypt.repository.ProductRepository;
import com.springboot.jasypt.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void test() {
        when(productRepository.findAll())
                .thenReturn(Arrays.asList(Product.builder().name("hai").build()));
        List<Product> products = productService.getProducts();

        Assert.assertNull(products);
    }
}
