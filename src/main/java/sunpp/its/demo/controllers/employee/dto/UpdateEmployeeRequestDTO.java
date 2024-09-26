package sunpp.its.demo.controllers.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequestDTO {
    private Integer EmployeeID;
    private String FullName;
    private Integer StaffUnitID;
}
