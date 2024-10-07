package sunpp.its.demo.shared.entities.service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import sunpp.its.demo.shared.entities.UserEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(
        name = "ServiceUser",
        uniqueConstraints = @UniqueConstraint(columnNames={"userId", "serviceId"})
)
public class ServiceUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serviceUserId")
    private Integer serviceUserId;

    @ManyToOne
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "roleId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TypeUserRoleInServiceEntity userRole;

    @ManyToOne
    @JoinColumn(name = "serviceId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ServiceEntity service;
}
