package fr.ensma.a3.ia.memory.dao.entity;

public abstract class PoiDAOException extends Exception {

	private static final long serialVersionUID = 7791281468128066643L;

	public PoiDAOException(String string) {
		super(string);
	}

	public static class ElementDejaPresent extends PoiDAOException {
		
		private static final long serialVersionUID = 129373808642709470L;

		public <T extends Entity> ElementDejaPresent(T entity){
			super("Element [" + entity.toString() + "] deja dans la base ...");
		}
		
	}
	
	public static class ElementAbsent extends PoiDAOException {
		
		private static final long serialVersionUID = -250983278118965268L;

		public <T extends Entity> ElementAbsent(T entity){
			super("Element [" + entity.toString() + "] absent de la base ...");
		}
		
	}
	
}
