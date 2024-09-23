package com.mendes.java.back.end.shopping_client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotBlank
	private String productIdentifier;
    @NotBlank
	private String nome;
    @NotNull
    private Float preco;
    @NotNull
	private CategoryDTO category;
	
}
