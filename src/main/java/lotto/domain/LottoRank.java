package lotto.domain;

public enum LottoRank {

    FIRST(2000000000, 6),
    SECOND(30000000, 5),
    THIRD(1500000, 5),
    FOURTH(50000, 4),
    FIFTH(5000, 3),
    FAIL(0, -1);

    private final int amount;
    private final int matchCount;

    LottoRank(int amount, int matchCount) {
        this.amount = amount;
        this.matchCount = matchCount;
    }

    public static LottoRank getRank(int matchCount, boolean isBonus) {
        for (LottoRank rank : LottoRank.values()) {
            if (rank.matchCount != matchCount) {
                continue;
            }

            if (matchCount == LottoRank.SECOND.matchCount) {
                return getSecondOrThird(isBonus);
            }
            return rank;
        }
        return FAIL;
    }

    private static LottoRank getSecondOrThird(boolean isBonus) {
        if (isBonus) {
            return LottoRank.SECOND;
        }
        return LottoRank.THIRD;
    }
}