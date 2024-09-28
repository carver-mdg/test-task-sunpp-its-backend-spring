package sunpp.its.demo.shared.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "UserOfSystem")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer UserID;

    @Column(name = "Login", unique = true)
    private String Login;

    @Column(name = "Password")
    private String Password;

    @OneToOne
    @JoinColumn(name = "EmployeeID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EmployeeEntity employee;
}
