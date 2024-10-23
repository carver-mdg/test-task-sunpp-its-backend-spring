package sunpp.its.demo.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunpp.its.demo.shared.entities.service.request.RequestObtainUserRoleInServiceEntity;
import sunpp.its.demo.shared.entities.service.request.ResponseRequestRoleInServiceEntity;

import java.util.List;

@Repository
public interface ResponseRequestRoleInServiceRepository extends JpaRepository <ResponseRequestRoleInServiceEntity, Integer> {
  List<ResponseRequestRoleInServiceEntity> findByRequest(RequestObtainUserRoleInServiceEntity requestObtainUserRoleInServiceEntity);
}
