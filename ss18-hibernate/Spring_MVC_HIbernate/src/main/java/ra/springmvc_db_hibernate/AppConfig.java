package ra.springmvc_db_hibernate;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ra")
public class AppConfig implements WebMvcConfigurer {
    @Bean
    public ViewResolver resolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    //Cấu hình kết nối với csdl
    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:1028/db_student?createDatabaseIfNotExist=true");
        source.setUsername("root");
        source.setPassword("123456");
        return source;
    }

    //Cấu hình SessionFactory
    @Bean
    public SessionFactory sessionFactory(){
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        //thiết lập các thông số kết nối với csdl
        sf.setDataSource(getDataSource());
        //Quét qua các package tìm các class ORM (class máp với bảng trong database
        sf.setPackagesToScan("ra.springmvc_db_hibernate.entity");
        //Các thông số cấu hình của hibernate
        Properties props = new Properties();
        props.put("hibernate.hbm2ddl.auto","update");
        props.put("hibernate.show_sql",true); // Khi gọi đếm hàm của hibernate thì sẽ sinh ra câu lệnh sql ở màn hình console
        props.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect"); // Nói cho hibernate biết sẽ làm việc với hệ quản trị csdl nào (mysql, postgree, sqlserver, oracle)
        sf.setHibernateProperties(props);
        try {
            sf.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sf.getObject();
    }
}
