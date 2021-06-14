import it.unimi.dsi.fastutil.ints.IntArrayList;

import static org.nathan.centralUtils.utils.NumericUtils.primeGen;

public final class Main{
  public static void main(String[] args){
    long t1 = System.nanoTime();
    IntArrayList res = new IntArrayList();
    for(int i = 0; i < 10; i++){
      res = primeGen(20_000_000);
    }
    long t2 = System.nanoTime();
    System.out.println(res.size());
    System.out.printf("%fms%n", (t2 - t1) / Math.pow(10, 6));
    System.out.printf("%fms%n", (t2 - t1) / Math.pow(10, 6) / 10.);
  }
}
