package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {

    return args -> {
      employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar"));
      employeeRepository.save(new Employee("Frodo", "Baggins", "thief"));

      employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));


      orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
      orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

      orderRepository.findAll().forEach(order -> {
        log.info("Preloaded " + order);
      });

    };
  }
}

//package payroll;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///* What happens when it gets loaded?
// *   Spring Boot will run ALL CommandLineRunner beans once the application context is loaded.
// *   This runner will request a copy of the EmployeeRepository you just created.
// *   Using it, it will create two entities and store them.
// */
//@Configuration
//class LoadDatabase {
//
//  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//  @Bean
//  CommandLineRunner initDatabase(EmployeeRepository repository) {
//
//    return args -> {
//      log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
//      log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
//    };
//  }
//}