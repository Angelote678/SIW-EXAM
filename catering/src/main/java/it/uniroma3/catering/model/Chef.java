package it.uniroma3.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Chef {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String nationality;
	
	@OneToMany(mappedBy="chef")
	private List<Buffet> buffets;

	public Chef(String firstName, String lastName, String nationality) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationality = nationality;
		this.buffets = new ArrayList<Buffet>();
	}

	public Chef() { 
		this.buffets = new ArrayList<Buffet>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Buffet> getBuffets() {
		return buffets;
	}

	public void addBuffets(Buffet buffet) {
		this.buffets.add(buffet);
	}
}
