package sunpp.its.demo.controllers.staffunit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.StaffUnitEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffUnitResponseDTO {
  private Integer staffUnitId;
  private String staffUnitName;
  private Integer departmentId;


  /**
   * Convert entity to response DTO to clients
   *
   * @param staffUnitEntity Entity of staff unit
   * @return DTO of staff unit
   */
  public static StaffUnitResponseDTO convertEntityToDTO(StaffUnitEntity staffUnitEntity) {
    StaffUnitResponseDTO response = new StaffUnitResponseDTO();
    response.setStaffUnitId(staffUnitEntity.getStaffUnitId());
    response.setStaffUnitName(staffUnitEntity.getStaffUnitName());
    response.setDepartmentId(staffUnitEntity.getDepartment().getDepartmentId());
    return response;
  }
}