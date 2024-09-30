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
@Table(name = "Employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeID")
    private Integer employeeID;

    @Column(name = "fullName", unique = true)
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "staffUnitID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StaffUnitEntity staffUnit;
}
