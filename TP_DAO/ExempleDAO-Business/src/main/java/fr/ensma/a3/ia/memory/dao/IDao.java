package fr.ensma.a3.ia.memory.dao;

import java.util.List;
import java.util.Optional;

import fr.ensma.a3.ia.memory.dao.entity.PoiDAOException.ElementAbsent;
import fr.ensma.a3.ia.memory.dao.entity.PoiDAOException.ElementDejaPresent;

public interface IDao<T> {

	public Optional<T> getById(int id);
	public Optional<T> getByValue(T elem);
	List<T> getAll();
	void create(T elem) throws ElementDejaPresent;
	void update(T elem) throws ElementAbsent;
	void delete(T elem) throws ElementAbsent;
	
}
