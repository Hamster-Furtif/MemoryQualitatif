package fr.ensma.a3.ia.memory.dao;

import java.util.List;
import java.util.Optional;

import fr.ensma.a3.ia.memory.dao.DAOException.DuplicateElement;
import fr.ensma.a3.ia.memory.dao.DAOException.ElementNotFound;

public interface IDao<T> {

	public Optional<T> getById(int id);
	public Optional<T> getByValue(T elem);
	List<T> getAll();
	void create(T elem) throws DuplicateElement;
	void update(T elem) throws ElementNotFound;
	void delete(T elem) throws ElementNotFound;
	
}
