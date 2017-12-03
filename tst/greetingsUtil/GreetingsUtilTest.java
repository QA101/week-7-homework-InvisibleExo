package greetingsUtil;

import static org.junit.Assert.*;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;


//How would integration testing be performed for this example?
//By combining multiple units and tested as groups?

public class GreetingsUtilTest {
	static GreetingsUtil gu;
	static GreetingsUtil su;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Checking that object is null and not null after creation.
		assertNull(gu);
		gu = new GreetingsUtil();
		assertNotNull(gu);
		su = new GreetingsUtil();
	}

	//Test cases while gu is assigned as "going"
	@Before
	public void setUp() throws Exception {
		gu.ChangeState();
	}
	// The main test coverage, which could cover about 90-100% in this block
	@Test
	public void test() {
		assertEquals(gu.GoodBye(), "You already said that");
		assertEquals(gu.Hello(), "Hi!");
		assertEquals(gu.GoodBye(), "bye");
		gu.ChangeState();
		assertEquals(gu.Hello(), "You already said that");
		//Where would fail() fit into this code?
		//Is it more for messages of others when code isn't ready yet?
		//fail("your tests here");
	}
	//Resetting to original value of currentState
	@After
	public void tearDown() throws Exception {
		gu.ChangeState();
	}
	//Boolean assert tests (Overdone on unit testing, but a setup with different style
	@Test
	public void test2() {
		assertTrue(gu.currentState.equals("coming"));
		assertFalse(gu.currentState.equals("going"));
		gu.ChangeState();
		assertTrue(gu.currentState.equals("going"));
		assertFalse(gu.currentState.equals("coming"));
		assertTrue(gu.Hello().equalsIgnoreCase("hi!"));
		assertFalse(gu.GoodBye().equalsIgnoreCase("you alreaDy said that"));
		
		
	}
	//Integration Version Testing Style Attempt with Test3 - test 4
	@Test
	public void test3() {
		su.Hello();
		su.GoodBye();
		assertThat(su.currentState, containsString("going"));
		//Not Needed Really/ reduces coverage of code.
		/*if (!su.currentState.equals("going")) {
			fail("currentState isn't \"going\"");
		} */
	}
	@After
	public void resetSu() throws Exception {
		su.ChangeState();
	}
	
	@Test
	public void test4() {
		su.GoodBye();
		su.Hello();
		assertThat(su.currentState, containsString("coming"));
		/*if (!su.currentState.equals("coming")) {
			fail("currentState isn't \"coming\"");
		} */
	}

	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//Not nesscesary to do this(next line.)
		//gu = null;
	}

	

}
