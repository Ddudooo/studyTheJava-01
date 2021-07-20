import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class MeetTest {

	@Test
	void isFullTest() {
		Meet meet = new Meet();
		meet.current = 10;
		meet.max = 100;

		assertFalse(meet.isFull());
	}
}