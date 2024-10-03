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
    @Column(name = "staffUnitId")
    private Integer staffUnitId;

    @Column(name = "staffUnitName")
    private String staffUnitName;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DepartmentEntity department;
}
