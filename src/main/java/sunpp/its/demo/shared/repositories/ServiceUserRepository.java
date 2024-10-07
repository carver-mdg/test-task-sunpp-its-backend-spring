package sunpp.its.demo.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sunpp.its.demo.shared.entities.service.ServiceEntity;
import sunpp.its.demo.shared.entities.service.ServiceUserEntity;

@Repository
public interface ServiceUserRepository extends CrudRepository<ServiceUserEntity, Integer> {
  void deleteByService(ServiceEntity service);
}
