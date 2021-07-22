package study.chapter05.demo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Member {

	private String name;

	private int age;

	public Member(String name, int age) {
		this.name = name;
		this.age = age;
	}
}