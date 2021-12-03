import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CircleTest {

	@Test
	void testGetRadius() {
		Circle c = new Circle(2.0,2.0, 4);
		assertEquals(c.getRadius(), 4);
	}

}
