package sunpp.its.demo.controllers.staffunit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStaffUnitRequestDTO {
  private String staffUnitName;
  private Integer departmentId;
}
