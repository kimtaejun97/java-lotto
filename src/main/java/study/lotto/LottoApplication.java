package study.lotto;

import study.lotto.controller.LottoGame;
import study.lotto.domain.LottoResult;
import study.lotto.service.Lottos;
import study.lotto.view.InputView;
import study.lotto.view.ResultView;
import study.lotto.view.dto.RequestMoney;
import study.lotto.view.dto.RequestWinningNumber;

public class LottoApplication {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        LottoGame lottoGame = new LottoGame();

        // 금액 요청
        RequestMoney requestMoney = InputView.requestMoney();
        resultView.printBuyCount(requestMoney);
        Lottos lottos = lottoGame.buyLotto(requestMoney);

        // 중간 보기
        resultView.printBuyLottos(lottos);

        // 당첨 번호 입력
        RequestWinningNumber winningNumber = inputView.requestWinningNumber();
        LottoResult lottoResult = new LottoResult(requestMoney, winningNumber, lottos);
        // 결과 보기
        resultView.printStatics(lottoResult);
        resultView.printRate(lottoResult);
    }
}