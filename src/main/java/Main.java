
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {

    private static BigDecimal generate(List<BigDecimal> a) {
        var solver = new Object() {
            public BigDecimal total = BigDecimal.ZERO;
            private List<BigDecimal> minFutures = new ArrayList<>();
            private List<BigDecimal> maxFutures = new ArrayList<>();

            {
                for (int i = 0; i < a.size(); ++i) {
                    var t = getFutures(i, a);
                    minFutures.add(t.get(0));
                    maxFutures.add(t.get(1));
                }
            }

            private static List<BigDecimal> getFutures(int idx,
                    List<BigDecimal> arr) {

                var min = arr.stream().skip(idx).map((a)->{
                    return a.min(BigDecimal.ONE.subtract(a));
                }).reduce(BigDecimal::multiply).get();

                var max = arr.stream().skip(idx).map((a)->{
                    return a.max(BigDecimal.ONE.subtract(a));
                }).reduce(BigDecimal::multiply).get();
                return List.of(min, max);
            }

            private void generateTableRecursive(int current_idx,
                    BigDecimal prev_choice_1_result,
                    BigDecimal prev_choice_2_result, List<BigDecimal> arr) {
                if (current_idx == arr.size()) {
                    total = total.add(
                            prev_choice_1_result.max(prev_choice_2_result));
                } else {
                    var minFuture = minFutures.get(current_idx);
                    var maxFuture = maxFutures.get(current_idx);
                    var minChoice = prev_choice_1_result
                            .min(prev_choice_2_result);
                    var maxChoice = prev_choice_2_result
                            .max(prev_choice_1_result);

                    if (minChoice.multiply(maxFuture)
                            .compareTo(maxChoice.multiply(minFuture)) <= 0) {
                        total = total.add(maxChoice);
                        return;
                    }

                    generateTableRecursive(current_idx + 1,
                            prev_choice_1_result.multiply(arr.get(current_idx)),
                            prev_choice_2_result.multiply(BigDecimal.ONE
                                    .subtract(arr.get(current_idx))),
                            arr);

                    generateTableRecursive(current_idx + 1,
                            prev_choice_1_result.multiply(BigDecimal.ONE
                                    .subtract(arr.get(current_idx))),
                            prev_choice_2_result.multiply(arr.get(current_idx)),
                            arr);
                }
            }
        };

        solver.generateTableRecursive(1, a.get(0),
                BigDecimal.ONE.subtract(a.get(0)), a);
        return solver.total;
    }

    private static List<BigDecimal> bigDecimalArray(int start, int endInclusive,
            int step, int scale) {
        var t = IntStream.iterate(start, i -> i <= endInclusive, i -> i + step)
                .mapToObj(i -> {
                    return BigDecimal.valueOf(i)
                            .divide(BigDecimal.valueOf(scale));
                }).collect(Collectors.toList());
        return t;
    }

    public static void main(String[] args) {

        System.out.println(generate(bigDecimalArray(2, 8, 2, 10)));
        System.out.println(generate(bigDecimalArray(25, 54, 1, 100)));
    }
}
