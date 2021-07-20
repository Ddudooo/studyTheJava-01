package study;

import static net.bytebuddy.implementation.FixedValue.value;
import static net.bytebuddy.matcher.ElementMatchers.any;
import static net.bytebuddy.matcher.ElementMatchers.named;

import java.lang.instrument.Instrumentation;
import net.bytebuddy.agent.builder.AgentBuilder;

public class JavaAgent {

	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("토끼 넣기!");
		new AgentBuilder.Default()
			.type(any())
			.transform(
				((builder, typeDescription, classLoader, module) ->
					builder.method(named("pullOut"))
						.intercept(value("Rabbit!"))))
			.installOn(inst);
	}
}