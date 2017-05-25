package edu.mum.coffee.controller;

import java.util.List;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.ProductService;

@Controller
@RequestMapping("/admin")
public class ProductController {
	@Autowired
	public ProductService productService;

//	@RequestMapping
//	public String userTest(){
//		return "home";
//	}
	@GetMapping({ "/createProduct" })
	public ModelAndView createProductPage() {
		ModelAndView modelAndView = new ModelAndView("createProduct");
		Product product = new Product();
		modelAndView.addObject(product);
		modelAndView.addObject("productType", ProductType.values());
		return modelAndView;
	}

	@RequestMapping("/saveProduct")
	public String saveProductPage(@ModelAttribute(value = "product") Product product) {
		productService.save(product);
		return "redirect:/admin";
	}

	@RequestMapping("/listProducts")
	public String listProductPage(Map<String, Object> map) {
		List<Product> allProduct = productService.getAllProduct();
		map.put("products", allProduct);
		return "listProduct";
	}

	@RequestMapping("/deleteProducts/{id}")
	public String deleteProductPage(@PathVariable("id") int id) {
		Product product = productService.getProduct(id);
		productService.delete(product);
		return "redirect:/admin/listProducts";
	}

	@RequestMapping("/updateProducts/{id}")
	public String updateProductsPage(@PathVariable("id") int id, Map<String, Object> map) {
		Product product = productService.getProduct(id);
		map.put("product", product);
		map.put("productType", ProductType.values());
		return "updateProduct";
	}

	@ModelAttribute
	public void getProduct(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("product", productService.getProduct(id));
			System.out.println("111111");
		}
		System.out.println("2222222222");
	}

	@RequestMapping("/updateProductAndSave")
	public String updateProductsPage(Product product) {
		productService.save(product);
		System.out.println("333333");
		System.out.println(product);
		return "redirect:/admin/listProducts";
	}
}
