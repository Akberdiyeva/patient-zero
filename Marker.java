import java.util.*;

public class Marker {

    public static int[] prefixFunction(String p) {
        int n = p.length();

        int[] pi = new int[n];
        int j = 0;

        for (int i = 1; i < n; i++) {

            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = pi[j - 1];
            }

            if (p.charAt(i) == p.charAt(j)) {
                j++;
            }

            pi[i] = j;
        }

        return pi;
    }

    public static int[] findAll(String text, String p) {
        if (p.isEmpty()) {
            return new int[0];
        }

        int[] pi = prefixFunction(p);

        int[] found = new int[Math.max(1, text.length())];

        int count = 0;
        int j = 0;

        for (int i = 0; i < text.length(); i++) {

            while (j > 0 && text.charAt(i) != p.charAt(j)) {
                j = pi[j - 1];
            }

            if (text.charAt(i) == p.charAt(j)) {
                j++;
            }

            if (j == p.length()) {

                found[count++] = i - p.length() + 1;

                j = pi[j - 1];
            }
        }

        return Arrays.copyOf(found, count);
    }

    public static int sharedWords(String a, String b, int k) {

        if (k <= 0 || a.length() < k || b.length() < k) {
            return 0;
        }

        final long base = 5L;
        final long mod = 1_000_000_007L;

        long highestPower = 1L;

        for (int i = 1; i < k; i++) {
            highestPower = (highestPower * base) % mod;
        }

        Map<Long, List<Integer>> positionsByHash =
                new HashMap<>();

        long hashB = 0;

        for (int i = 0; i < k; i++) {
            hashB = (hashB * base + value(b.charAt(i))) % mod;
        }

        positionsByHash
                .computeIfAbsent(hashB, x -> new ArrayList<>())
                .add(0);

        for (int start = 1; start <= b.length() - k; start++) {

            hashB = roll(
                    hashB,
                    b.charAt(start - 1),
                    b.charAt(start + k - 1),
                    highestPower,
                    base,
                    mod
            );

            positionsByHash
                    .computeIfAbsent(hashB, x -> new ArrayList<>())
                    .add(start);
        }

        long hashA = 0;

        for (int i = 0; i < k; i++) {
            hashA = (hashA * base + value(a.charAt(i))) % mod;
        }

        int answer = 0;

        for (int start = 0; start <= a.length() - k; start++) {

            List<Integer> candidates = positionsByHash.get(hashA);

            if (candidates != null) {

                for (int position : candidates) {

                    if (windowEquals(a, start, b, position, k)) {
                        answer++;
                        break;
                    }
                }
            }

            if (start < a.length() - k) {

                hashA = roll(
                        hashA,
                        a.charAt(start),
                        a.charAt(start + k),
                        highestPower,
                        base,
                        mod
                );
            }
        }

        return answer;
    }

    private static int value(char c) {

        switch (c) {
            case 'A': return 1;
            case 'C': return 2;
            case 'G': return 3;
            case 'T': return 4;
            default: return c + 1;
        }
    }

    private static long roll(
            long oldHash,
            char outgoing,
            char incoming,
            long highestPower,
            long base,
            long mod
    ) {

        long removed =
                (value(outgoing) * highestPower) % mod;

        long result = (oldHash - removed + mod) % mod;

        result = (result * base + value(incoming)) % mod;

        return result;
    }

    private static boolean windowEquals(
            String a,
            int i,
            String b,
            int j,
            int k
    ) {

        for (int x = 0; x < k; x++) {

            if (a.charAt(i + x) != b.charAt(j + x)) {
                return false;
            }
        }

        return true;
    }
}