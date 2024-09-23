package com.mendes.java.back.end.shopping_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mendes.java.back.end.shopping_client.dto.ProductDTO;
import com.mendes.java.back.end.shopping_client.exception.ProductNotFoundException;

import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	@Value("${PRODUCT_API_URL:http://localhost:54880}")
	private String productApiURL;
	
	public ProductDTO getProductByIdentifier(String productIdentifier) {

		try {
			WebClient webClient = WebClient.builder()
					.baseUrl(productApiURL)
					.build();

			Mono<ProductDTO> product = webClient.get()
					.uri("/product/" + productIdentifier)
					.retrieve()
					.bodyToMono(ProductDTO.class);

			return product.block();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductNotFoundException();
		}

	}

}
