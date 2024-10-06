package sunpp.its.demo.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunpp.its.demo.shared.entities.service.UserRoleInServiceEntity;

@Repository
public interface UserRoleInServiceRepository extends JpaRepository<UserRoleInServiceEntity, Integer> {
    Boolean existsByRoleName(String roleName);
    UserRoleInServiceEntity findByRoleName(String roleName);
}
