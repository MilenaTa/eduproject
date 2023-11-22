package rs.edu.shipping.delivery.repository;

import java.util.Date;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.edu.shipping.delivery.entity.OrdersEntity;
import rs.edu.shipping.delivery.entity.ProviderEntity;

@Repository
public interface OrdersEntityRepository extends JpaRepository<OrdersEntity, Integer>,
    JpaSpecificationExecutor<OrdersEntity> {

  @Modifying
  @Query(value = "UPDATE orders SET active = false WHERE delivery_date = :date", nativeQuery = true)
  void deactivateExpiredOrders(@Param("date") Date date);

}
