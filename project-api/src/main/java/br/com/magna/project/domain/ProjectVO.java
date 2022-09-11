package br.com.magna.project.domain;

public record ProjectVO(
        Long id,
        String name,
        String type,
        boolean active
) {

}
