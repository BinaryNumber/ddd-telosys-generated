/*
 * Generated by Telosys ( https://www.telosys.org/ )
 * 2022-04-12 (15:15:28)
 */

package org.demo.orders.application.service.impl;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

import org.demo.orders.rest.dto.response.CatalogRestResponse;
import org.demo.orders.domain.model.Catalog;
import org.demo.orders.domain.model.CatalogId;
import org.demo.orders.domain.repository.CatalogRepository;
import org.demo.orders.application.mapper.CatalogMapper;
import org.demo.orders.rest.dto.CatalogRestDto;
import org.demo.orders.application.service.CatalogQueryService;

/**
 * Service for "QUERIES" (database read operations)
 * Entity Catalog
 *
 * @author Telosys
 */
@Component
public class CatalogQueryServiceImpl implements CatalogQueryService {

	@Inject
	private CatalogRepository catalogRepository;
	
	@Inject
	private CatalogMapper catalogMapper;

	private CatalogId buildId(short year, short quarter) {
		return new CatalogId(year, quarter);
	}
	
	/**
	 * Get and return a Catalog instance for the given id
	 * @param year id
	 * @param quarter id
	 * @return entity found (or null if not found)
	 */
 	@Override
	public CatalogRestDto getCatalog(short year, short quarter) {
		Optional<Catalog> catalog = catalogRepository.findById(buildId(year, quarter));
		if (catalog.isPresent()) {
			return catalogMapper.toDTO(catalog.get());
		}
		else {
			return null;
		}
	}
	
	/**
	 * Get a page of Catalog 
	 * @param page page number (from 1 to N)
	 * @param size page size
	 * @param sort
	 * @param order
	 * @return
	 */
 	@Override
	public CatalogRestResponse findCatalog(Integer page, Integer size, String sort, String order) {

		// Find all in database
		List<Catalog> catalogList = catalogRepository.findAll(page-1, size, sort, order);

		// Count all in database
		long totalElements = catalogRepository.count();
		
		// Build API response object
		//return buildResponse(catalogList, totalElements, size);
		List<CatalogRestDto> list = catalogMapper.toDTOList(catalogList);
		return new CatalogRestResponse(list, totalElements, size);
	}

}