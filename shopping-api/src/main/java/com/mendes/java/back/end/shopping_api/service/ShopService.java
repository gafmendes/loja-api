package com.mendes.java.back.end.shopping_api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mendes.java.back.end.shopping_api.converter.DTOConverter;
import com.mendes.java.back.end.shopping_api.item.Shop;
import com.mendes.java.back.end.shopping_api.repository.ShopRepository;
import com.mendes.java.back.end.shopping_client.dto.ItemDTO;
import com.mendes.java.back.end.shopping_client.dto.ProductDTO;
import com.mendes.java.back.end.shopping_client.dto.ShopDTO;
import com.mendes.java.back.end.shopping_client.dto.ShopReportDTO;
import com.mendes.java.back.end.shopping_client.dto.UserDTO;
import com.mendes.java.back.end.shopping_client.exception.ProductNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {

	private final ShopRepository shopRepository;
	private final ProductService productService;
	private final UserService userService;
		
	public List<ShopDTO> getAll() {
		List<Shop> shops = shopRepository.findAll();
		return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());		
	}
	
	public List<ShopDTO> getByUser(String userIdentifier) {
		List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
		return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());		
	}
		
	public List<ShopDTO> getByDate(ShopDTO shopDTO) {
		List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
		return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());		
	}
		
	public ShopDTO findById(long ProductId) {
		Optional<Shop> shop = shopRepository.findById(ProductId);
		if (shop.isPresent()) {
			return DTOConverter.convert(shop.get());
		}
		throw new ProductNotFoundException();
	}
	
	public ShopDTO save(ShopDTO shopDTO, String key) {		
		UserDTO userDTO = userService.getUserByCpf(shopDTO.getUserIdentifier(), key);
		validateProducts(shopDTO.getItems());
		
		shopDTO.setTotal(shopDTO.getItems()
				  .stream()
				  .map(x -> x.getPrice())
				  .reduce((float) 0, Float::sum));
		
		Shop shop = Shop.convert(shopDTO);
		shop.setDate(LocalDateTime.now());
		
		shop = shopRepository.save(shop);
		return DTOConverter.convert(shop);
	}

	private boolean validateProducts(List<ItemDTO> items) {
		for (ItemDTO  item : items) {
			ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());
			if (productDTO == null) {
				return false;
			}
			item.setPrice(productDTO.getPreco());
		}
		return true;		
	}
	
	public List<ShopDTO> getShopsByFilter(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo) {
		List<Shop> shops = shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
		return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());		
		
	}
	
	public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim) {
		return shopRepository.getReportByDate(dataInicio, dataFim);
	}
	
}
