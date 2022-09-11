package br.com.magna.project.service;

import br.com.magna.project.domain.ProjectCreationDTO;
import br.com.magna.project.domain.ProjectUpdateDTO;
import br.com.magna.project.domain.ProjectVO;
import br.com.magna.project.domain.entity.Project;
import br.com.magna.project.domain.entity.factory.ProjectFactory;
import br.com.magna.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectFactory projectFactory;

    public Optional<ProjectVO> get(Long id) {
        return this.projectRepository.findById(id)
                .map(project ->
                        new ProjectVO(
                                project.getId(),
                                project.getName(),
                                project.getType()
                                        .name(),
                                project.getActive()
                        )
                );
    }

    public Page<ProjectVO> getAll(Pageable pageable) {
        return this.projectRepository.findAll(pageable)
                .map(project ->
                        new ProjectVO(
                                project.getId(),
                                project.getName(),
                                project.getType()
                                        .name(),
                                project.getActive()
                        )
                );
    }

    public Project save(ProjectCreationDTO projectCreationDTO) {
        return this.projectRepository.save(
                this.projectFactory.createFrom(projectCreationDTO)
        );
    }

    public Project save(ProjectUpdateDTO projectUpdateDTO) {
        return this.projectRepository.save(
                this.projectFactory.createFrom(projectUpdateDTO)
        );
    }

    @Transactional
    public void softDelete(Long id) {
        int deactivate = this.projectRepository.deactivate(id);
        log.info("deleted {} records with id {}", deactivate, id);
    }

}
