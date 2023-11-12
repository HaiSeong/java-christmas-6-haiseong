package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.enums.EventBadge;
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
        BenefitService benefitService = BenefitService.createBenefitService(date);
        EventBadge eventBadge = benefitService.getEventBadge(order);

        outputView.printPreviewEventBenefitsMessage(date.getDay());
        outputView.printOrderedMenuItems(order.toString());
        outputView.printTotalAmountBeforeDiscount(order.getTotalPrice());

        outputView.printGiftedItems(benefitService.getGifts(order));
        outputView.printBenefitDetails(benefitService.getBenefits(order));

        outputView.printTotalBenefitAmount(benefitService.getTotalBenefitAmount(order));
        outputView.printExpectedPaymentAfterDiscount(benefitService.getExpectedPaymentAfterDiscount(order));
        outputView.printEventBadge(eventBadge.getBadgeName());
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
