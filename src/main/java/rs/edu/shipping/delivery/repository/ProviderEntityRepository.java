package rs.edu.shipping.delivery.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import rs.edu.shipping.delivery.constants.ProviderName;
import rs.edu.shipping.delivery.entity.ProviderEntity;

@Repository
public interface ProviderEntityRepository extends JpaRepository<ProviderEntity, Integer>,
    JpaSpecificationExecutor<ProviderEntity> {

  Optional<ProviderEntity> getProviderEntityByName(ProviderName name);

}
