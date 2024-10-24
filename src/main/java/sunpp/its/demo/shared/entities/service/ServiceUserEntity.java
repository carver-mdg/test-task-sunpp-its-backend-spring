package sunpp.its.demo.shared.entities.service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import sunpp.its.demo.shared.entities.UserEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"user", "service"})
@Entity
@Table(
        name = "ServiceUser",
        uniqueConstraints = @UniqueConstraint(columnNames={"userId", "serviceId"})
)
@Comment("Пользователь привязаный к сервису")
public class ServiceUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serviceUserId")
    @Comment("Первичный ключ")
    private Integer serviceUserId;

    @ManyToOne
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Comment("Пользователь сервиса")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "roleId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Comment("Роль пользователя в сервисе")
    private TypeUserRoleInServiceEntity userRole;

    @ManyToOne
    @JoinColumn(name = "serviceId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Comment("Сервис системы")
    private ServiceEntity service;
}
