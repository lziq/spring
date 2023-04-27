package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Customer;
import com.luv2code.springboot.thymeleafdemo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	// load customer data
	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService){
		this.customerService = customerService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		List<Customer> customers = customerService.findAll();

		// add to the spring model
		theModel.addAttribute("customers", customers);

		return "customers/list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model){
		Customer customer = new Customer();

		model.addAttribute("customer", customer);

		return "customers/customer-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model){
		Customer customer = customerService.findById(id);

		model.addAttribute("customer", customer);

		return "customers/customer-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int id){
		customerService.deleteById(id);

		return "redirect:/customers/list";
	}

	@PostMapping("/save")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result){
		if (result.hasErrors()){
			return "customers/customer-form";
		}

		customerService.save(customer);

		return "redirect:/customers/list";
	}
}









