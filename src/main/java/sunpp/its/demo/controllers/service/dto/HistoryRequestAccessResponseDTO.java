package sunpp.its.demo.controllers.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryRequestAccessResponseDTO {
  private String serviceName;
  private String requestedRole;
  private String userNameCustomer;
  private String userNameGivesAccess;
  private String statusAccess;
  private LocalDateTime dateCreated;
}
