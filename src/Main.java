public class Main extends Output<int[]> {
  public static void main(String[] args) {
    Object[][] tests = {
        {5, new int[] {3, 4, 4, 6, 1, 4, 4}, /* Expected output: */ new int[] {5}}
    };
    new Main().process(tests, int[].class);
  }

  @Override
  protected int[] solution(Object[] inputs) {
    int N = (int) inputs[0];
    int[] A = (int[]) inputs[1];

    // Implement

    return new int[] {4};
  }
}
