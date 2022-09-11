package br.com.magna.project.domain;

public record ProjectUpdateDTO(
        Long id,
        String name,
        String type,
        Boolean active
) {
}
