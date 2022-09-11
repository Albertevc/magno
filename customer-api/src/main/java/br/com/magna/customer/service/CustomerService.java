package br.com.magna.customer.service;

import br.com.magna.customer.domain.CustomerCreationDTO;
import br.com.magna.customer.domain.CustomerUpdateDTO;
import br.com.magna.customer.domain.CustomerVO;
import br.com.magna.customer.domain.entity.Customer;
import br.com.magna.customer.domain.entity.factory.CustomerFactory;
import br.com.magna.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerFactory customerFactory;

    public Optional<CustomerVO> get(Long id) {
        return this.customerRepository.findById(id)
                .map(customer ->
                        new CustomerVO(
                                customer.getId(),
                                customer.getName(),
                                customer.getType()
                                        .name(),
                                customer.getActive()
                        )
                );
    }

    public Page<CustomerVO> getAll(Pageable pageable) {
        return this.customerRepository.findAll(pageable)
                .map(customer ->
                        new CustomerVO(
                                customer.getId(),
                                customer.getName(),
                                customer.getType()
                                        .name(),
                                customer.getActive()
                        )
                );
    }

    public Customer save(CustomerCreationDTO customerCreationDTO) {
        return this.customerRepository.save(
                this.customerFactory.createFrom(customerCreationDTO)
        );
    }

    public Customer save(CustomerUpdateDTO customerUpdateDTO) {
        return this.customerRepository.save(
                this.customerFactory.createFrom(customerUpdateDTO)
        );
    }

    @Transactional
    public void softDelete(Long id) {
        int deactivate = this.customerRepository.deactivate(id);
        if (deactivate == 0) {
            throw new EntityNotFoundException();
        }
        log.info("deleted {} records with id {}", deactivate, id);
    }

}
