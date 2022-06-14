package it.uniroma3.catering.start;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.model.Chef;
import it.uniroma3.catering.model.Credentials;
import it.uniroma3.catering.model.Dish;
import it.uniroma3.catering.model.Ingredient;
import it.uniroma3.catering.service.BuffetService;
import it.uniroma3.catering.service.ChefService;
import it.uniroma3.catering.service.CredentialsService;
import it.uniroma3.catering.service.DishService;
import it.uniroma3.catering.service.IngredientService;

@Component
public class Start implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private CredentialsService credentialService;
    
    @Autowired
    DishService dishService;
    
    @Autowired
    private ChefService chefService;
    
    @Autowired
    private BuffetService buffetService;
    
    @Autowired
    private IngredientService ingredientService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    	
    	//Credentials
    	credentialService.saveCredentials(new Credentials("test1@test.com","p"));
    	credentialService.saveCredentials(new Credentials("test2@test.com","p"));
    	credentialService.saveCredentials(new Credentials("test3@test.com","p"));
    	credentialService.saveCredentials(new Credentials("angelote678","ejercito3"));
    	
    	//Ingredients
    	Ingredient ingredient1 = new Ingredient("Bread", "Spain", "Best flour and coal oven");
    	Ingredient ingredient2 = new Ingredient("Chocolate", "Colombia", "From the best chocolate farmers");
    	Ingredient ingredient3 = new Ingredient("Tomato", "Spain", "Spanish tomatoes from Almeria");
    	Ingredient ingredient4 = new Ingredient("Toasts", "France", "Best flour and coal oven");
    	Ingredient ingredient5 = new Ingredient("Beans", "Spagna", "classic black beans");

    	
    	//save ingredients
    	ingredientService.save(ingredient1);
    	ingredientService.save(ingredient2);
    	ingredientService.save(ingredient3);
    	ingredientService.save(ingredient4);
    	ingredientService.save(ingredient5);

    	
    	//I set the ingredients of the first dish
    	List<Ingredient> FirstDish = new ArrayList<Ingredient>();
    	FirstDish.add(ingredient1);
    	FirstDish.add(ingredient2);
    	
    	//I set the ingredients of the second dish
    	List<Ingredient> SecondDish = new ArrayList<Ingredient>();
    	SecondDish.add(ingredient3);
    	SecondDish.add(ingredient4);
    	SecondDish.add(ingredient5);
    	
    	//set first dish
    	Dish dish1 = new Dish("Merienda española", "Classic spanish snack with bread and chocolate" , FirstDish);
    	dishService.save(dish1);
    	
    	
    	//set second dish
    	Dish dish2 = new Dish("Pantumaca", "An authentic and distinguished breakfast " , SecondDish);
    	dishService.save(dish2);
    	
    	//create a dish list in which I set the created dish
    	List<Dish> dishes = new ArrayList<Dish>();
    	dishes.add(dish1);
    	dishes.add(dish2);
    	
    	//create and save cheff
    	Chef chef1= new Chef("Joan", "Canroca", "Spanish");
    	chefService.save(chef1);
    	
    	//I create the buffet, I add the dishes, chef and propperties
    	Buffet buffet1 = new Buffet("Spanish Snacks", "light food, great flavours", chef1);
    	buffet1.setDishes(dishes);
    	//I save the buffet
    	buffetService.save(buffet1);
    }

}
