package sunpp.its.demo.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunpp.its.demo.shared.entities.service.TypeUserRoleInServiceEntity;

@Repository
public interface UserRoleInServiceRepository extends JpaRepository<TypeUserRoleInServiceEntity, Integer> {
    Boolean existsByRoleName(String roleName);
    TypeUserRoleInServiceEntity findByRoleName(String roleName);
}
