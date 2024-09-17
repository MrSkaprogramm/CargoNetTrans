package com.androidcargo.spring.config;

import com.androidcargo.spring.repository.*;
import com.androidcargo.spring.service.impl.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.androidcargo.spring")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement(proxyTargetClass = true)
public class SpringConfig implements WebMvcConfigurer {
  @Value("${hibernate.dialect}")
  private String hibernateDialect;

  @Value("${hibernate.show_sql}")
  private String hibernateShowSql;

  @Value("${db.driver}")
  private String connectionDriverClass;

  @Value("${db.url}")
  private String connectionUrl;

  @Value("${db.user}")
  private String connectionUsername;

  @Value("${db.password}")
  private String connectionPassword;

  @Value("${db.poolsize}")
  private int poolSize;

  @Bean
  public AdminService personService(AdminRepository adminRepository, ClientRepository clientRepository, DriverRepository driverRepository, CarRepository carRepository,  OrderRepository orderRepository, CarDataRepository carDataRepository, DriverDataRepository driverDataRepository, MoverDataRepository moverDataRepository) {
    return new AdminService(adminRepository, clientService(clientRepository), driverService(driverRepository, carService(carRepository)), carService(carRepository), orderService(orderRepository, dataService(carDataRepository, driverDataRepository, moverDataRepository));
  }

  @Bean
  public CarService carService(CarRepository carRepository) {
    return new CarService(carRepository);
  }

  @Bean
  public DataService dataService(CarDataRepository carDataRepository, DriverDataRepository driverDataRepository, MoverDataRepository moverDataRepository) {
    return new DataService(carDataRepository, driverDataRepository, moverDataRepository);
  }

  @Bean
  public ClientService clientService(ClientRepository clientRepository) {
    return new ClientService(clientRepository);
  }

  @Bean
  public DriverService driverService(DriverRepository driverRepository, CarService carService, CarRepository carRepository) {
    return new DriverService(driverRepository, carService(carRepository));
  }

  @Bean
  public MoverService moverService(MoverRepository moverRepository) {
    return new MoverService(moverRepository);
  }

  @Bean
  public OrderService orderService(OrderRepository orderRepository, CarDataRepository carDataRepository, DriverDataRepository driverDataRepository, MoverDataRepository moverDataRepository) {
    return new OrderService(orderRepository, dataService(carDataRepository, driverDataRepository, moverDataRepository));
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

    factory.setDataSource(dataSource()); // обновленная строка
    factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    factory.setPackagesToScan("com.andersen.tr.model");

    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.dialect", hibernateDialect);
    jpaProperties.put("hibernate.show_sql", hibernateShowSql);
    jpaProperties.put("hibernate.hbm2ddl.auto", "update");
    factory.setJpaProperties(jpaProperties);
    return factory;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(connectionDriverClass);
    dataSource.setUrl(connectionUrl);
    dataSource.setUsername(connectionUsername);
    dataSource.setPassword(connectionPassword);
    return dataSource;
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    JpaTransactionManager tm = new JpaTransactionManager();
    tm.setEntityManagerFactory(entityManagerFactory().getObject());
    return tm;
  }

}
