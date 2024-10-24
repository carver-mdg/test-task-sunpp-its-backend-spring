package sunpp.its.demo.shared.entities.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Comments;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TypeUserRoleInSystem")
@Comment("Типы ролей пользователя в системе")
public class TypeUserRoleInSystemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    @Comment("первичный ключ")
    private Integer roleId;

    @Column(name = "roleName", unique = true)
    @Comment("имя роли")
    private String roleName;
}
