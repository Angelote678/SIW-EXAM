package it.uniroma3.catering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.model.Chef;
import it.uniroma3.catering.model.Dish;
import it.uniroma3.catering.model.Ingredient;
import it.uniroma3.catering.service.BuffetService;
import it.uniroma3.catering.service.ChefService;
import it.uniroma3.catering.service.DishService;
import it.uniroma3.catering.service.IngredientService;

@Controller
public class GenericController {
	
	@Autowired
	private BuffetService bs;
	
	@Autowired
	private DishService ds;

	@Autowired
	private ChefService cs;

	@Autowired
	private IngredientService is;
	
	@GetMapping("/home")
	public String visitHome(Model model) {
		List<Buffet> buffets = bs.findAll();
		model.addAttribute("buffets", buffets);
		return "home.html";
	}
	
	@GetMapping("/home/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.bs.findById(id);
		model.addAttribute("buffet", buffet);
		model.addAttribute("chef", buffet.getChef());
		return "buffet.html";
	}
	
	@GetMapping("/buffet/{id}")
	public String getDish(@PathVariable("id") Long id, Model model) {
		Dish dish = this.ds.findById(id);
		model.addAttribute("dish", dish);
		return "dish.html";
	}
	
	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model) {
		Chef chef = this.cs.findById(id);
		model.addAttribute("chef", chef);
		return "chef.html";
	}
	
	@GetMapping("/dish/{id}")
	public String getIngredient(@PathVariable("id") Long id, Model model) {
		Ingredient ingredient = this.is.findById(id);
		model.addAttribute("ingredient", ingredient);
		return "ingredient.html";
	}
}
