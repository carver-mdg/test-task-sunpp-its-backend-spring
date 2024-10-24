package sunpp.its.demo.shared.entities.service.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import sunpp.its.demo.shared.entities.UserEntity;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ResponseRequestRoleInServiceEntity")
@Comment("Решение по заявке на повышение роли пользователя в сервисе")
public class ResponseRequestRoleInServiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "responseId")
  @Comment("Первичный ключ")
  private Integer responseId;

  @ManyToOne
  @JoinColumn(name = "requestId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @Comment("Заявка на повышение роли пользователя в сервисе")
  private RequestObtainUserRoleInServiceEntity request;

  @ManyToOne
  @JoinColumn(name = "userId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @Comment("Пользователь принимавший решение")
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "typeResponseId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @Comment("Ответ по заявке, один из предложеных типов")
  private TypeResponseRequestRoleInServiceEntity typeResponse;
}


