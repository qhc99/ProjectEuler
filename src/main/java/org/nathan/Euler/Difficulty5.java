package org.nathan.Euler;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import static org.nathan.centralUtils.NumericUtils.*;

/**
 * n th solution less than 50
 */
public final class Difficulty5{

    @SuppressWarnings("unused")
    public static void answer1(){
        int acc = 0;
        for(int i = 3; i < 1000; i++){
            if(i % 3 == 0 || i % 5 == 0){
                acc += i;
            }
        }
        System.out.println(acc);
    }

    @SuppressWarnings("unused")
    public static void answer2(){
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        int lIdx = 0;
        int hIdx = 1;
        int r = 2;
        while(true) {
            int sum = l.get(lIdx) + l.get(hIdx);
            if(sum > 4_000_000){
                break;
            }

            if(sum % 2 == 0){
                r += sum;
            }
            l.add(sum);
            lIdx++;
            hIdx++;
        }
        System.out.println(r);
    }

    @SuppressWarnings("unused")
    public static void answer3(){
        long num = 600851475143L;
        List<Integer> primes = primeGen(7000);
        for(var prime : primes){
            while(num % prime == 0) {
                num /= prime;
            }
            if(num == 1){
                System.out.println(prime);
                break;
            }
        }
    }

    @SuppressWarnings("unused")
    public static void answer4(){
        int max = 999999;
        while(true) {
            List<Integer> primeFactors = getAllPrimeFactors(max, 999999);
            if(isMultipleOf_3_Digits(max, primeFactors)){
                System.out.println(max);
                break;
            }
            System.out.println(max);
            max = next_6_DigitPalindrome(max);
        }
    }

    private static int next_6_DigitPalindrome(int num){
        int sixth = num / 100_000;
        int fifth = (num - sixth * 100_000) / 10_000;
        int fourth = (num - sixth * 100_000 - fifth * 10_000) / 1_000;

        fourth--;
        if(fourth == -1){
            fourth = 9;
            fifth--;
            if(fifth == -1){
                fifth = 9;
                sixth--;
                if(sixth == 0){
                    throw new RuntimeException("no next 6 digit palindrome.");
                }
            }
        }

        return sixth * 100_000 + sixth +
                fifth * 10_000 + fifth * 10 +
                fourth * 1_000 + fourth * 100;
    }

    private static boolean isMultipleOf_3_Digits(int num, List<Integer> primeFactors){
        return recursiveSearch_3_DigitsFactors(num, 0, 1, primeFactors);
    }

    private static boolean recursiveSearch_3_DigitsFactors(int num, int idx, int accMul, List<Integer> primeFactors){
        if(isLargerThan_3_Digits(accMul)){
            return false;
        }
        for(int i = idx; i < primeFactors.size(); i++){
            int tempAccMul = accMul * primeFactors.get(i);
            if(is_3_Digits(tempAccMul) && is_3_Digits(num / tempAccMul)){
                return true;
            }
            if(recursiveSearch_3_DigitsFactors(num, i + 1, tempAccMul, primeFactors)){
                return true;
            }
        }
        return false;
    }

    private static boolean is_3_Digits(int num){
        return num >= 100 && num <= 999;
    }

    private static boolean isLargerThan_3_Digits(int num){
        return num >= 1000;
    }

    @SuppressWarnings("unused")
    public static void answer5(){
        System.out.println(2 * 3 * 2 * 5 * 7 * 2 * 3 * 11 * 13 * 2 * 17 * 19);
    }

    @SuppressWarnings("unused")
    public static void answer6(){
        long acc = 0;
        for(int i = 1; i <= 100; i++){
            acc += Math.pow(i, 2);
        }
        System.out.println(5050 * 5050 - acc);
    }

    @SuppressWarnings("unused")
    public static void answer7(){
        var l = primeGen(200_000);
        System.out.println(l.get(10_000));
    }

    @SuppressWarnings("unused")
    public static void answer8(){
        String s = "73167176531330624919225119674426574742355349194934" +
                "96983520312774506326239578318016984801869478851843" +
                "85861560789112949495459501737958331952853208805511" +
                "12540698747158523863050715693290963295227443043557" +
                "66896648950445244523161731856403098711121722383113" +
                "62229893423380308135336276614282806444486645238749" +
                "30358907296290491560440772390713810515859307960866" +
                "70172427121883998797908792274921901699720888093776" +
                "65727333001053367881220235421809751254540594752243" +
                "52584907711670556013604839586446706324415722155397" +
                "53697817977846174064955149290862569321978468622482" +
                "83972241375657056057490261407972968652414535100474" +
                "82166370484403199890008895243450658541227588666881" +
                "16427171479924442928230863465674813919123162824586" +
                "17866458359124566529476545682848912883142607690042" +
                "24219022671055626321111109370544217506941658960408" +
                "07198403850962455444362981230987879927244284909188" +
                "84580156166097919133875499200524063689912560717606" +
                "05886116467109405077541002256983155200055935729725" +
                "71636269561882670428252483600823257530420752963450";

        int[] ints = new int[s.length()];
        for(int i = 0; i < ints.length; i++){
            ints[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
        }

        long max = 0;
        for(int i = 0; i < ints.length - 12; i++){
            long mul = 1;
            for(int j = i; j < i + 13; j++){
                mul *= ints[j];
            }

            if(mul > max){
                max = mul;
            }
        }
        System.out.println(max);
    }

    @SuppressWarnings("unused")
    public static void answer9(){
        for(int a = 1; a < 1000; a++){
            for(int b = a + 1; b < 1000; b++){
                int c = 1000 - a - b;
                if(a >= c || b >= c){
                    break;
                }
                if(Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)){
                    System.out.printf("a:%d, b:%d, c:%d, prod:%d\n", a, b, c, a * b * c);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    public static void answer10(){
        List<Integer> l = primeGen(2_000_000);
        long sum = 0;
        for(var prime : l){
            sum += prime;
        }
        System.out.println(sum);
    }

    @SuppressWarnings("unused")
    public static void answer11(){
        int[][] grid = new int[][]{
                {8, 2, 22, 97, 38, 15, 0, 40, 0, 75, 4, 5, 7, 78, 52, 12, 50, 77, 91, 8},
                {49, 49, 99, 40, 17, 81, 18, 57, 60, 87, 17, 40, 98, 43, 69, 48, 4, 56, 62, 0},
                {81, 49, 31, 73, 55, 79, 14, 29, 93, 71, 40, 67, 53, 88, 30, 3, 49, 13, 36, 65},
                {52, 70, 95, 23, 4, 60, 11, 42, 69, 24, 68, 56, 1, 32, 56, 71, 37, 2, 36, 91},
                {22, 31, 16, 71, 51, 67, 63, 89, 41, 92, 36, 54, 22, 40, 40, 28, 66, 33, 13, 80},
                {24, 47, 32, 60, 99, 3, 45, 2, 44, 75, 33, 53, 78, 36, 84, 20, 35, 17, 12, 50},
                {32, 98, 81, 28, 64, 23, 67, 10, 26, 38, 40, 67, 59, 54, 70, 66, 18, 38, 64, 70},
                {67, 26, 20, 68, 2, 62, 12, 20, 95, 63, 94, 39, 63, 8, 40, 91, 66, 49, 94, 21},
                {24, 55, 58, 5, 66, 73, 99, 26, 97, 17, 78, 78, 96, 83, 14, 88, 34, 89, 63, 72},
                {21, 36, 23, 9, 75, 0, 76, 44, 20, 45, 35, 14, 0, 61, 33, 97, 34, 31, 33, 95},
                {78, 17, 53, 28, 22, 75, 31, 67, 15, 94, 3, 80, 4, 62, 16, 14, 9, 53, 56, 92},
                {16, 39, 5, 42, 96, 35, 31, 47, 55, 58, 88, 24, 0, 17, 54, 24, 36, 29, 85, 57},
                {86, 56, 0, 48, 35, 71, 89, 7, 5, 44, 44, 37, 44, 60, 21, 58, 51, 54, 17, 58},
                {19, 80, 81, 68, 5, 94, 47, 69, 28, 73, 92, 13, 86, 52, 17, 77, 4, 89, 55, 40},
                {4, 52, 8, 83, 97, 35, 99, 16, 7, 97, 57, 32, 16, 26, 26, 79, 33, 27, 98, 66},
                {88, 36, 68, 87, 57, 62, 20, 72, 3, 46, 33, 67, 46, 55, 12, 32, 63, 93, 53, 69},
                {4, 42, 16, 73, 38, 25, 39, 11, 24, 94, 72, 18, 8, 46, 29, 32, 40, 62, 76, 36},
                {20, 69, 36, 41, 72, 30, 23, 88, 34, 62, 99, 69, 82, 67, 59, 85, 74, 4, 36, 16},
                {20, 73, 35, 29, 78, 31, 90, 1, 74, 31, 49, 71, 48, 86, 81, 16, 23, 57, 5, 54},
                {1, 70, 54, 71, 83, 51, 54, 69, 16, 92, 33, 48, 61, 43, 52, 1, 89, 19, 67, 48},
        };
        int max = 0;
        for(int i = 0; i < 20; i++){
            for(int j = 0; j <= 16; j++){
                int mul = grid[i][j] * grid[i][j + 1] * grid[i][j + 2] * grid[i][j + 3];
                if(mul > max){
                    max = mul;
                }
            }
        }

        for(int i = 0; i <= 16; i++){
            for(int j = 0; j < 20; j++){
                int mul = grid[i][j] * grid[i + 1][j] * grid[i + 2][j] * grid[i + 3][j];
                if(mul > max){
                    max = mul;
                }
            }
        }

        for(int i = 0; i <= 16; i++){
            for(int j = 0; j <= 16; j++){
                int mul = grid[i][j] * grid[i + 1][j + 1] * grid[i + 2][j + 2] * grid[i + 3][j + 3];
                if(mul > max){
                    max = mul;
                }
            }
        }

        for(int i = 0; i <= 16; i++){
            for(int j = 3; j < 20; j++){
                int mul = grid[i][j] * grid[i + 1][j - 1] * grid[i + 2][j - 2] * grid[i + 3][j - 3];
                if(mul > max){
                    max = mul;
                }
            }
        }

        System.out.println(max);
    }

    @SuppressWarnings("unused")
    public static void answer12(){
        int triNum = 0;
        for(int i = 1; i < 100_000; i++){
            triNum += i;
            var l = getAllDivisors(triNum, 100_000);
            if(l.size() > 500){
                System.out.println(triNum);
                break;
            }
        }
    }

    @SuppressWarnings("unused")
    public static void answer13(){
        String data = "37107287533902102798797998220837590246510135740250\n" +
                "46376937677490009712648124896970078050417018260538\n" +
                "74324986199524741059474233309513058123726617309629\n" +
                "91942213363574161572522430563301811072406154908250\n" +
                "23067588207539346171171980310421047513778063246676\n" +
                "89261670696623633820136378418383684178734361726757\n" +
                "28112879812849979408065481931592621691275889832738\n" +
                "44274228917432520321923589422876796487670272189318\n" +
                "47451445736001306439091167216856844588711603153276\n" +
                "70386486105843025439939619828917593665686757934951\n" +
                "62176457141856560629502157223196586755079324193331\n" +
                "64906352462741904929101432445813822663347944758178\n" +
                "92575867718337217661963751590579239728245598838407\n" +
                "58203565325359399008402633568948830189458628227828\n" +
                "80181199384826282014278194139940567587151170094390\n" +
                "35398664372827112653829987240784473053190104293586\n" +
                "86515506006295864861532075273371959191420517255829\n" +
                "71693888707715466499115593487603532921714970056938\n" +
                "54370070576826684624621495650076471787294438377604\n" +
                "53282654108756828443191190634694037855217779295145\n" +
                "36123272525000296071075082563815656710885258350721\n" +
                "45876576172410976447339110607218265236877223636045\n" +
                "17423706905851860660448207621209813287860733969412\n" +
                "81142660418086830619328460811191061556940512689692\n" +
                "51934325451728388641918047049293215058642563049483\n" +
                "62467221648435076201727918039944693004732956340691\n" +
                "15732444386908125794514089057706229429197107928209\n" +
                "55037687525678773091862540744969844508330393682126\n" +
                "18336384825330154686196124348767681297534375946515\n" +
                "80386287592878490201521685554828717201219257766954\n" +
                "78182833757993103614740356856449095527097864797581\n" +
                "16726320100436897842553539920931837441497806860984\n" +
                "48403098129077791799088218795327364475675590848030\n" +
                "87086987551392711854517078544161852424320693150332\n" +
                "59959406895756536782107074926966537676326235447210\n" +
                "69793950679652694742597709739166693763042633987085\n" +
                "41052684708299085211399427365734116182760315001271\n" +
                "65378607361501080857009149939512557028198746004375\n" +
                "35829035317434717326932123578154982629742552737307\n" +
                "94953759765105305946966067683156574377167401875275\n" +
                "88902802571733229619176668713819931811048770190271\n" +
                "25267680276078003013678680992525463401061632866526\n" +
                "36270218540497705585629946580636237993140746255962\n" +
                "24074486908231174977792365466257246923322810917141\n" +
                "91430288197103288597806669760892938638285025333403\n" +
                "34413065578016127815921815005561868836468420090470\n" +
                "23053081172816430487623791969842487255036638784583\n" +
                "11487696932154902810424020138335124462181441773470\n" +
                "63783299490636259666498587618221225225512486764533\n" +
                "67720186971698544312419572409913959008952310058822\n" +
                "95548255300263520781532296796249481641953868218774\n" +
                "76085327132285723110424803456124867697064507995236\n" +
                "37774242535411291684276865538926205024910326572967\n" +
                "23701913275725675285653248258265463092207058596522\n" +
                "29798860272258331913126375147341994889534765745501\n" +
                "18495701454879288984856827726077713721403798879715\n" +
                "38298203783031473527721580348144513491373226651381\n" +
                "34829543829199918180278916522431027392251122869539\n" +
                "40957953066405232632538044100059654939159879593635\n" +
                "29746152185502371307642255121183693803580388584903\n" +
                "41698116222072977186158236678424689157993532961922\n" +
                "62467957194401269043877107275048102390895523597457\n" +
                "23189706772547915061505504953922979530901129967519\n" +
                "86188088225875314529584099251203829009407770775672\n" +
                "11306739708304724483816533873502340845647058077308\n" +
                "82959174767140363198008187129011875491310547126581\n" +
                "97623331044818386269515456334926366572897563400500\n" +
                "42846280183517070527831839425882145521227251250327\n" +
                "55121603546981200581762165212827652751691296897789\n" +
                "32238195734329339946437501907836945765883352399886\n" +
                "75506164965184775180738168837861091527357929701337\n" +
                "62177842752192623401942399639168044983993173312731\n" +
                "32924185707147349566916674687634660915035914677504\n" +
                "99518671430235219628894890102423325116913619626622\n" +
                "73267460800591547471830798392868535206946944540724\n" +
                "76841822524674417161514036427982273348055556214818\n" +
                "97142617910342598647204516893989422179826088076852\n" +
                "87783646182799346313767754307809363333018982642090\n" +
                "10848802521674670883215120185883543223812876952786\n" +
                "71329612474782464538636993009049310363619763878039\n" +
                "62184073572399794223406235393808339651327408011116\n" +
                "66627891981488087797941876876144230030984490851411\n" +
                "60661826293682836764744779239180335110989069790714\n" +
                "85786944089552990653640447425576083659976645795096\n" +
                "66024396409905389607120198219976047599490197230297\n" +
                "64913982680032973156037120041377903785566085089252\n" +
                "16730939319872750275468906903707539413042652315011\n" +
                "94809377245048795150954100921645863754710598436791\n" +
                "78639167021187492431995700641917969777599028300699\n" +
                "15368713711936614952811305876380278410754449733078\n" +
                "40789923115535562561142322423255033685442488917353\n" +
                "44889911501440648020369068063960672322193204149535\n" +
                "41503128880339536053299340368006977710650566631954\n" +
                "81234880673210146739058568557934581403627822703280\n" +
                "82616570773948327592232845941706525094512325230608\n" +
                "22918802058777319719839450180888072429661980811197\n" +
                "77158542502016545090413245809786882778948721859617\n" +
                "72107838435069186155435662884062257473692284509516\n" +
                "20849603980134001723930671666823555245252804609722\n" +
                "53503534226472524250874054075591789781264330331690";

        String[] strings = data.split("\\n");
        BigInteger[] ints = new BigInteger[strings.length];
        for(int i = 0; i < ints.length; i++){
            ints[i] = new BigInteger(strings[i]);
        }

        var sum = ints[0];
        for(int i = 1; i < ints.length; i++){
            sum = sum.add(ints[i]);
        }
        System.out.println(sum);
    }

    @SuppressWarnings("unused")
    public static void answer14(){
        long num = 1_000_000;
        long target = num;
        long max_len = 0;
        for(; num >= 1; num--){
            long len = lengthOfCollatzSequence(num);
            if(len > max_len){
                max_len = len;
                target = num;
            }
        }
        System.out.println(target);
    }

    private static long lengthOfCollatzSequence(long num){
        long count = 1;
        while(num != 1) {
            if(num % 2 == 0){
                num = num / 2;
            }
            else{
                num = 3 * num + 1;
            }
            count++;
        }
        return count;
    }

    @SuppressWarnings("unused")
    public static void answer15(){
        for(int i = 2; i <= 20; i++){
            System.out.printf("%d: %d\n", i, searchGrid(i));
        }
    }

    private static long searchGrid(int size){
        long[][] store = new long[size + 1][size + 1];
        store[0][0] = 1;

        for(int sum = 1; sum <= size * 2; sum++){
            for(int i = 0; i <= sum && i <= size; i++){
                int j = sum - i;
                if(j < 0 || j > size){
                    continue;
                }

                if(i - 1 >= 0){
                    store[i][j] += store[i - 1][j];
                }

                if(j - 1 >= 0){
                    store[i][j] += store[i][j - 1];
                }
            }
        }

        return store[size][size];
    }

    @SuppressWarnings("unused")
    public static void answer16(){
        BigInteger mul = new BigInteger("1");
        BigInteger factor = new BigInteger("33554432");
        for(int i = 0; i < 40; i++){
            mul = mul.multiply(factor);
        }
        String s = mul.toString();
        long sum = 0;
        for(int i = 0; i < s.length(); i++){
            sum += s.charAt(i) - '0';
        }
        System.out.println(sum);
    }

    @SuppressWarnings("unused")
    public static void answer17(){
        int sum = 0;
        for(int i = 1; i <= 1000; i++){
            var s = getNumberEnglishFormat(i);
            sum += s.replace(" ", "").length();
        }
        System.out.println(sum);
    }

    private static String getNumberEnglishFormat(int num){
        if(num <= 0){
            throw new IllegalArgumentException();
        }
        String[] numbers = new String[]{
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
        };

        String[] multiple_10 = new String[]{
                "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
        };

        String HUNDRED = "hundred";
        String THOUSAND = "thousand";
        if(num < 20){
            return numbers[num - 1];
        }
        else if(num < 100){
            int tenth = num / 10;
            int one = num - tenth * 10;
            if(one != 0){
                return multiple_10[tenth - 1] + " " + numbers[one - 1];
            }
            else{
                return multiple_10[tenth - 1];
            }
        }
        else{
            if(num == 100){
                return "one " + HUNDRED;
            }

            if(num == 1000){
                return "one " + THOUSAND;
            }

            int hundredth = num / 100;
            int tenth = (num - hundredth * 100) / 10;
            int one = num - hundredth * 100 - tenth * 10;

            if(one == 0){
                if(tenth == 0){
                    return numbers[hundredth - 1] + " " + HUNDRED;
                }
                else{
                    return numbers[hundredth - 1] + " " + HUNDRED + " and " + multiple_10[tenth - 1];
                }
            }
            else{
                if(tenth == 0){
                    return numbers[hundredth - 1] + " " + HUNDRED + " and " + numbers[one - 1];
                }
                else{
                    if(tenth == 1){
                        return numbers[hundredth - 1] + " " + HUNDRED + " and " + numbers[tenth * 10 + one - 1];
                    }
                    else{
                        return numbers[hundredth - 1] + " " + HUNDRED + " and " + multiple_10[tenth - 1] + " " + numbers[one - 1];
                    }
                }
            }
        }
    }

    @SuppressWarnings("unused")
    public static void answer18(){
        String[] data = ("75\n" +
                "95 64\n" +
                "17 47 82\n" +
                "18 35 87 10\n" +
                "20 04 82 47 65\n" +
                "19 01 23 75 03 34\n" +
                "88 02 77 73 07 63 67\n" +
                "99 65 04 28 06 16 70 92\n" +
                "41 41 26 56 83 40 80 70 33\n" +
                "41 48 72 33 47 32 37 16 94 29\n" +
                "53 71 44 65 25 43 91 52 97 51 14\n" +
                "70 11 33 28 77 73 17 78 39 68 17 57\n" +
                "91 71 52 38 17 14 91 43 58 50 27 29 48\n" +
                "63 66 04 68 89 53 67 30 73 16 69 87 40 31\n" +
                "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23").split("\\n");

        String[][] dataMatrix = new String[data.length][];
        for(int i = 0; i < data.length; i++){
            dataMatrix[i] = data[i].split(" ");
        }

        String[][] transformedMatrix = new String[data.length][];
        for(int i = 0; i < data.length; i++){
            transformedMatrix[i] = new String[data.length - i];
            int idx = 0;
            for(int row = 0; row < data.length; row++){
                if(dataMatrix[row].length - i - 1 >= 0){
                    transformedMatrix[i][idx++] = dataMatrix[row][dataMatrix[row].length - i - 1];
                }
            }
        }

        long[][] sums = new long[data.length][];
        for(int i = 0; i < data.length; i++){
            sums[i] = new long[data.length - i];
            for(int c = 0; c < data.length - i; c++){
                sums[i][c] = Integer.parseInt(transformedMatrix[i][c]);
            }
        }

        for(int sum = 1; sum < data.length; sum++){
            for(int r = 0; r < data.length; r++){
                int c = sum - r;
                if(c < 0 || c >= data.length){
                    break;
                }

                if(c - 1 >= 0){
                    if(r - 1 >= 0){
                        sums[r][c] += Math.max(sums[r][c-1], sums[r-1][c]);
                    }
                    else{
                        sums[r][c] += sums[r][c-1];
                    }
                }
                else{
                    sums[r][c] += sums[r-1][c];
                }
            }
        }

        long max = 0;
        for(int i = 0; i < data.length; i++){
            long t = sums[i][sums[i].length-1];
            if(t > max){
                max = t;
            }
        }

        System.out.println(max);
    }

    @SuppressWarnings("unused")
    public static void answer19(){
        int sum = 0;
        for(int year = 1901; year <= 2000; year++){
            for(int month = 1; month <= 12; month++){
                LocalDate date = LocalDate.of(year,month,1);
                if(date.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }

    @SuppressWarnings("unused")
    public static void answer20(){
        var a = new BigInteger("87178291200");
        var b = new BigInteger("296541907200");
        var c = new BigInteger("318073392000");
        var d = new BigInteger("63606090240");
        var n = a.multiply(b).multiply(c).multiply(d);
        for(int i = 39; i <= 100; i++){
            n = n.multiply(new BigInteger(Integer.toString(i)));
        }
        var s = n.toString();
        long sum = 0;
        for(int i = 0; i < s.length(); i++){
            sum += Integer.parseInt(String.valueOf(s.charAt(i)));
        }
        System.out.println(sum);
    }
}
