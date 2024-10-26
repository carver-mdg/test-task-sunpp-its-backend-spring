package sunpp.its.demo.controllers.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequestDTO {
    private Integer employeeId;
    private String fullName;
    private Integer staffUnitId;
}
