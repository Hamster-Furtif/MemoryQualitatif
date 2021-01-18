package fr.ensma.a3.ia.memory.dao;

import fr.ensma.a3.ia.memory.dao.entity.DAOEntity;

public abstract class DAOException extends Exception {

	private static final long serialVersionUID = -240028530532552287L;

	public DAOException(String string) {
		super(string);
	}

	
	public static class DuplicateElement extends DAOException {
		
		private static final long serialVersionUID = 1783379016900886967L;

		public <T extends DAOEntity> DuplicateElement(T entity){
			super("Duplicate element [" + entity.toString() + "].");
		}
		
	}
	
	
	public static class ElementNotFound extends DAOException {
		
		private static final long serialVersionUID = -1332858619109732949L;

		public <T extends DAOEntity> ElementNotFound(T entity){
			super("Could not find element [" + entity.toString() + "] in base.");
		}
		
	}
	
}
