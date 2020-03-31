package lotto.model;

import lotto.model.wrapper.LottoMatchCount;
import lotto.model.wrapper.LottoNumber;

import java.util.Set;

public class WinningLottoTicket extends LottoTicket {

    private final LottoNumber bonusNumber;

    private WinningLottoTicket(final Set<LottoNumber> numbers, final LottoNumber bonusNumber) {
        super(numbers);
        this.bonusNumber = bonusNumber;
    }

    public static WinningLottoTicket newInstance(final Set<LottoNumber> numbers, final LottoNumber bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("bonus number must be distinct.");
        }

        return new WinningLottoTicket(numbers, bonusNumber);
    }

    public LottoResult checkLottoTicket(Set<LottoNumber> lottoNumbers) {
        long count = numbers.stream()
                .filter(lottoNumbers::contains)
                .count();
        boolean matchBonusNumber = lottoNumbers.contains(bonusNumber);

        return LottoResult.of(LottoMatchCount.create(Math.toIntExact(count), matchBonusNumber));
    }
}
