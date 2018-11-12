package myMath;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PolynomTest {
Polynom p1=new Polynom("3x^2-x^3+x-1");
Polynom p2=new Polynom("x^2+x+1");
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Polynom p1=new Polynom("3x^2-x^3+x-1");
		Polynom p2=new Polynom("x^2+x+1");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void stringTest() {
		String actual=p1.toString();
		String expected="-x^3+3.0*x^2+x-1.0";
		assertEquals(true,expected.equals(actual));
	}
	@Test
	void fTest() {
		double x=1;
		double expected=2;
		double actual=p1.f(x);
		assertEquals(expected,actual,0.1);
	}
	@Test
	void addTest() {
		Polynom expected=new Polynom("-x^3+4x^2+2x");
		p1.add(p2);
		assertEquals(true,p1.equals(expected));
	}
	@Test
	void addMonomTest() {
		Monom m1=new Monom(2,2);
		Polynom expected=new Polynom("-x^3+5x^2+x-1");
		p1.add(m1);
		assertEquals(true,p1.equals(expected));
	}
	@Test
	void subTest() {
		Polynom expected=new Polynom("-x^3+2x^2-2");
		p1.substract(p2);
		assertEquals(true,p1.equals(expected));
	}
	@Test 
	void mulTest(){
		Polynom expected=new Polynom("-x^5+2.0*x^4+3.0*x^3+3.0*x^2-1.0");
		p1.multiply(p2);
		assertEquals(true,p1.equals(expected));
	}
	@Test
	void isZeroTest() {
		Polynom p1=new Polynom();
		assertTrue(p1.isZero());
	}
	@Test
	void rooTest() {
		Polynom pRoot=new Polynom("x^3-x^2+2");
		double expected=-1.00250244140625;
		double actual=pRoot.root(-200, 300, 0.01);
		assertEquals(expected,actual,0.01);
		
	}
	@Test
	void polynomAbleCopyTest() {
	 Polynom expected=(Polynom) p1.copy();
	 String Sp1=p1.toString();
	 String sExpected=expected.toString();
	 assertEquals(true,Sp1.equals(sExpected));
	
	}
	@Test
	void derivativeTest() {
		Polynom expected=new Polynom("-3.0*x^2+6.0*x+1.0");
		Polynom actual=(Polynom) p1.derivative();
		assertEquals(true,expected.equals(actual));
	}
	@Test
	void areaTest() {
		Polynom parea=new Polynom("3x^2+6x+2");
		double expected=18.25;
		double actual=parea.area(0, 2, 0.5);
		assertEquals(expected,actual,0.01);
	}
	@Test
	void areaUnderXaxisTest() {
		 Polynom p=new Polynom("0.2x^4-1.5x^3+3x^2-x-5");
		double expected =25.18363108708581;
		double actual=p.areaUnderXaxis(-0.94064,4.83104 , 0.01);
		assertEquals(expected,actual,0.01);
		
	}
	@Test
	void stringTest1() {
		String expected="-x^3+3.0*x^2+x-1.0";
		String actual=p1.toString();
		assertTrue(actual.equals(expected));
	}
}
