package br.com.magna.customer.repository;

import br.com.magna.customer.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Modifying
    @Query("UPDATE Customer c set c.active = false WHERE c.id = :id")
    int deactivate(@Param("id") Long id);

}
