package cf.zpdima.kafkadb.mappers;

import cf.zpdima.kafkadb.dto.ProductDto;
import cf.zpdima.kafkadb.model.Product;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;



class ProductMapperTest {

//    @Autowired

    private ProductMapper productMapper = new ProductMapper(new ModelMapper());


//    @Test
    void toEntity() {
        ProductDto productDto = new ProductDto(1l, "name1", "desc1", 5, 100.0f);
        Product product = productMapper.toEntity(productDto);
        assertEquals(1, product.getId());
        assertEquals("name1", product.getName());
        assertEquals("desc1", product.getDescription());
        assertEquals(5, product.getRemain());
        assertEquals(100.0f, product.getPrice());
    }

//    @Test
    void toDto() {
    }
}
