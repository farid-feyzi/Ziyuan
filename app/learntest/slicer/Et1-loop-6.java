	public static void loop(int a, int b) {
		if (a <= b) {
			return;
		}
		b = b + a;
		a = a + 1;
		loop(a, b);
		return;
	}