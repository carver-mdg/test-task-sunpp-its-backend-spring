package sunpp.its.demo.controllers.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.EmployeeEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {
    private Integer EmployeeID;
    private String FullName;
    private Integer StaffUnitID;

    /**
     * Convert entity to response DTO to clients
     *
     * @param employeeEntity Entity of employee
     * @return DTO of employee
     */
    public static EmployeeResponseDTO convertEntityToDTO (EmployeeEntity employeeEntity) {
        EmployeeResponseDTO response = new EmployeeResponseDTO();
        response.setEmployeeID(employeeEntity.getEmployeeID());
        response.setFullName(employeeEntity.getFullName());
        response.setStaffUnitID(employeeEntity.getStaffUnit().getStaffUnitID());
        return response;
    }
}