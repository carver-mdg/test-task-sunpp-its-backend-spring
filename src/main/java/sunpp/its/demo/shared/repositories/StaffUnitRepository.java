package sunpp.its.demo.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunpp.its.demo.shared.entities.StaffUnitEntity;

@Repository
public interface StaffUnitRepository  extends JpaRepository<StaffUnitEntity, Integer> {
}
