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

  @Bean
  public AdminService adminService(AdminRepository adminRepository, DriverWorkRepository driverWorkRepository, MoverWorkRepository moverWorkRepository) {
    return new AdminService(adminRepository, driverWorkRepository, moverWorkRepository);
  }

  @Bean
  public ClientService clientService(ClientRepository clientRepository, OrderRepository orderRepository) {
    return new ClientService(clientRepository, orderRepository);
  }

  @Bean
  public DriverService driverService(DriverRepository driverRepository, OrderRepository orderRepository, CarRepository carRepository) {
    return new DriverService(driverRepository, orderRepository, carRepository);
  }

  @Bean
  public MoverService moverService(MoverRepository moverRepository, OrderRepository orderRepository) {
    return new MoverService(moverRepository, orderRepository);
  }

  @Bean
  public OrderService orderService(OrderRepository orderRepository, DriverRepository driverRepository) {
    return new OrderService(orderRepository, driverRepository);
  }

}
