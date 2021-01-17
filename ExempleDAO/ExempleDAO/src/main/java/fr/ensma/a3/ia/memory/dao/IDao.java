package fr.ensma.a3.ia.memory.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

	public Optional<T> getById(int id);
	public Optional<T> getByValue(T elem);
	List<T> getAll();
	void create(T elem);
	void update(T elem);
	void delete(T elem);
	
}
