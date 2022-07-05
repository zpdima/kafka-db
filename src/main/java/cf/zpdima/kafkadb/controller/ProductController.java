package cf.zpdima.kafkadb.controller;

import cf.zpdima.kafkadb.config.KafkaProducerConfig;
import cf.zpdima.kafkadb.model.Product;
import cf.zpdima.kafkadb.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class ProductController {


    private ProductRepository productRepository;

    @Autowired
    private KafkaProducerConfig.MessageProducer messageProducer;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @PostConstruct
    private void init(){
        log.info(" --- init product");
        Product product = Product.builder().name("product1").description("desc1").remain(10).price(100f).build();
        productRepository.save(product);
        product = Product.builder().name("product2").description("desc1").remain(15).price(150f).build();
        productRepository.save(product);
        product = Product.builder().name("product3").description("desc1").remain(20).price(200f).build();
        productRepository.save(product);

    }

    @GetMapping("/hello")
    Map<Long, String> hello(@RequestParam(name = "name", defaultValue = "world") String name) {
        log.info(" --- request name {}", name);
        Map<Long, String> map = new HashMap<>();
        map.put(1l, "Hello " + name);
        return map;
    }

    @GetMapping("/products")
    public List<Product> listAll() {
        log.info(" --- request all product");
        List<Product> list = productRepository.findAll();
        return list;
    }

    @GetMapping("/product")
    public Optional<Product> getPrductId(@RequestParam(name = "id") Long id){
        Optional<Product> product = productRepository.findById(id);
        return product;
    }

    @PostMapping("/products")
    Product newEmployee(@RequestBody Product product) {
        product = productRepository.save(product);
        messageProducer.sendProductMessage(product);
        return product;
    }
}
