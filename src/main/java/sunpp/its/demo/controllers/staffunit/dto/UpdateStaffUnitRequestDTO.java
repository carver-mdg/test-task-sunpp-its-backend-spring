package sunpp.its.demo.controllers.staffunit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStaffUnitRequestDTO {
    private Integer StaffUnitID;
    private String StaffUnitName;
    private Integer DepartmentID;
}
