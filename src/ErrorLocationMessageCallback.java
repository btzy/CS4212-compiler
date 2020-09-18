@FunctionalInterface
public interface ErrorLocationMessageCallback {
	public void apply(int left_line, int left_col, int right_line, int right_col);
}