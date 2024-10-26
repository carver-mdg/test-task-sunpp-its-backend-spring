package sunpp.its.demo.controllers.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceWaitingAccessResponseDTO {
  private Integer serviceId;
  private String serviceName;
  private Integer userId;
  private String userName;
  private String userRoleName;
}
