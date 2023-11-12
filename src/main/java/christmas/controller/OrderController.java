package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.service.BenefitService;
import christmas.service.ParserService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class OrderController {
    private static final String DATE_INPUT_ERROR_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private final InputView inputView;
    private final OutputView outputView;

    public OrderController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Date date = getDate();
        Order order = getOrder();

        BenefitService orderService = BenefitService.createBenefitService(date);
    }

    private Date getDate() {
        while (true) {
            try {
                return new Date(inputView.readDate());
            } catch (IllegalArgumentException e) {
                outputView.printError(DATE_INPUT_ERROR_MESSAGE);
            }
        }
    }

    private Order getOrder() {
        ParserService parserService = new ParserService();

        while (true) {
            try {
                String line = inputView.readOrderLine();
                return new Order(parserService.parseOrder(line));
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}