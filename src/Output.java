import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Output<T> {
  protected void process(Object[][] tests, Class<T> outputClass) {
    for (Object[] test : tests) {
      Object[] input = Arrays.copyOfRange(test, 0, test.length - 1);
      T result = solution(input);
      T expected = outputClass.cast(test[test.length - 1]);
      System.out.format("\nInput: %-35s Expected: %-35s Output: %-35s%n",
          String.format("[%s]", Stream.of(input)
              .map(this::toString)
              .collect(Collectors.joining(", "))
          ),
          toString(expected),
          String.format("%s%s\u001B[0m",
              Objects.deepEquals(expected, result) ? "\u001B[32m" : "\u001B[31m",
              toString(result)
          )
      );
    }
  }

  private String toString(Object obj) {
    return obj.getClass().isArray() ? arrayToString(obj, obj.getClass().getComponentType()) :
        (obj instanceof String ? "\"" + obj + "\"" : obj.toString());
  }

  private String arrayToString(Object result, Class<?> componentClass) {
    List<String> elements = new ArrayList<>();
    if (componentClass.isPrimitive()) {
      int length = Array.getLength(result);
      for (int i = 0; i < length; i++) {
        Object obj = Array.get(result, i);
        elements.add(obj instanceof String ? "\"" + obj + "\"" : obj.toString());
      }
    } else {
      Object[] objects = (Object[]) result;
      for (Object obj : objects) {
        elements.add(obj instanceof String ? "\"" + obj + "\"" : obj.toString());
      }
    }
    return String.format("[%s]", String.join(", ", elements));
  }

  protected abstract T solution(Object[] inputs);
}
