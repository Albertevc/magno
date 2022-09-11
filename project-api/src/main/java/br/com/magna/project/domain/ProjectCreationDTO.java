package br.com.magna.project.domain;

public record ProjectCreationDTO(
        String name,
        String type,
        Long customerId
) {
}
