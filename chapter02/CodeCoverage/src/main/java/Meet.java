public class Meet {

	int max;
	int current;

	public boolean isFull() {
		if (max == 0) {
			return false;
		}

		if (current < max) {
			return false;
		}

		return true;
	}
}