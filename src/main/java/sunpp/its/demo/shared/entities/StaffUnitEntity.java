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
@Table(name = "StaffUnit")
@Comment("Штатная единица")
public class StaffUnitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffUnitId")
    @Comment("Первичный ключ")
    private Integer staffUnitId;

    @Column(name = "staffUnitName")
    @Comment("Название штатной единицы")
    private String staffUnitName;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Comment("Цех к которому привязана штатная единица")
    private DepartmentEntity department;
}
