package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    private static final String INVALID_NUMBER_FORMAT_ERROR_MESSAGE = "숫자가 아닌 문자가 입력되었습니다.";

    public int readDate() {
        try {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            return Integer.parseInt(readLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    public String readOrderLine() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return readLine().trim();
    }

}
