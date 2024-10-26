package sunpp.its.demo.controllers.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.EmployeeEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {
  private Integer employeeId;
  private String fullName;
  private Integer staffUnitId;


  /**
   * Convert entity to response DTO to clients
   *
   * @param employeeEntity Entity of employee
   * @return DTO of employee
   */
  public static EmployeeResponseDTO convertEntityToDTO(EmployeeEntity employeeEntity) {
    EmployeeResponseDTO response = new EmployeeResponseDTO();
    response.setEmployeeId(employeeEntity.getEmployeeId());
    response.setFullName(employeeEntity.getFullName());
    response.setStaffUnitId(employeeEntity.getStaffUnit().getStaffUnitId());
    return response;
  }
}