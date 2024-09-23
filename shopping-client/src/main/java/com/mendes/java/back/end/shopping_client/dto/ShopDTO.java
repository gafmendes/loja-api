package com.mendes.java.back.end.shopping_client.dto;

import java.time.LocalDateTime;
import java.util.List;

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
public class ShopDTO {

	@NotBlank
	private String userIdentifier;
	@NotNull
	private Float total;
	@NotNull
	private LocalDateTime date;
	@NotNull
	private List<ItemDTO> items;

}
