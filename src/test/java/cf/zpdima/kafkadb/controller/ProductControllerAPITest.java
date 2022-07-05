package cf.zpdima.kafkadb.controller;

import cf.zpdima.kafkadb.config.KafkaProducerConfig;
import cf.zpdima.kafkadb.model.Product;
import cf.zpdima.kafkadb.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private KafkaProducerConfig.MessageProducer messageProducer;

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/api/hello?name=Peter"))
                .andExpect(content().string("{\"1\":\"Hello Peter\"}"))
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void shouldReturnFullName() throws Exception {

        Product product = Product.builder().id(1l).name("product1").description("desc1").remain(10).price(100f).build();
        given(productRepository.findById(1l)).willReturn(Optional.of(product));

        mockMvc.perform(get("/api/product?id=1"))
                .andExpect(content().string("{\"id\":1,\"name\":\"product1\",\"description\":\"desc1\",\"remain\":10,\"price\":100.0}"))
                .andExpect(status().is2xxSuccessful());
    }


}
