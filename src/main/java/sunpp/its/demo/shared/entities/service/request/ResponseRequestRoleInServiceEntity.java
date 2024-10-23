package sunpp.its.demo.shared.entities.service.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import sunpp.its.demo.shared.entities.UserEntity;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ResponseRequestRoleInServiceEntity")
public class ResponseRequestRoleInServiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "responseId")
  private Integer responseId;

  @ManyToOne
  @JoinColumn(name = "requestId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private RequestObtainUserRoleInServiceEntity request;

  @ManyToOne
  @JoinColumn(name = "userId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "typeResponseId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private TypeResponseRequestRoleInServiceEntity typeResponse;
}


