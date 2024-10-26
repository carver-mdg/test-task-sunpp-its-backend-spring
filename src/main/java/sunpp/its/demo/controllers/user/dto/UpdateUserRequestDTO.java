package sunpp.its.demo.controllers.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDTO {
  private Integer userId;
  private String userName;
  private String password;
  private Integer employeeId;
}
