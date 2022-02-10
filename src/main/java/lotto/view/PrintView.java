package lotto.view;

import lotto.domain.Lottos;
import lotto.domain.dto.ResultDto;
import lotto.domain.vo.RankCounts;

public class PrintView {

    private final static String RANK_SECOND = "SECOND";
    private final static int MIN_WINNING_MATCH_COUNT = 3;
    private final static String FAIL_MATCH_COUNT_RESULT = "0-2";
    private final static String RANK_SECOND_RESULT_FORMAT = "%s개 일치, 보너스 볼 일치(%d원) - %d개%n";
    private final static String NOT_RANK_SECOND_RESULT_FORMAT = "%s개 일치 (%d원) - %d개%n";

    private PrintView() {
    }

    public static void printNumberOfLotto(final int manualSize, int autoSize) {
        System.out.printf("수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", manualSize, autoSize);
    }

    public static void printLottoNumber(final Lottos lottos) {
        lottos.get().forEach(System.out::println);
    }

    public static void printResult(final ResultDto resultDto) {
        printRankCounts(resultDto.getNumberOfRanks());
        printProfitRate(resultDto.getProfitRate());
    }

    private static void printRankCounts(RankCounts rankCounts) {
        StringBuilder result = new StringBuilder();

        rankCounts.get().forEach((rank, count) ->
                result.append(String.format(getResultFormat(rank.name()),
                        getMatchCount(rank.getMatchCount()), rank.getAmount(), count)));

        System.out.print(result);
    }

    private static String getResultFormat(final String rank) {
        if (rank.equals(RANK_SECOND)) {
            return RANK_SECOND_RESULT_FORMAT;
        }
        return NOT_RANK_SECOND_RESULT_FORMAT;
    }

    private static String getMatchCount(int matchCount) {
        if (matchCount < MIN_WINNING_MATCH_COUNT) {
            return FAIL_MATCH_COUNT_RESULT;
        }
        return String.valueOf(matchCount);
    }

    private static void printProfitRate(final double profitRatio) {
        System.out.printf("총 수익률은 %.2f 입니다. (기준:1)\n", profitRatio);
    }
}
