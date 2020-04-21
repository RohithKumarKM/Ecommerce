package org.lazE.Ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.lazE.Ecommerce.util.FileUploadUtility;
import org.lazE.Ecommerce.validator.ProductValidator;
import org.lazE.EcommerceBackend.dao.CategoryDAO;
import org.lazE.EcommerceBackend.dao.ProductDAO;
import org.lazE.EcommerceBackend.dto.Category;
import org.lazE.EcommerceBackend.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {

		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Proucts");

		Product nProduct = new Product();

		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		/*
		 * nProduct - new Product
		 */
		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product Submitted Successfully!");
			}
		}
		mv.addObject("product", nProduct);

		return mv;
	}

	// Handling Product Submission
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results,
			Model model, HttpServletRequest request) {

		
		new ProductValidator().validate(mProduct, results);
		
		// checking if there are any errors
		if (results.hasErrors()) {

			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Proucts");
			model.addAttribute("message", "Validation Failed for Product Submission");
			return "page";
		}
		logger.info(mProduct.toString());
		/*
		 * mProduct - modified product
		 */
		productDAO.add(mProduct);

		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}

		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		//Activating and Deactivating based on the value of active field
		product.setActive(!product.isActive());
		//updating the product
		productDAO.update(product);
		return (isActive)? 	"You have Successfully Deactivated the Product with ID"+ product.getId() : 
							"You have Successfully Activated the Product with ID"+ product.getId();
	}

	// returning category for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();

	}
}
