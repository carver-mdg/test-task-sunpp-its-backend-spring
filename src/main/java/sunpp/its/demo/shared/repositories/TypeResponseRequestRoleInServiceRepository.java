package sunpp.its.demo.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunpp.its.demo.shared.entities.service.request.TypeResponseRequestRoleInServiceEntity;

@Repository
public interface TypeResponseRequestRoleInServiceRepository extends JpaRepository<TypeResponseRequestRoleInServiceEntity, Integer> {
    Boolean existsByTypeResponseName(String responseName);
    TypeResponseRequestRoleInServiceEntity findByTypeResponseName(String responseName);
}
