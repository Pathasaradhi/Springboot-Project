package com.weshopify.platform.features.customers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weshopify.platform.features.customers.bean.CustomerBean;
import com.weshopify.platform.features.customers.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/view-customerBoard")
	public String viewCustomerDashBoard(Model model) {
		log.info("i am inn viewCustomerDashBoard page");
		List<CustomerBean> customerList = customerService.getAllCustomers();
		model.addAttribute("customerData", customerList);
		return "customer-dashboard";
	}
	
	@RequestMapping("/view-customerBoard/{noOfRecPerPage}")
	public String viewCustomerDashBoardWithPagination(@PathVariable("noOfRecPerPage") String noOfRecPerPage,Model model) {
		log.info("i am inn viewCustomerDashBoard page");
		log.info("curent page is:\t"+0);
		log.info("no.Of Rec Per Page is:\t"+noOfRecPerPage);
		List<CustomerBean> customerList = customerService.getAllCustomers(Integer.valueOf(noOfRecPerPage));
		model.addAttribute("currentPage", 0);
		model.addAttribute("noOfRecPerPage", noOfRecPerPage);
		
		int totalRecords = customerList.size();
		int noOfPages = totalRecords/Integer.valueOf(noOfRecPerPage);
		model.addAttribute("totalNoOfRecords", noOfPages);
		model.addAttribute("customerData", customerList);
		return "customer-dashboard";
	}
	
	@RequestMapping("/add-customer-view")
	public String addCustomerViewPage(Model model) {
		log.info("i am inn addCustomerViewPage page");
		model.addAttribute("customerFormBean", new CustomerBean());
		return "add-customer";
	}
	
	@RequestMapping(value = "/save-customer",method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute("customerFormBean") CustomerBean customerBean, Model model) {
		log.info("i am inn addCustomerViewPage page");
		log.info(customerBean.toString());
		
		if(customerBean.getIsSelfReg() != null && Boolean.valueOf(customerBean.getIsSelfReg())) {
			customerService.saveCustomer(customerBean);
			if(customerBean != null && customerBean.getId() >0) {
				
				String isReg="true";
				model.addAttribute("regMessage", isReg);
				
				return "customer-sefReg";
				
			}
		}else {
			customerService.saveCustomer(customerBean);
			
		}
		return "redirect:/view-customerBoard";
	}
	
	@RequestMapping(value = "/delete-customer/{customerId}",method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable("customerId") String customerId, Model model) {
		log.info("i am inn deleteCustomerPage:\t"+customerId);
		
		customerService.deleteCustomerById(Integer.valueOf(customerId));
		return "redirect:/view-customerBoard";
	}
	
	@RequestMapping(value = "/edit-customer/{customerId}",method = RequestMethod.GET)
	public String editCustomer(@PathVariable("customerId") String customerId, Model model) {
		log.info("i am inn deleteCustomerPage:\t"+customerId);
		
		CustomerBean customerBean = customerService.getCustomerById(Integer.valueOf(customerId));
		model.addAttribute("customerFormBean", customerBean);
		return "edit-customer";
	}
	
	
}
