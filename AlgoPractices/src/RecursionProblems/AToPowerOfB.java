package RecursionProblems;

public class AToPowerOfB {
    public long power(int a, int b) {
        if (a == 0) return 0;
        if (b == 0) return 1;

        long half = power(a, b / 2);
        return b % 2 == 0 ? half * half : half * half * a;
    }
}
