package org.nathan.Euler;

import java.util.*;
import java.util.stream.Collectors;

public final class utils{
    /**
     * generate prime numbers less or equal limit
     * @param limit included upper limit
     * @return list of prime numbers less or equal limit
     */
    public static List<Integer> primeGen(int limit){
        return sieveOfEratosthenes(limit);
    }

    private static List<Integer> sieveOfEratosthenes(int limit){
        boolean[] prime = new boolean[limit + 1];
        Arrays.fill(prime, true);
        for(long p = 2; p * p <= limit; p++){
            if(prime[(int)p]){
                for(long i = p * 2; i <= limit; i += p){
                    prime[(int)i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for(int i = 2; i <= limit; i++){
            if(prime[i]){
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }


    /**
     * <pre>
     * example:28 -> 2,2,7
     * </pre>
     * @param num number
     * @param upLimit number's biggest prime factor
     * @return all prime factors of number
     */
    public static List<Integer> getAllPrimeFactors(int num, int upLimit){
        if(STATIC_PRIMES == null){
            STATIC_PRIMES = primeGen(upLimit);
            STATIC_LIMIT = upLimit;
        }
        else if(upLimit > STATIC_LIMIT){
            STATIC_PRIMES = primeGen(upLimit);
        }

        var primes = STATIC_PRIMES;
        List<Integer> l = new ArrayList<>(32);
        for(var prime : primes){
            while(num % prime == 0) {
                l.add(prime);
                num /= prime;
            }
            if(num == 1){
                break;
            }
        }
        if(num != 1){
            throw new RuntimeException("\"upLimit\" is not big enough");
        }
        return l;
    }

    private static List<Integer> STATIC_PRIMES;
    private static int STATIC_LIMIT;

    /**
     * <pre>
     * example:28 -> 1,2,4,7,14,28
     * </pre>
     * @param num number
     * @param upLimit number's biggest prime factor
     * @return all divisors of number
     */
    public static List<Integer> getAllDivisors(int num, int upLimit){
        List<Integer> primeFactors = getAllPrimeFactors(num, upLimit);
        List<Integer> l = new ArrayList<>(16);
        l.add(1);

        l.addAll(primeFactors.stream().distinct().collect(Collectors.toList()));
        for(int len = 2; len < primeFactors.size(); len++){
            for(int idx = 0; idx < primeFactors.size() - len + 1; idx++){
                recursiveGetDivisors(idx+1, primeFactors.get(idx), len -1, l, primeFactors);
            }
        }
        l.add(num);
        return l.stream().distinct().collect(Collectors.toList());
    }

    private static void recursiveGetDivisors(int idx, int mul, int len, List<Integer> l, List<Integer> primeFactors){
        if(len == 1){
            for(; idx < primeFactors.size(); idx++){
                l.add(mul * primeFactors.get(idx));
            }
        }
        else{
            for(;idx < primeFactors.size() - len + 1; idx++){
                recursiveGetDivisors(idx+1, primeFactors.get(idx)*mul,len-1,l,primeFactors);
            }
        }
    }
}
