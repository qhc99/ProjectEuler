import static org.nathan.centralUtils.NumericUtils.*;

public final class Main{
    public static void main(String[] args){
        var t1 = System.nanoTime();
        var r = primeGen(200);
        System.out.println(r.get(r.size()-1));
        var t2 = System.nanoTime();
        System.out.printf("%fms%n",(t2-t1)/Math.pow(10,6));

    }
}
