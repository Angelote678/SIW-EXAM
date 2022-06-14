package it.uniroma3.catering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.catering.controller.validator.BuffetValidator;
import it.uniroma3.catering.controller.validator.DishValidator;
import it.uniroma3.catering.controller.validator.IngredientValidator;
import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.model.Chef;
import it.uniroma3.catering.model.Dish;
import it.uniroma3.catering.model.Ingredient;
import it.uniroma3.catering.service.BuffetService;
import it.uniroma3.catering.service.ChefService;
import it.uniroma3.catering.service.DishService;
import it.uniroma3.catering.service.IngredientService;

@Controller
public class AdminController {
		
	@Autowired
	private BuffetValidator bv;
	
	@Autowired
	private BuffetService bs;

	@Autowired
	private DishService ds;
	
	@Autowired
	private ChefService cs;

	@Autowired
	private DishValidator dv;
	
	@Autowired
	private IngredientService is;
	
	@Autowired
	private IngredientValidator iv;
	
	private Buffet currentBuffet;

	private Dish currentDish;
	
	@GetMapping("/login")
	public String showLoginForm (Model model) {
		return "loginForm.html";
	}
	
	@GetMapping("/logout")
	public String returnIndex(Model model) {
		return "index.html";
	}
	
    @GetMapping("/default")
    public String defaultAfterLogin(Model model) {
    	model.addAttribute("buffets", bs.findAll());
        return "admin/home.html";
    }
    
    @GetMapping("/homead/{id}")
    public String getBuffet(@PathVariable("id") Long id, Model model) {
    	Buffet buffet = this.bs.findById(id);
    	this.currentBuffet = buffet;
    	model.addAttribute("buffet", buffet);
    	model.addAttribute("chef", buffet.getChef());
    	return "admin/buffet.html";
    }
    
    @GetMapping("/chefad/{id}")
    public String getChef(@PathVariable("id") Long id, Model model) {
    	Chef chef = this.cs.findById(id);
    	model.addAttribute("chef", chef);
    	return "admin/chef.html";
    }
    
    @GetMapping("/buffetad/{id}")
    public String getDish(@PathVariable("id") Long id, Model model) {
    	Dish dish = this.ds.findById(id);
    	model.addAttribute("dish", dish);
    	return "admin/dish.html";
    }
    
    @GetMapping("/dishad/{id}")
    public String getIngredient(@PathVariable("id") Long id, Model model) {
    	Ingredient ingredient = this.is.findById(id);
    	model.addAttribute("ingredient", ingredient);
    	return "admin/ingredient.html";
    }
    
    @GetMapping("/todeletebuffet/{id}")
    public String toDeleteBuffet(@PathVariable("id") Long id, Model model) {
    	this.bs.deleteById(id);
		model.addAttribute("buffets", this.bs.findAll());
    	return "admin/home.html";
    }
    
    @GetMapping("/todeletedish/{id}")
    public String toDeleteDish(@PathVariable("id") Long id, Model model) {
    	Dish dish = this.ds.findById(id);
    	for(Buffet b : this.bs.findAll()) {
    		if(b.getDishes().contains(dish)) {
    			b.removeDish(dish);
    			this.bs.save(b);
    			this.currentBuffet = b;
    		}
    	}
    	this.ds.deleteById(id);
		model.addAttribute("buffet", this.currentBuffet);
		model.addAttribute("chef", this.currentBuffet.getChef());
    	return "admin/buffet.html";
    }
    
    @GetMapping("/todeleteingredient/{id}")
    public String toDeleteIngredient(@PathVariable("id") Long id, Model model) {
    	Ingredient ingredient = this.is.findById(id);
    	for(Buffet b : this.bs.findAll()) {
    		for(Dish d : b.getDishes()) {
    			if(d.getIngredients().contains(ingredient)) {
    				d.removeIngredient(ingredient);
    				this.ds.save(d);
    				this.currentDish = d;
    			}
    		}
    	}
    	this.is.deleteById(id);
		model.addAttribute("dish", this.currentDish);
		return "admin/dish.html";
    }
    
    @GetMapping("/addbuffet")
	public String showBuffetForm (Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chef", new Chef());
		return "admin/buffetregister.html";
	}
    
    @PostMapping("/addbuffet")
    public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bbr,  //lo que hacemos es meter valid y modellattribute a buffet y chef
    		@Valid @ModelAttribute("chef") Chef chef, BindingResult cbr, Model model) {
    	this.bv.validate(buffet, bbr); //validamos
    	if(!bbr.hasErrors()) { //si el validador no tiene errores
    		if(this.cs.existsByFirstNameAndLastName(chef.getFirstName(), chef.getLastName())) { //si existe con chefservice apellido y nombre
    			chef = this.cs.findByFirstNameAndLastName(chef.getFirstName(), chef.getLastName()); //ponemos al chef como este
    		}
    		   chef.addBuffets(buffet); //añadimos al chef un buffet
    		   this.cs.save(chef); //guardamos con chefservice el chef
    		   buffet.setChef(chef); //metemos al buffet el cheff
    		   this.bs.save(buffet);	//guardamos con buffetservice el buffet
    		   model.addAttribute("buffets", this.bs.findAll()); //añadimos al model el atributo buffets, añadiendolos todos
    		return "admin/home.html";
    	}
    	return "admin/buffetregister.html";
    }
    
    @GetMapping("/adddishto/{id}")
    public String getDishForm(@PathVariable("id") Long id, Model model) {
    	this.currentBuffet = this.bs.findById(id);
    	model.addAttribute("dish", new Dish());
    	return "admin/dishregister.html";
    }
    
    @PostMapping("/adddish")
    public String addDish(@Valid @ModelAttribute("dish") Dish dish, BindingResult br, Model model) {
    	this.dv.validate(dish, br);
    	if(!br.hasErrors()) {
    		this.ds.save(dish);
    		this.currentBuffet.addDish(dish);
    		this.bs.save(currentBuffet);
    		model.addAttribute("buffet", currentBuffet);
    		model.addAttribute("chef", currentBuffet.getChef());
    		return "admin/buffet.html";
    	}
    	return "admin/dishregister.html";
    }
    
    @GetMapping("/addingredientto/{id}")
    public String getIngredientForm(@PathVariable("id") Long id, Model model) {
    	this.currentDish = this.ds.findById(id);
    	model.addAttribute("ingredient", new Ingredient());
    	return "admin/ingredientregister.html";
    }
    
    @PostMapping("/addingredient")
    public String addIngredient(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult br, Model model) {
    	this.iv.validate(ingredient, br);
    	if(!br.hasErrors()) {
    		String name = ingredient.getName();
    	    if(this.is.existsByName(name)) {
    	    	ingredient = this.is.findByName(name);
		    }
    	    this.is.save(ingredient);
    	    this.currentDish.addIngredient(ingredient);
    	    this.ds.save(currentDish);
    	    model.addAttribute("dish", this.currentDish);
    	    return "admin/dish.html";
    	}
    	return "admin/ingredientregister.html";
    }
    
    
    
    
    
}