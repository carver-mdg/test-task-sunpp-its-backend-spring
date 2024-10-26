package sunpp.its.demo.shared.services;

import org.springframework.stereotype.Service;
import sunpp.its.demo.shared.entities.DepartmentEntity;
import sunpp.its.demo.shared.repositories.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
  private final DepartmentRepository departmentRepository;


  /**
   * @param departmentRepository
   */
  public DepartmentService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }


  /**
   * @return
   */
  public List<DepartmentEntity> getDepartmentsList() {
    return this.departmentRepository.findAll();
  }


  /**
   * @param department
   * @return
   */
  public DepartmentEntity addDepartment(DepartmentEntity department) {
    return this.departmentRepository.save(department);
  }


  /**
   * @param department
   * @return
   */
  public DepartmentEntity updateDepartment(DepartmentEntity department) {
    return this.departmentRepository.save(department);
  }


  /**
   * @param departmentId
   * @return
   */
  public DepartmentEntity deleteDepartment(Integer departmentId) {
    Optional<DepartmentEntity> dep = this.departmentRepository.findById(departmentId);
    this.departmentRepository.delete(dep.orElseThrow());
    return dep.get();
  }
}
