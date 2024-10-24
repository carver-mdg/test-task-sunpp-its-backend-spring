package sunpp.its.demo.shared.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Department")
@Comment("Цеха предприятия")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departmentId")
    @Comment("Первичный ключ")
    private Integer departmentId;

    @Column(name = "departmentName", unique = true)
    @Comment("Название цеха")
    private String departmentName;
}