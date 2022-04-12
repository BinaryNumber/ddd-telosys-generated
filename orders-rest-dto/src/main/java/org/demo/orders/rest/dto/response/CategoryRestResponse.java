/*
 * Generated by Telosys ( https://www.telosys.org/ )
 * 2022-04-12 (15:13:48)
 */
package org.demo.orders.rest.dto.response;

import org.demo.orders.rest.dto.common.AbstractRestResponse;
import org.demo.orders.rest.dto.CategoryRestDto;

import java.util.List;

public class CategoryRestResponse extends AbstractRestResponse<CategoryRestDto> {

    /**
     * Constructor
     * @param list
     * @param totalElements
     * @param pageSize
     */
    public CategoryRestResponse(List<CategoryRestDto> list, long totalElements, int pageSize) {
        super(list, totalElements, pageSize);
    }
}