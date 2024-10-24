package sunpp.its.demo.shared.entities.service.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TypeResponseRequestRoleInService")
@Comment("Типы ответов пользователя на запрос о повышении роли пользователя в сервисе")
public class TypeResponseRequestRoleInServiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "typeResponseId")
  @Comment("Первичный ключ")
  private Integer typeResponseId;

  @Column(name = "typeResponseName", unique = true)
  @Comment("Вариант ответа")
  private String typeResponseName;
}
