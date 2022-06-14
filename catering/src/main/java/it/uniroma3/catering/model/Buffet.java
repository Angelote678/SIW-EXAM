package it.uniroma3.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;

@Entity
public class Buffet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@ManyToOne(cascade = {CascadeType.MERGE})
	private Chef chef;
	
	@Fetch(value = org.hibernate.annotations.FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private List<Dish> dishes;

	public Buffet(String name, String description, Chef chef) {
		this.name = name;
		this.description = description;
		this.chef = chef;
		this.dishes = new ArrayList<Dish>();
	}

	public Buffet() {
		this.dishes = new ArrayList<Dish>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	
	public void addDish(Dish dish) {
		this.dishes.add(dish);
	}

	public void removeDish(Dish dish) {
		this.dishes.remove(dish);
	}
}
