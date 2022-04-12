/*
 * Generated by Telosys ( https://www.telosys.org/ )
 * 2022-04-12 (15:15:28)
 */

package org.demo.orders.application.service.impl;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import org.demo.orders.domain.model.Customer;
import org.demo.orders.domain.model.CustomerFactoryRestDto;
import org.demo.orders.domain.repository.CustomerRepository;
import org.demo.orders.application.mapper.CustomerMapper;
import org.demo.orders.application.exception.CustomerException;
import org.demo.orders.application.exception.ErrorCode;
import org.demo.orders.rest.dto.CustomerRestDto;
import org.demo.orders.application.service.CustomerCommandService;

/**
 * Service for "COMMANDS" (database write operations)
 * Entity Customer
 *
 * @author @{AUTHOR}
 */
@Component
public class CustomerCommandServiceImpl implements CustomerCommandService {

	@Inject
	private CustomerRepository customerRepository;
	
	@Inject
	private CustomerMapper customerMapper;
	
	

	/**
	 * Insert a new Customer 
	 * @param customerRestDto Data Transfer Object
	 * @return created aggregate
	 */
 	@Override
	public CustomerRestDto insertCustomer(CustomerRestDto customerRestDto) {
		Customer customer = CustomerFactoryRestDto.createCustomer(customerRestDto);
		customerRepository.insert(customer);
		//return customer;
		return customerMapper.toDTO(customer);
	}

	/**
	 * Delete Customer with the given id
	 * @param id id
	 */
 	@Override
	public void deleteCustomer(Integer id) {  

		Optional<Customer> customer = customerRepository.findById(id);

		if (customer.isPresent()) {
			customerRepository.delete(customer.get());
		} else {
			throw new CustomerException(ErrorCode.DELETE_NOT_FOUND); 
		}
	}
	
	/**
	 * Save Customer (update or create)
	 * @param id id
	 * @param customerRestDto Data Transfer Object
	 * @return true if created (else false)
	 */
 	@Override
	public boolean saveCustomer(Integer id, CustomerRestDto customerRestDto) {
		Customer customer = CustomerFactoryRestDto.createCustomer(customerRestDto);
		Customer current = customerRepository.findById(id).orElse(null);
		if (current == null) {
			// currentEntity not found => create a new one
			customerRepository.insert(customer);
			return true;
		}
		else {
			// currentEntity found => update from given data
			update(current, customer);
			return false;
		}
	}

    private void update(Customer currentEntity, Customer newEntity) {

		//--- You can update partially the existing entity
		// Call here specific update methods
		//// currentEntity.doSomething(newEntity.getXxx(), newEntity.getYyy());
		// Update
		//// customerRepository.update(currentEntity);

		//--- You can also just update the entity using the new one
		customerRepository.update(newEntity);		
    }
}