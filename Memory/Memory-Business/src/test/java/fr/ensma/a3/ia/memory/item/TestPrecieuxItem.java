package fr.ensma.a3.ia.memory.item;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPrecieuxItem {
	private PrecieuxItem anneau;
	
	@Before
	public void initTest() {
		anneau = new PrecieuxItem("Gollum");
	}
	
	@Test
	public void T00_testConstructeur() {
		Assert.assertTrue("Gollum" == anneau.getID());
	}
}
