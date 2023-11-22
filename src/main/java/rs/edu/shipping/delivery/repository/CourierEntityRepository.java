package rs.edu.shipping.delivery.repository;

import java.util.Date;
import java.util.List;
import jakarta.persistence.QueryHint;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.edu.shipping.delivery.entity.CourierEntity;
import rs.edu.shipping.delivery.entity.ProviderEntity;
import rs.edu.shipping.user.entity.UserEntity;

@Repository
public interface CourierEntityRepository extends JpaRepository<CourierEntity, Integer>,
    JpaSpecificationExecutor<CourierEntity> {

  @QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE, value = "100"))
  @Query(value = "SELECT * FROM courier WHERE id NOT IN (SELECT courier FROM orders WHERE active = 1) AND provider = :provider", nativeQuery = true)
  List<CourierEntity> findAvailableByProvider(@Param("provider") String provider);

  @QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE, value = "100"))
  @Query(value = "SELECT coalesce(MIN(delivery_date), sysdate()) FROM courier c, orders o\n"
      + "WHERE o.courier = c.id\n"
      + "and c.provider = :provider ", nativeQuery = true)
  Date findEarliestDeliveryDateByProvider(@Param("provider") String provider);

  List<CourierEntity> getCourierEntitiesByProvider(ProviderEntity provider);

  CourierEntity getCourierEntityByUser(UserEntity user);
}
