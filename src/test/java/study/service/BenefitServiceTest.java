package study.service;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.enums.MenuItem;
import christmas.service.BenefitService;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BenefitServiceTest {

    private BenefitService benefitService;
    private Order order;
    private Date date;

    @BeforeEach
    void setUp() {
        date = new Date(15);
        benefitService = BenefitService.createBenefitService(date);

        order = new Order(Map.of(
                MenuItem.T_BONE_STEAK, 1,
                MenuItem.SEAFOOD_PASTA, 2,
                MenuItem.CHOCOLATE_CAKE, 2
        ));
    }

    @Test
    @DisplayName("선물 리스트 정상 반환")
    void getGifts_선물_리스트_정상_반환() {
        Map<String, Integer> gifts = benefitService.getGifts(order);

        assertThat(gifts).containsExactlyInAnyOrderEntriesOf(Map.of("샴페인", 1));
    }

    @Test
    @DisplayName("혜택 맵 정상 반환")
    void getBenefits_혜택_맵_정상_반환() {
        Map<String, Integer> benefits = benefitService.getBenefits(order);

        assertThat(benefits).containsExactlyInAnyOrderEntriesOf(
                Map.of("크리스마스 디데이 할인", 2400, "주말 할인", 2023 * 3, "증정 이벤트", 25_000));
    }

    @Test
    @DisplayName("총 혜택 금액 계산")
    void getTotalBenefitAmount_총_혜택_금액_계산() {
        int totalBenefitAmount = benefitService.getTotalBenefitAmount(order);

        assertThat(totalBenefitAmount).isEqualTo(2400 + 2023 * 3 + 25_000);
    }
}
