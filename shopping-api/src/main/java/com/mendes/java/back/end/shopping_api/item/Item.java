package com.mendes.java.back.end.shopping_api.item;

import com.mendes.java.back.end.shopping_client.dto.ItemDTO;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Item {
	
	private String productIdentifier;
	private Float price;
	
	public String getProductIdentifier() {
		return productIdentifier;
	}
	
	public void setProductIdentifier(String product) {
		this.productIdentifier = product;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
		
	public static Item convert(ItemDTO itemDTO) {
		Item item = new Item();
		item.setProductIdentifier(itemDTO.getProductIdentifier());
		item.setPrice(itemDTO.getPrice());
		return item;
	}

}
