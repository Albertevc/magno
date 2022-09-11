package br.com.magna.customer.domain.entity.factory;

import br.com.magna.customer.domain.CustomerCreationDTO;
import br.com.magna.customer.domain.CustomerUpdateDTO;
import br.com.magna.customer.domain.entity.Customer;
import br.com.magna.customer.domain.entity.CustomerTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class CustomerFactory {

    public Customer createFrom(CustomerCreationDTO customerCreationDTO) {
        Customer customer = new Customer();

        customer.setName(customerCreationDTO.name());
        customer.setType(CustomerTypeEnum.valueOf(customerCreationDTO.type()));
        customer.setActive(true);

        return customer;
    }

    public Customer createFrom(CustomerUpdateDTO customerUpdateDTO) {
        Customer customer = new Customer();

        customer.setId(customerUpdateDTO.id());
        customer.setName(customerUpdateDTO.name());
        customer.setType(CustomerTypeEnum.valueOf(customerUpdateDTO.type()));
        customer.setActive(customerUpdateDTO.active());

        return customer;
    }

}
