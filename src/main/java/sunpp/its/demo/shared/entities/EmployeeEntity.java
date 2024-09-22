package sunpp.its.demo.shared.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    private Integer EmployeeID;

    @Column(name = "FullName", unique = true)
    private String FullName;

    @OneToOne
    @JoinColumn(name = "StaffUnitID")
    private StaffUnitEntity staffUnit;
}
