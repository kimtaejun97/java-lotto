package lotto.domain.statistics;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.statistics.dto.ResultDto;
import lotto.domain.lottorank.LottoRank;
import lotto.domain.lottorank.LottoRanks;
import lotto.domain.statistics.vo.RankCounts;

public class Statistics {

    private final Map<LottoRank, Integer> rankCounts;
    private final double profitRate;

    public Statistics(final LottoRanks lottoRanks) {
        this.rankCounts = rankStatistics(lottoRanks.get());
        this.profitRate = profitStatistics(lottoRanks.getAmounts());
    }

    private Map<LottoRank, Integer> rankStatistics(final List<LottoRank> lottoRanks) {
        return Arrays.stream(LottoRank.values()).collect(Collectors.toMap(
                i1 -> i1,  i1 -> Collections.frequency(lottoRanks, i1), (e1,e2) -> e1 ,LinkedHashMap::new));
    }

    private double profitStatistics(final List<Integer> amounts) {
        double profit = amounts.stream().mapToDouble(a -> a).sum();
        double budget = (amounts.size() * 1000);

        return  profit / budget;
    }

    public ResultDto getResult() {
        return new ResultDto(new RankCounts(this.rankCounts), this.profitRate);
    }
}
