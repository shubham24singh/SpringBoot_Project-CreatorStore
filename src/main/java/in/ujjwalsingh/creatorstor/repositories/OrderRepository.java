package in.ujjwalsingh.creatorstor.repositories;

import in.ujjwalsingh.creatorstor.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
