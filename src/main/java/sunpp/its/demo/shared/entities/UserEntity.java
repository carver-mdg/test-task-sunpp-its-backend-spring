package sunpp.its.demo.shared.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import sunpp.its.demo.shared.entities.auth.TypeUserRoleInSystemEntity;
import sunpp.its.demo.shared.entities.service.ServiceUserEntity;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"usersInService"})
@Entity
@Table(name = "UserOfSystem")
@Comment("Пользователь системы")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    @Comment("Первичный ключ")
    private Integer userId;

    @Column(name = "userName", unique = true)
    @Comment("Имя пользователя")
    private String userName;

    @Column(name = "password")
    @Comment("Пароль пользователя")
    private String password;

    @OneToOne
    @JoinColumn(name = "employeeId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Comment("Работник к которому привязан пользователь")
    private EmployeeEntity employee;

    @OneToMany(mappedBy = "user")
    @Comment("Внешний ключ - пользователь в сервисе")
    private List<ServiceUserEntity> usersInService;
}
