package sunpp.its.demo.shared.entities.service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Service")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serviceId")
    private Integer serviceId;

    @Column(name = "serviceName", nullable = false, unique = true)
    private String serviceName;

    @Column(name = "serviceDesc", nullable = false)
    private String serviceDesc;

    @OneToMany(mappedBy = "service")
    @Column(name = "serviceUserId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ServiceUserEntity> users;
}
