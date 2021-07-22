package study.chapter05.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MemberTest {

	@Test
	void propertiesTest() {
		Member member = new Member("이름", 10);

		assertEquals(10, member.getAge());
		assertEquals("이름", member.getName());
	}
}