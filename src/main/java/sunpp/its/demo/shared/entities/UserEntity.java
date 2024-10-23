package sunpp.its.demo.shared.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;

    @Column(name = "userName", unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "employeeId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EmployeeEntity employee;

    @OneToMany(mappedBy = "user")
    private List<ServiceUserEntity> usersInService;

//    @OneToOne
//    @JoinColumn(name = "roleInSystemId")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private TypeUserRoleInSystemEntity role;
}
