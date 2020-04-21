package org.lazE.Ecommerce.validator;

import org.lazE.EcommerceBackend.dto.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Product product = (Product) target;

		// Checking if the file is selected are not
		if (product.getFile() == null || product.getFile().getOriginalFilename().equalsIgnoreCase("")) {
			errors.rejectValue("file", null, "Please select an image file to upload");
			return;
		}

		if (!(	product.getFile().getContentType().equalsIgnoreCase("image/jpeg") 	|| 
				product.getFile().getContentType().equalsIgnoreCase("image/png")	|| 
				product.getFile().getContentType().equalsIgnoreCase("image/gif"))) 
		{
			errors.rejectValue("file", null, "Please use only image file to upload");
			return;
		}
	}

}
