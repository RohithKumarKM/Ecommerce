package org.lazE.EcommerceBackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.lazE.EcommerceBackend.dao.CategoryDAO;
import org.lazE.EcommerceBackend.dto.Category;
import org.springframework.stereotype.Repository;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<Category>();
	
	static {
		//First Category
		Category category = new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("Television description");
		category.setImageURL("CAT_1.png");
		category.setActive(true);
		
		categories.add(category);
		
		//Second Category
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("Mobile description");
		category.setImageURL("CAT_2.png");
		category.setActive(true);
		
		categories.add(category);
		
		//Third Category
		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("Laptop description");
		category.setImageURL("CAT_3.png");
		category.setActive(true);
		
		categories.add(category);
	}
	@Override
	public List<Category> list() {
		
		return categories;
	}
	@Override
	public Category get(int id) {
		// enhancedFor Loop
		for(Category category:categories) {
			if(category.getId() == id) return category;
		}
		return null;
	}

}
