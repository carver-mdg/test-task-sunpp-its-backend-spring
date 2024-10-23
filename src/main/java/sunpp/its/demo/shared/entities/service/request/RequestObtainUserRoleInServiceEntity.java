package sunpp.its.demo.shared.entities.service.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import sunpp.its.demo.shared.entities.UserEntity;
import sunpp.its.demo.shared.entities.service.ServiceEntity;
import sunpp.its.demo.shared.entities.service.TypeUserRoleInServiceEntity;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "RequestObtainUserRoleInService")
public class RequestObtainUserRoleInServiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "requestId")
  private Integer requestId;

  @ManyToOne
  @JoinColumn(name = "userCustomerId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private UserEntity userCustomer;

  @ManyToOne
  @JoinColumn(name = "serviceId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ServiceEntity service;

  @ManyToOne
  @JoinColumn(name = "requestedRoleId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private TypeUserRoleInServiceEntity requestedRole;

  @Column(name = "isActive")
  private Boolean isActive;


  @Column(name = "createdOn")
  @CreationTimestamp
  private LocalDateTime createdOn;

  @Column(name = "updatedOn")
  @UpdateTimestamp
  private LocalDateTime updatedOn;
}
