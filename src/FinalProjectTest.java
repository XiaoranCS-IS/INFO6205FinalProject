import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import paraType.City;
import paraType.Person;
import paraType.Policy;
import paraType.Virus;
import window.Simulation;

class FinalProjectTest {

	@Test
	void testGetNumber() {
		int x = Simulation.getNumber(1);
		int y = Simulation.getNumber(0);
		assertEquals(1, x, 1.0E-7);
		assertEquals(0, y, 1.0E-7);
	}
	
	@Test
	void testCalculateRate() {
		double x = Simulation.calculateRate(1, 0, 0, 0);
		assertEquals(1, x, 1.0E-7);
		x = Simulation.calculateRate(1, 1, 0, 0);
		assertEquals(0, x, 1.0E-7);
		x = Simulation.calculateRate(1, 0, 1, 0);
		assertEquals(0, x, 1.0E-7);
		x = Simulation.calculateRate(1, 0, 0, 1);
		assertEquals(0, x, 1.0E-7);
		x = Simulation.calculateRate(0.5, 0.5, 0.5, 0.5);
		assertEquals(0.0625, x, 1.0E-7);
	}
	
	@Test
	void testCalculateInfectedCount() {
		Virus virus = new Virus();
		City city = new City();
		Policy policy = new Policy();
		Simulation s = new Simulation(virus, city, policy);
		Person p = new Person(1, null);
		p.setDay(0);
		s.setDailyInfected(new int[2]);
		int x = s.calculateInfectedCount(p, 2, 1, 1, 0, 0);
		assertEquals(3, x, 1.0E-7);
		
		s = new Simulation(virus, city, policy);
		p = new Person(1, null);
		p.setDay(0);
		s.setDailyInfected(new int[2]);
		x = s.calculateInfectedCount(p, 2, 1, 1, 1, 0); // 100% dead
		assertEquals(1, x, 1.0E-7);
		
		s = new Simulation(virus, city, policy);
		p = new Person(1, null);
		p.setDay(0);
		s.setDailyInfected(new int[2]);
		x = s.calculateInfectedCount(p, 2, 1, 1, 0, 1); // 100% self-healing
		assertEquals(0, x, 1.0E-7);
		
		s = new Simulation(virus, city, policy);
		p = new Person(1, null);
		p.setDay(0);
		s.setDailyInfected(new int[2]);
		x = s.calculateInfectedCount(p, 2, 1, 0, 0, 0);
		assertEquals(1, x, 1.0E-7);
		
		s = new Simulation(virus, city, policy);
		p = new Person(1, null);
		p.setDay(0);
		s.setDailyInfected(new int[2]);
		x = s.calculateInfectedCount(p, 2, 2, 1, 0, 0);
		assertEquals(9, x, 1.0E-7);
		
		s = new Simulation(virus, city, policy);
		p = new Person(1, null);
		p.setDay(0);
		s.setDailyInfected(new int[2]);
		x = s.calculateInfectedCount(p, 2, 2, 0, 0, 0);
		assertEquals(1, x, 1.0E-7);
	}
	
	@Test
	void testCalculateRValue() {
		Virus virus = new Virus();
		City city = new City();
		Policy policy = new Policy();
		Simulation s = new Simulation(virus, city, policy);
		Person p = new Person(1, null);
		p.setDay(0);
		s.setDailyInfected(new int[3]);
		int x = s.calculateInfectedCount(p, 2, 2, 1, 0, 0);
		assertEquals(9, x, 1.0E-7);
		double r = s.calculateRValue(2, 2);
		assertEquals(1, r, 1.0E-7);
		
		s = new Simulation(virus, city, policy);
		p = new Person(1, null);
		p.setDay(0);
		s.setDailyInfected(new int[3]);
		x = s.calculateInfectedCount(p, 2, 2, 0, 0, 0);
		assertEquals(1, x, 1.0E-7);
		r = s.calculateRValue(2, 2);
		assertEquals(1/9, r, 1.0E-7);
	}
}
