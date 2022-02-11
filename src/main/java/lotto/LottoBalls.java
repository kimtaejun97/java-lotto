package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoNumber;

public class LottoBalls {

    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;
    private static final int NUMBER_OF_LOTTO_BALL = 6;
    private static final String OUT_OF_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE = String.format(
            "로또 번호는 %d부터 %d 까지 입니다.", LOTTO_START_NUMBER, LOTTO_END_NUMBER);
    private static final int DIFFERENCE_FROM_INDEX = 1;

    private static final List<LottoNumber> BALLS = new ArrayList<>(
            IntStream.range(LOTTO_START_NUMBER, LOTTO_END_NUMBER + 1)
                    .mapToObj(LottoNumber::new)
                    .collect(Collectors.toList()));

    private LottoBalls() {}

    public static void shuffle() {
        Collections.shuffle(BALLS);
    }

    public static Set<LottoNumber> getRandomLottoNumber() {
        return new TreeSet<>(BALLS.subList(0, NUMBER_OF_LOTTO_BALL));
    }

    public static Set<LottoNumber> of(final List<Integer> numbers) {
        return numbers.stream()
                .map(n -> {
                    validateLottoNumberRange(n);
                    return BALLS.get(n - DIFFERENCE_FROM_INDEX);
                })
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private static void validateLottoNumberRange(final int value) {
        if (isNotLottoNumberRange(value)) {
            throw new IllegalArgumentException(OUT_OF_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    private static boolean isNotLottoNumberRange(final int value) {
        return value < LOTTO_START_NUMBER || value > LOTTO_END_NUMBER;
    }

    public static List<LottoNumber> get() {
        return BALLS;
    }
}
