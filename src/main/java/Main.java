import org.nathan.Euler.*;

public final class Main{
    public static void main(String[] args){
        var t1 = System.nanoTime();
        var r = Utils.primeGen(100000000);
        System.out.println(r.get(1000));
        var t2 = System.nanoTime();
        System.out.println((t2-t1)/Math.pow(10,6));
    }
}
