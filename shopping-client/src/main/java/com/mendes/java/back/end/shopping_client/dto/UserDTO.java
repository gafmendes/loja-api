package com.mendes.java.back.end.shopping_client.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String nome;
	private String cpf;
	private String endereco;
	private String key;
	private String email;
    private String telefone;
    private LocalDateTime dataCadastro;
}
