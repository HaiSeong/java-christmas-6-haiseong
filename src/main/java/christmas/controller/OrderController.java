package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.service.BenefitService;
import christmas.service.ParserService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class OrderController {
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

        outputView.printPreviewEventBenefitsMessage();
        outputView.println();
        outputView.printOrderedMenuItems(order.toString());
        outputView.println();
        outputView.printTotalAmountBeforeDiscount(order.getTotalPrice());
        outputView.println();

        Map<String, Integer> gifts = orderService.getGifts(order);
        outputView.printGiftedItems(gifts);
        outputView.println();
        Map<String, Integer> benefits = orderService.getBenefits(order);
        outputView.printBenefitDetails(benefits);
        outputView.println();

        outputView.printTotalBenefitAmount(orderService.getTotalBenefitAmount(order));
        outputView.println();
        outputView.printExpectedPaymentAfterDiscount(order.getTotalPrice() - orderService.getTotalBenefitAmount(order));
        outputView.println();
        outputView.printDecemberEventBadge("준비중");
    }

    private Date getDate() {
        while (true) {
            try {
                return new Date(inputView.readDate());
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
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
