package com.mendes.java.back.end.product_api.converter;

import com.mendes.java.back.end.product_api.model.Category;
import com.mendes.java.back.end.product_api.model.Product;
import com.mendes.java.back.end.shopping_client.dto.CategoryDTO;
import com.mendes.java.back.end.shopping_client.dto.ProductDTO;

public class DTOConverter {
	
	public static CategoryDTO convert(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setNome(category.getNome());
		return categoryDTO;
	}	
		
	public static ProductDTO convert(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setNome(product.getNome());
		productDTO.setPreco(product.getPreco());
		productDTO.setProductIdentifier(product.getProductIdentifier());
		if (product.getCategory() != null) {
			productDTO.setCategory(DTOConverter.convert(product.getCategory()));
		}
		return productDTO;
	}
	
}

