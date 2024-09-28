package sunpp.its.demo.controllers.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDTO {
    private String Login;
    private String Password;
    private Integer EmployeeID;
}
