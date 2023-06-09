package dev.akuniutka.cbrratesbot.repository;

import dev.akuniutka.cbrratesbot.dto.FilterCriteria;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(ReportRepository.class)
class ReportRepositoryTest {
    private static List<Long> chatIds;
    private static List<LocalDate> datesFrom;
    private static List<LocalDate> datesTo;
    private static List<BigDecimal> amountsFrom;
    private static List<BigDecimal> amountsTo;

    @Autowired
    private ReportRepository reportRepository;

    @BeforeAll
    static void prepareTestData() {
        chatIds = Arrays.asList(null, 123456789L);
        amountsFrom = Arrays.asList(null, BigDecimal.valueOf(100.00));
        amountsTo = Arrays.asList(null, BigDecimal.valueOf(500.00));
        datesFrom = Arrays.asList(null, LocalDate.of(2023,1,1));
        datesTo = Arrays.asList(null, LocalDate.of(2023,6,1));
    }

    @Test
    void testGetIncomesCount() {
        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    for (BigDecimal amountFrom : amountsFrom) {
                        for (BigDecimal amountTo : amountsTo) {
                            FilterCriteria filter = new FilterCriteria(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            Long count = 3L;
                            count += chatId == null ? 2 : 0;
                            count += amountFrom == null ? 2 : 0;
                            count += amountTo == null ? 2 : 0;
                            count += dateFrom == null ? 2 : 0;
                            count += dateTo == null ? 2 : 0;

                            assertEquals(count, reportRepository.getIncomesCount(filter));
                        }
                    }
                }
            }
        }
    }

    @Test
    void testGetIncomesSum() {
        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    for (BigDecimal amountFrom : amountsFrom) {
                        for (BigDecimal amountTo : amountsTo) {
                            FilterCriteria filter = new FilterCriteria(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            BigDecimal sum = BigDecimal.valueOf(700.00).setScale(2, RoundingMode.HALF_UP);
                            sum = sum.add(BigDecimal.valueOf(chatId == null ? 600.00 : 0.0));
                            sum = sum.add(BigDecimal.valueOf(amountFrom == null ? 125.00 : 0.0));
                            sum = sum.add(BigDecimal.valueOf(amountTo == null ? 1500.00 : 0.0));
                            sum = sum.add(BigDecimal.valueOf(dateFrom == null ? 600.00 : 0.0));
                            sum = sum.add(BigDecimal.valueOf(dateTo == null ? 600.00 : 0.0));

                            assertEquals(sum, reportRepository.getIncomesSum(filter));
                        }
                    }
                }
            }
        }
    }

    @Test
    void testGetExpensesCount() {
        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    for (BigDecimal amountFrom : amountsFrom) {
                        for (BigDecimal amountTo : amountsTo) {
                            FilterCriteria filter = new FilterCriteria(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            Long count = 3L;
                            count += chatId == null ? 2 : 0;
                            count += amountFrom == null ? 2 : 0;
                            count += amountTo == null ? 2 : 0;
                            count += dateFrom == null ? 2 : 0;
                            count += dateTo == null ? 2 : 0;

                            assertEquals(count, reportRepository.getExpensesCount(filter));
                        }
                    }
                }
            }
        }
    }

    @Test
    void testGetExpensesSum() {
        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    for (BigDecimal amountFrom : amountsFrom) {
                        for (BigDecimal amountTo : amountsTo) {
                            FilterCriteria filter = new FilterCriteria(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            BigDecimal sum = BigDecimal.valueOf(700.00).setScale(2, RoundingMode.HALF_UP);
                            sum = sum.add(BigDecimal.valueOf(chatId == null ? 600.00 : 0.0));
                            sum = sum.add(BigDecimal.valueOf(amountFrom == null ? 125.00 : 0.0));
                            sum = sum.add(BigDecimal.valueOf(amountTo == null ? 1500.00 : 0.0));
                            sum = sum.add(BigDecimal.valueOf(dateFrom == null ? 600.00 : 0.0));
                            sum = sum.add(BigDecimal.valueOf(dateTo == null ? 600.00 : 0.0));

                            assertEquals(sum, reportRepository.getExpensesSum(filter));
                        }
                    }
                }
            }
        }
    }
}