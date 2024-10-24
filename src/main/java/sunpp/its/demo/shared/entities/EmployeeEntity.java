package sunpp.its.demo.shared.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Employee")
@Comment("Работник предприятия")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    @Comment("Первичный ключ")
    private Integer employeeId;

    @Column(name = "fullName", unique = true)
    @Comment("Полное имя работника")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "staffUnitId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Comment("Штатная единица, которую занимает работник")
    private StaffUnitEntity staffUnit;
}
