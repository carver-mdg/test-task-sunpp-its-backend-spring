package sunpp.its.demo.controllers.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.DepartmentEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDepartmentRequestDTO {
  private Integer departmentId;
  private String departmentName;


  /**
   * @return
   */
  public DepartmentEntity convertDTOToEntity() {
    DepartmentEntity entity = new DepartmentEntity();
    entity.setDepartmentId(this.getDepartmentId());
    entity.setDepartmentName(this.getDepartmentName());
    return entity;
  }
}
