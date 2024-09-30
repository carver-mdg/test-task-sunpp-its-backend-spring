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
@Table(name = "StaffUnit")
public class StaffUnitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffUnitID")
    private Integer staffUnitID;

    @Column(name = "staffUnitName")
    private String staffUnitName;

    @ManyToOne
    @JoinColumn(name = "departmentID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DepartmentEntity department;
}
