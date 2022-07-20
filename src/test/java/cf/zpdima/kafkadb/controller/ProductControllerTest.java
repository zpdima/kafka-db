package cf.zpdima.kafkadb.controller;

import cf.zpdima.kafkadb.model.Product;
import cf.zpdima.kafkadb.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class ProductControllerTest {

    private ProductController subject;

    @Mock
    private ProductRepository productRepository;


    @BeforeEach
    void setUp() {
        System.out.println(" --- 1 ");
        MockitoAnnotations.openMocks(this);
        System.out.println(" --- 2 ");
        subject = new ProductController(productRepository);
        System.out.println(" --- 3 ");
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        Map<Long, String> m = subject.hello("Peter");
        System.out.println(" --- " + m);
        System.out.println(" ---  " + m.get(1l));
        assertThat(m.get(1l), is("Hello Peter"));
    }


    @Test
    void getProducts() {
        Product product = Product.builder().id(1l).name("product1").description("desc1").remain(10).price(100f).build();
        given(productRepository.findById(1l)).willReturn(Optional.of(product));

        Optional<Product> optionalProduct = Optional.of(subject.getPrductId(1l));

//        Assumptions.assumeFalse(optionalProduct.isPresent());
        assertTrue(optionalProduct.isPresent());
        assertThat(optionalProduct.get().getName(), is("product1"));
    }

    @Test
    public void shouldTellIfPersonIsUnknown() throws Exception {

        given(productRepository.findById(org.mockito.ArgumentMatchers.anyLong())).willReturn(Optional.empty());
        Optional<Product> optionalProduct = Optional.of(subject.getPrductId(org.mockito.ArgumentMatchers.anyLong()));
        assertTrue(optionalProduct.isEmpty());
    }

    @Test
    void newEmployee() {
    }
}
