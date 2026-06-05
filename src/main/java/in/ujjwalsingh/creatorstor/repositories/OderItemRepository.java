package in.ujjwalsingh.creatorstor.repositories;

import in.ujjwalsingh.creatorstor.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OderItemRepository extends JpaRepository<OrderItem,Long> {
}
