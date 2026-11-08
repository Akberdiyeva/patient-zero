public class Strains {

    public static int editDistance(String a, String b) {
        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                int replaceCost =
                        a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;

                int replace = dp[i - 1][j - 1] + replaceCost;
                int delete = dp[i - 1][j] + 1;
                int insert = dp[i][j - 1] + 1;

                dp[i][j] = Math.min(
                        replace,
                        Math.min(delete, insert)
                );
            }
        }

        return dp[n][m];
    }

    public static int lcsLength(String a, String b) {
        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(
                            dp[i - 1][j],
                            dp[i][j - 1]
                    );
                }
            }
        }

        return dp[n][m];
    }
}