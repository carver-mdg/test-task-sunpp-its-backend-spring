package sunpp.its.demo.shared.entities.service.request;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
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
@Comment("Запросы на повышение роли в сервисе")
public class RequestObtainUserRoleInServiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "requestId")
  @Comment("Первичный ключ")
  private Integer requestId;

  @ManyToOne
  @JoinColumn(name = "userCustomerId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @Comment("Пользователь сделавший запрос")
  private UserEntity userCustomer;

  @ManyToOne
  @JoinColumn(name = "serviceId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @Comment("Сервис в котором в котором запрошено повышение роли")
  private ServiceEntity service;

  @ManyToOne
  @JoinColumn(name = "requestedRoleId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @Comment("Тип роли пользователя в сервисе")
  private TypeUserRoleInServiceEntity requestedRole;

  @Column(name = "isActive")
  @Comment("Заявка актуальна или нет ?")
  private Boolean isActive;


  @Column(name = "createdOn")
  @CreationTimestamp
  @Comment("Время создания заявки")
  private LocalDateTime createdOn;

  @Column(name = "updatedOn")
  @UpdateTimestamp
  @Comment("Время обновления заявки")
  private LocalDateTime updatedOn;
}
