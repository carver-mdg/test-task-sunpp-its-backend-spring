package sunpp.its.demo.controllers.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.DepartmentEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepartmentRequestDTO {
  private String departmentName;


  /**
   * @return
   */
  public DepartmentEntity convertDTOToEntity() {
    DepartmentEntity entity = new DepartmentEntity();
    entity.setDepartmentName(this.getDepartmentName());
    return entity;
  }
}
