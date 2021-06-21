import org.nathan.centralUtils.utils.NumericUtils;

import java.math.BigDecimal;
import java.util.List;

public final class Main{
  public static void main(String[] args){
    long t1 = System.nanoTime();
    List<Integer> res = null;
    int iter = 10_000;
    for(int i = 0; i < iter; i++){
      res = NumericUtils.primeGen(2_000);
    }
    long t2 = System.nanoTime();
    System.out.println(res.size());
    System.out.printf("%fms%n", (t2 - t1) / Math.pow(10, 6));
    System.out.printf("%fus%n", (t2 - t1) / Math.pow(10, 3) / iter);
  }
}
