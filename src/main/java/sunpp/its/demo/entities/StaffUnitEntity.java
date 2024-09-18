package sunpp.its.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "StaffUnit")
public class StaffUnitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StaffUnitID")
    private Integer StaffUnitID;

    @Column(name = "StaffUnitName", unique = true)
    private String StaffUnitName;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    private DepartmentEntity department;
}
