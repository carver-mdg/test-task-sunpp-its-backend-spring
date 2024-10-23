package sunpp.its.demo.shared.entities.service.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TypeResponseRequestRoleInService")
public class TypeResponseRequestRoleInServiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "typeResponseId")
  private Integer typeResponseId;

  @Column(name = "typeResponseName", unique = true)
  private String typeResponseName;
}
