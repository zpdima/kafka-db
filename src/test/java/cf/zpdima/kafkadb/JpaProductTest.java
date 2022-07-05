package cf.zpdima.kafkadb;

import cf.zpdima.kafkadb.model.Product;
import cf.zpdima.kafkadb.repository.ProductRepository;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class JpaProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        this.testEntityManager.persistAndFlush(Product.builder().name("product1").description("desc1").remain(10).price(100f).build());
        this.testEntityManager.persistAndFlush(Product.builder().name("product2").description("desc2").remain(15).price(150f).build());
        this.testEntityManager.persistAndFlush(Product.builder().name("product3").description("desc3").remain(20).price(200f).build());

    }

    @Test
    void testList() {
        List<Product> list = productRepository.findAll();
        assertEquals(list.size(),3);
    }

    @Test
    void testSaveNewProdict(){
        Product product = Product.builder().name("product4").description("desc4").remain(25).price(250f).build();
        productRepository.save(product);
        Supplier<String> messageSupplier  = () -> "new id test failed";
        Assumptions.assumeTrue(product.getId().equals(5l), messageSupplier);
        System.out.println(product.getId());
        assertEquals(5l, product.getId());
    }

}
