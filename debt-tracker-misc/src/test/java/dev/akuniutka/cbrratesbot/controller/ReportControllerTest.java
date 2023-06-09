package dev.akuniutka.cbrratesbot.controller;

import dev.akuniutka.cbrratesbot.dto.FilterCriteria;
import dev.akuniutka.cbrratesbot.dto.SimpleFilterCriteria;
import dev.akuniutka.cbrratesbot.service.ReportService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportController.class)
class ReportControllerTest {
    private static final Random RANDOM = new Random();
    private static List<Long> chatIds;
    private static List<LocalDate> datesFrom;
    private static List<LocalDate> datesTo;
    private static List<BigDecimal> amountsFrom;
    private static List<BigDecimal> amountsTo;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReportService reportService;

    @BeforeAll
    static void prepareTestData() {
        chatIds = Arrays.asList(null, RANDOM.nextLong());
        datesFrom = Arrays.asList(null, getRandomDate());
        datesTo = Arrays.asList(null, getRandomDate());
        amountsFrom = Arrays.asList(null, getRandomBigDecimal());
        amountsTo = Arrays.asList(null, getRandomBigDecimal());
    }

    @Test
    void testGetCount() throws Exception {
        long count = RANDOM.nextInt(1000);
        Map<String, Long> expected = new HashMap<>();

        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    SimpleFilterCriteria filter = new SimpleFilterCriteria(chatId, dateFrom, dateTo);
                    String query = filterCriteriaToQuery(FilterCriteria.of(filter));
                    count += RANDOM.nextInt(1000) + 1;
                    given(reportService.getCount(filter)).willReturn(count);
                    expected.put(query, count);
                }
            }
        }

        for (Map.Entry<String, Long> entry : expected.entrySet()) {
            mvc.perform(get("/reports/count" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().json("{\"count\":" + entry.getValue() + "}"));
        }
    }

    @Test
    void testGetSum() throws Exception {
        BigDecimal sum = getRandomBigDecimal();
        Map<String, BigDecimal> expected = new HashMap<>();

        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    SimpleFilterCriteria filter = new SimpleFilterCriteria(chatId, dateFrom, dateTo);
                    String query = filterCriteriaToQuery(FilterCriteria.of(filter));
                    sum = sum.add(getRandomBigDecimal());
                    given(reportService.getSum(filter)).willReturn(sum);
                    expected.put(query, sum);
                }
            }
        }

        for (Map.Entry<String, BigDecimal> entry : expected.entrySet()) {
            mvc.perform(get("/reports/sum" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().json("{\"sum\":" + entry.getValue() + "}"));
        }
    }

    @Test
    void testGetIncomesCount() throws Exception {
        long count = RANDOM.nextInt(1000);
        Map<String, Long> expected = new HashMap<>();

        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    for (BigDecimal amountFrom : amountsFrom) {
                        for (BigDecimal amountTo : amountsTo) {
                            FilterCriteria filter = new FilterCriteria(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            String query = filterCriteriaToQuery(filter);
                            count += RANDOM.nextInt(1000) + 1;
                            given(reportService.getIncomesCount(filter)).willReturn(count);
                            expected.put(query, count);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, Long> entry : expected.entrySet()) {
            mvc.perform(get("/reports/incomes/count" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().json("{\"count\":" + entry.getValue() + "}"));
        }
    }

    @Test
    void testGetIncomesSum() throws Exception {
        BigDecimal sum = getRandomBigDecimal();
        Map<String, BigDecimal> expected = new HashMap<>();

        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    for (BigDecimal amountFrom : amountsFrom) {
                        for (BigDecimal amountTo : amountsTo) {
                            FilterCriteria filter = new FilterCriteria(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            String query = filterCriteriaToQuery(filter);
                            sum = sum.add(getRandomBigDecimal());
                            given(reportService.getIncomesSum(filter)).willReturn(sum);
                            expected.put(query, sum);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, BigDecimal> entry : expected.entrySet()) {
            mvc.perform(get("/reports/incomes/sum" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().json("{\"sum\":" + entry.getValue() + "}"));
        }
    }

    @Test
    void testGetExpensesCount() throws Exception {
        long count = RANDOM.nextInt(1000);
        Map<String, Long> expected = new HashMap<>();

        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    for (BigDecimal amountFrom : amountsFrom) {
                        for (BigDecimal amountTo : amountsTo) {
                            FilterCriteria filter = new FilterCriteria(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            String query = filterCriteriaToQuery(filter);
                            count += RANDOM.nextInt(1000) + 1;
                            given(reportService.getExpensesCount(filter)).willReturn(count);
                            expected.put(query, count);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, Long> entry : expected.entrySet()) {
            mvc.perform(get("/reports/expenses/count" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().json("{\"count\":" + entry.getValue() + "}"));
        }
    }

    @Test
    void testGetExpensesSum() throws Exception {
        BigDecimal sum = getRandomBigDecimal();
        Map<String, BigDecimal> expected = new HashMap<>();

        for (Long chatId : chatIds) {
            for (LocalDate dateFrom : datesFrom) {
                for (LocalDate dateTo : datesTo) {
                    for (BigDecimal amountFrom : amountsFrom) {
                        for (BigDecimal amountTo : amountsTo) {
                            FilterCriteria filter = new FilterCriteria(chatId, dateFrom, dateTo, amountFrom, amountTo);
                            String query = filterCriteriaToQuery(filter);
                            sum = sum.add(getRandomBigDecimal());
                            given(reportService.getExpensesSum(filter)).willReturn(sum);
                            expected.put(query, sum);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, BigDecimal> entry : expected.entrySet()) {
            mvc.perform(get("/reports/expenses/sum" + entry.getKey()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().json("{\"sum\":" + entry.getValue() + "}"));
        }
    }

    private static BigDecimal getRandomBigDecimal() {
        return BigDecimal.valueOf(RANDOM.nextFloat() * 1000).setScale(2, RoundingMode.HALF_UP);
    }

    private static LocalDate getRandomDate() {
        return LocalDate.now().minusDays(1000).plusDays(RANDOM.nextInt(1000));
    }

    private String filterCriteriaToQuery(FilterCriteria filter) {
        StringBuilder query = new StringBuilder();
        if (filter.getChatId() != null) {
            query.append("&chatId=").append(filter.getChatId());
        }
        if (filter.getDateFrom() != null) {
            query.append("&dateFrom=").append(filter.getDateFrom().toString());
        }
        if (filter.getDateTo() != null) {
            query.append("&dateTo=").append(filter.getDateTo().toString());
        }
        if (filter.getAmountFrom() != null) {
            query.append("&amountFrom=").append(filter.getAmountFrom());
        }
        if (filter.getAmountTo() != null) {
            query.append("&amountTo=").append(filter.getAmountTo());
        }
        return query.length() == 0 ? "" : "?" + query.substring(1);
    }
}