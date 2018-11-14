package myMath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Junit for the monom.
 * @author ariel and moshe
 *
 */
class JuniTestMonom {
   Monom m1=new Monom(3,2);
   Monom m2=new Monom(3,2);
    @BeforeEach
	void setUp() throws Exception {
		 Monom m1=new Monom(3,2);
		 Monom m2=new Monom(3,2);
	}
	@Test
	void getPower() {
		assertTrue(m1.get_power()==2);
		assertEquals(0,0);
	}
	@Test
	void getCoefficient() {
		assertTrue(m1.get_coefficient()==3);
	}
	@Test
	void AddTest() {
	Monom expected=new Monom(6,2);
	m1.add(m2);
	 assertEquals(true,expected.equals(m1),"the monoms are not equals");
	
	
		
		}
	@Test
	void mulTest() {
		Monom expected=new Monom(9,4);
		m1.mult(m2);
		assertEquals(true,expected.equals(m1));
	}
	@Test
	void derivativeTest() {
		Monom expected=new Monom(6,1);
		m1.derivative();
		assertEquals(true,expected.equals(m1));
	}
	@Test
	void testf() {
		double x=2;
		double expected=12;
		double actual=m1.f(x);
		assertEquals(expected,actual,0.1);
	}
	@Test
	void stringTest() {
		String expected="3.0*x^2";
		String actual=m1.toString();
		assertTrue(actual.equals(expected));
	}
	@Test
	void stringTest2() {
		Monom mTestString=new Monom(-1,1);
		String expected="-x";
		String actual= mTestString.toString();
		assertTrue(actual.equals(expected));
	}
	
	

	

}
