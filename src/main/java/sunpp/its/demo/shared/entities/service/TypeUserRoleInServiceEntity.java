package sunpp.its.demo.shared.entities.service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TypeUserRoleInService")
@Comment("Типы ролей пользователя в сервисе системы")
public class TypeUserRoleInServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    @Comment("Первичный ключ")
    private Integer roleId;

    @Column(name = "roleName", unique = true)
    @Comment("Название роли пользователя в сервисе")
    private String roleName;
}
