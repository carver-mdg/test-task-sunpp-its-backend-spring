package sunpp.its.demo.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunpp.its.demo.shared.entities.UserEntity;
import sunpp.its.demo.shared.entities.service.ServiceEntity;
import sunpp.its.demo.shared.entities.service.request.RequestObtainUserRoleInServiceEntity;

import java.util.List;

@Repository
public interface RequestObtainUserRoleInServiceRepository extends JpaRepository <RequestObtainUserRoleInServiceEntity, Integer> {
  List<RequestObtainUserRoleInServiceEntity> findByServiceAndUserCustomer(ServiceEntity service, UserEntity userCustomer);
  List<RequestObtainUserRoleInServiceEntity> findByServiceAndUserCustomerAndIsActive(ServiceEntity service, UserEntity userCustomer, Boolean isActive);
}
