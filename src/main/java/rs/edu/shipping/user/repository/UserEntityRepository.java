package rs.edu.shipping.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import rs.edu.shipping.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer>,
    JpaSpecificationExecutor<UserEntity> {

  Optional<UserEntity> findByUsername(String username);

}
