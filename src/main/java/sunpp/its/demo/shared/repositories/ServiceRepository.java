package sunpp.its.demo.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sunpp.its.demo.shared.entities.UserEntity;
import sunpp.its.demo.shared.entities.service.ServiceEntity;
import sunpp.its.demo.shared.entities.service.TypeUserRoleInServiceEntity;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
  @Query(value = """
      SELECT
      service.*
      FROM service
      LEFT OUTER JOIN service_user
      ON service_user.service_id = service.service_id
      LEFT OUTER JOIN type_user_role_in_service
      ON type_user_role_in_service.role_id = service_user.role_id
      LEFT OUTER JOIN user_of_system
      ON service_user.user_id = user_of_system.user_id
      WHERE service_user.role_id = :role_id AND user_of_system.user_id = :user_id;
      """,
      nativeQuery = true)
  List<ServiceEntity> findByUsersAndUserRole(@Param("user_id") Integer user_id, @Param("role_id") Integer role_id);
}
