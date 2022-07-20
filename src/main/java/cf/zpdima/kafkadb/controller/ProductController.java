package cf.zpdima.kafkadb.controller;

import cf.zpdima.kafkadb.config.KafkaProducerConfig;
import cf.zpdima.kafkadb.dto.ProductDto;
import cf.zpdima.kafkadb.mappers.ProductMapper;
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
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class ProductController {


    private ProductRepository productRepository;

    @Autowired
    private KafkaProducerConfig.MessageProducer messageProducer;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @PostConstruct
    private void init() {
        log.info(" --- init product");
//        Product product = Product.builder().name("product1").description("desc1").remain(10).price(100f).build();
//        productRepository.save(product);
//        product = Product.builder().name("product2").description("desc1").remain(15).price(150f).build();
//        productRepository.save(product);
//        product = Product.builder().name("product3").description("desc1").remain(20).price(200f).build();
//        productRepository.save(product);


        Product product = Product.builder().name("1000000").description("desc1").remain(10).price(100f).build();
        productRepository.save(product);
        product = Product.builder().name("2000000").description("desc1").remain(15).price(150f).build();
        productRepository.save(product);
        product = Product.builder().name("3000000").description("desc1").remain(20).price(200f).build();
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
    public List<ProductDto> listAll() {
        log.info(" --- request all product");
        List<Product> list = productRepository.findAll();

        List<ProductDto> listDto = list.stream().map(p -> {
            System.out.println(productMapper.toDto(p));
            return productMapper.toDto(p);
        }).collect(Collectors.toList());

        return listDto;
    }

//    @GetMapping("/product")
//        public Optional<Product> getPrductId(@RequestParam(name = "id") Long id){
//        Optional<Product> product = productRepository.findById(id);
//        return product;
//    }

    @GetMapping("/product")
    public Product getPrductId(@RequestParam(name = "id") Long id) {
        Product product = productRepository.findById(id).get();
        return product;
    }


    @PostMapping("/products")
    Product newEmployee(@RequestBody Product product) {
        product = productRepository.save(product);
        messageProducer.sendProductMessage(product);
        return product;
    }
}
