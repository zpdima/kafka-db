package cf.zpdima.kafkadb.repository;

import cf.zpdima.kafkadb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
