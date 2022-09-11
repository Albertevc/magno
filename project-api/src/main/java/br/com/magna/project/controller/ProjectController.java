package br.com.magna.project.controller;

import br.com.magna.project.domain.ProjectCreationDTO;
import br.com.magna.project.domain.ProjectUpdateDTO;
import br.com.magna.project.domain.ProjectVO;
import br.com.magna.project.domain.entity.Customer;
import br.com.magna.project.domain.entity.Project;
import br.com.magna.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "Find one project")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Project Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProjectVO.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Project not found",
                    content = @Content
            )
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProjectVO> customer(@PathVariable("id") Long id) {
        return ResponseEntity.of(
                this.projectService.get(id)
        );
    }

    @Operation(summary = "List all projects, paged")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listed successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProjectVO.class)
                            )
                    }
            )
    }
    )
    @GetMapping
    public ResponseEntity<Page<ProjectVO>> projects(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(
                this.projectService.getAll(pageable)
        );
    }

    @Operation(summary = "Create one project")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Project created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Project.class)
                            )
                    }
            )
    }
    )
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody ProjectCreationDTO projectCreationDTO) {
        return new ResponseEntity<>(
                this.projectService.save(projectCreationDTO),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Update given project")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Project updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Project.class)
                            )
                    }
            )
    }
    )
    @PutMapping
    public ResponseEntity<Project> update(@RequestBody ProjectUpdateDTO projectUpdateDTO) {
        return ResponseEntity.ok(
                this.projectService.save(projectUpdateDTO)
        );
    }

    @Operation(summary = "Delete referenced project")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Project deleted"
            )
    }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.projectService.softDelete(id);
        return ResponseEntity.ok()
                .build();
    }

}
