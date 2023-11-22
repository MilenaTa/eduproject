package rs.edu.shipping.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import rs.edu.shipping.user.entity.AuthoritiesEntity;
import rs.edu.shipping.user.entity.AuthoritiesId;

@Repository
public interface AuthoritiesEntityRepository extends JpaRepository<AuthoritiesEntity, AuthoritiesId>,
    JpaSpecificationExecutor<AuthoritiesEntity> {

}
