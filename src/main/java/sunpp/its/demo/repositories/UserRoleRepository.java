package sunpp.its.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunpp.its.demo.entities.DepartmentEntity;
import sunpp.its.demo.entities.UserRoleEntity;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
}
