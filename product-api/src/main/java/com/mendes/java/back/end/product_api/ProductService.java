package com.mendes.java.back.end.product_api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mendes.java.back.end.product_api.converter.DTOConverter;
import com.mendes.java.back.end.product_api.model.Product;
import com.mendes.java.back.end.product_api.repository.CategoryRepository;
import com.mendes.java.back.end.product_api.repository.ProductRepository;
import com.mendes.java.back.end.shopping_client.dto.ProductDTO;
import com.mendes.java.back.end.shopping_client.exception.CategoryNotFoundException;
import com.mendes.java.back.end.shopping_client.exception.ProductNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
		
	public List<ProductDTO> getAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(DTOConverter::convert).collect(Collectors.toList());		
	}
	
	public List<ProductDTO> getProductByCategoryId(Long categoryId) {
		List<Product> products = productRepository.getProductByCategory(categoryId);
		return products.stream().map(DTOConverter::convert).collect(Collectors.toList());		
	}
		
	public ProductDTO findByProductIdentifier(String  productIdentifier) {
		Product product = productRepository.findByProductIdentifier(productIdentifier);
		if (product != null) {
			return DTOConverter.convert(product);
		}
		throw new ProductNotFoundException();
	}
	
	public ProductDTO save(ProductDTO productDTO) {
		Boolean existsCategory = categoryRepository.existsById(productDTO.getCategory().getId());
		if (!existsCategory) {
			throw new CategoryNotFoundException();
		}				
		Product product = productRepository.save(Product.convert(productDTO));
		return DTOConverter.convert(product);
	}
	
	public ProductDTO delete(long id) throws ProductNotFoundException {
		Optional<Product> Product = productRepository.findById(id);
		if (Product.isPresent()) {
			productRepository.delete(Product.get());
		}
		throw new ProductNotFoundException();
	}

	public ProductDTO editProduct(long id, ProductDTO dto) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

		if (dto.getNome() != null || !dto.getNome().isEmpty()) {
			product.setNome(dto.getNome());
		}
		if (dto.getPreco() != null) {
			product.setPreco(dto.getPreco());
		}
		return DTOConverter.convert(productRepository.save(product));
	}

	public Page<ProductDTO> getAllPage(Pageable page) {
		Page<Product> users = productRepository.findAll(page);
		return users
				.map(DTOConverter::convert);
	}
	
}

