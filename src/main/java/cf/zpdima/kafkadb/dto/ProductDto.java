package cf.zpdima.kafkadb.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

//    private String nane;
//  private Integer name;
    private String name;

    private String description;

    private Integer remain;

    private Float price;

}
