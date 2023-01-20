package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;
import com.springdemo.util.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// Inject customer service layer using DI (previously customer DAO)
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam(required = false) String sort) {
		
		List<Customer> theCustomers = null;
		
		if (sort != null) {
			int theSortField = Integer.parseInt(sort);
			
			// Get customers from the service (previously DAO)
			theCustomers = customerService.getCustomers(theSortField);
			
		} else {
			// If no sort index, default as last name
			theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
		
		// Add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// Create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
		
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// Save the customer using customer service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		// Get the customer from the customer service
		Customer theCustomer = customerService.getCustomer(theId);
		
		// Set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// Send over to our form
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// Delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
									Model theModel) {
		
		// Search customer from the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		
		// Add the customers to the model
		theModel.addAttribute("theSearchName", theSearchName);
		theModel.addAttribute("customers", theCustomers);
		
		return "search-customers";
	}
	
}
