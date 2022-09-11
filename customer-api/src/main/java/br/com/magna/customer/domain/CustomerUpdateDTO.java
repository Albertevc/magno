package br.com.magna.customer.domain;

public record CustomerUpdateDTO(
        Long id,
        String name,
        String type,
        Boolean active
) {
}
