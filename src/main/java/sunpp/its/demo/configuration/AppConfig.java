package sunpp.its.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sunpp.its.demo.shared.services.DepartmentService;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public DepartmentService departmentBean() {
        return new DepartmentService();
    }

//    @Bean(name="entityManagerFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//
//        return new LocalSessionFactoryBean();
//    }
}
