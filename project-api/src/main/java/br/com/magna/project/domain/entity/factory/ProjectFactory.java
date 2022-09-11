package br.com.magna.project.domain.entity.factory;

import br.com.magna.project.domain.ProjectCreationDTO;
import br.com.magna.project.domain.ProjectUpdateDTO;
import br.com.magna.project.domain.entity.Customer;
import br.com.magna.project.domain.entity.Project;
import br.com.magna.project.domain.entity.ProjectTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class ProjectFactory {

    public Project createFrom(ProjectCreationDTO projectCreationDTO) {
        Project project = new Project();

        project.setName(projectCreationDTO.name());
        project.setType(ProjectTypeEnum.valueOf(projectCreationDTO.type()));
        Customer customer = new Customer();
        customer.setId(projectCreationDTO.customerId());
        project.setCustomer(customer);
        project.setActive(true);

        return project;
    }

    public Project createFrom(ProjectUpdateDTO projectUpdateDTO) {
        Project project = new Project();

        project.setId(projectUpdateDTO.id());
        project.setName(projectUpdateDTO.name());
        project.setType(ProjectTypeEnum.valueOf(projectUpdateDTO.type()));
        project.setActive(projectUpdateDTO.active());

        return project;
    }

}
