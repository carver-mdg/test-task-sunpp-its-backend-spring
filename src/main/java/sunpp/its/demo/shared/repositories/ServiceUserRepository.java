package sunpp.its.demo.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunpp.its.demo.shared.entities.UserEntity;
import sunpp.its.demo.shared.entities.service.ServiceEntity;
import sunpp.its.demo.shared.entities.service.ServiceUserEntity;

@Repository
public interface ServiceUserRepository extends JpaRepository<ServiceUserEntity, Integer> {
  void deleteByService(ServiceEntity service);
  ServiceUserEntity findByUserAndService(UserEntity user, ServiceEntity service);
}
