package br.com.magna.project.repository;

import br.com.magna.project.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Modifying
    @Query("UPDATE Project p set p.active = false WHERE p.id = :id")
    int deactivate(@Param("id") Long id);

}
