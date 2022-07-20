package cf.zpdima.kafkadb.mappers;

import cf.zpdima.kafkadb.dto.ProductDto;
import cf.zpdima.kafkadb.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductMapper {

    private ModelMapper mapper;

    @Autowired
    public ProductMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

//    @Override
    public Product toEntity(ProductDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Product.class);
    }

//    @Override
    public ProductDto toDto(Product entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ProductDto.class);
    }
}
