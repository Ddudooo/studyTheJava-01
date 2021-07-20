package study;

public class Application {

	public static void main(String[] args) {
//		try {
//			new ByteBuddy()
//				.redefine(Hat.class)
//				.method(named("pullOut"))
//				.intercept(value("Rabbit!"))
//				.make()
//				.saveIn(new File(
//					"C:\\workspace_java\\vscode-java\\thejava-01\\chapter02\\ArabbitInHat\\out\\production\\classes\\"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		System.out.println(new Hat().pullOut());
	}
}