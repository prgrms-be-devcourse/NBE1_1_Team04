package com.devcoruse.nbe1_1_team04.product.domain;

public record ProductContent(
        String productName,
        String description,
        String price,
        Category category
) {

}
