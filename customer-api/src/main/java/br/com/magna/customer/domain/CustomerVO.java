package br.com.magna.customer.domain;

public record CustomerVO(
        Long id,
        String name,
        String type,
        boolean active
) {

}
