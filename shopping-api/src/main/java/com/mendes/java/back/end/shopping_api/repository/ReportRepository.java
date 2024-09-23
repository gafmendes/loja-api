package com.mendes.java.back.end.shopping_api.repository;

import java.time.LocalDate;
import java.util.List;

import com.mendes.java.back.end.shopping_api.item.Shop;
import com.mendes.java.back.end.shopping_client.dto.ShopReportDTO;

public interface ReportRepository  {
	
	List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo);
	
	ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim);

}
