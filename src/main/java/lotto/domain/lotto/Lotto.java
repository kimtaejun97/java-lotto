package lotto.domain.lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.lottonumber.LottoBalls;
import lotto.domain.lottonumber.LottoNumber;
import lotto.utils.Parser;

public class Lotto {

    private static final String DELIMITER_COMMA = ",";
    private static final int NUMBER_OF_LOTTO_BALL = 6;
    private static final String INVALID_INPUT_FORMAT_EXCEPTION_MESSAGE = "구분자와 숫자만으로 이루어져야 합니다.";
    private static final String INVALID_NUMBER_COUNT_EXCEPTION_MESSAGE = "당첨 번호는 중복되지 않은 6개의 숫자 입니다.";

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(final Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public Lotto(final String inputLottoNumbers) {
        validateInputFormat(inputLottoNumbers);
        final Set<LottoNumber> lottoNumbers = convertToLottoNumbers(inputLottoNumbers);

        validateLottoNumberSize(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    private void validateInputFormat(final String numbers) {
        if (!numbers.matches(getValidateRegex())) {
            throw new IllegalArgumentException(INVALID_INPUT_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private String getValidateRegex() {
        return String.format("[0-9%s]+$", DELIMITER_COMMA);
    }

    private Set<LottoNumber> convertToLottoNumbers(final String winningNumbers) {
        final List<Integer> numbers = Arrays.stream(winningNumbers.split(DELIMITER_COMMA))
                .map(Parser::parseInt
                )
                .collect(Collectors.toList());

        return LottoBalls.of(numbers);
    }

    private void validateLottoNumberSize(final Set<LottoNumber> winningNumbers) {
        if (winningNumbers.size() != NUMBER_OF_LOTTO_BALL) {
            throw new IllegalArgumentException(INVALID_NUMBER_COUNT_EXCEPTION_MESSAGE);
        }
    }

    public int matchNumber(final Set<LottoNumber> winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public Set<LottoNumber> get() {
        return Collections.unmodifiableSet(this.lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
