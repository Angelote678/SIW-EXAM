package it.uniroma3.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.repository.BuffetRepository;

@Service
public class BuffetService {
	
	@Autowired
	private BuffetRepository br;

	@Transactional
	public void save(Buffet buffet) {
		br.save(buffet);
	}

	public List<Buffet> findAll() {
		List<Buffet> buffets = new ArrayList<Buffet>();
		for (Buffet b : br.findAll()) { buffets.add(b); } //La findAll ritorna un iteratore, non una lista
		return buffets;
	}

	@Transactional
	public void deleteById(Long id) {
		br.deleteById(id);
	}

	public Buffet findById(Long id) {
		return this.br.findById(id).get();
	}

	public boolean existsByName(String name) {
		return this.br.existsByName(name);
	}
	
}
