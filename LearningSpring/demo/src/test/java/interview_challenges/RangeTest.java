package interview_challenges;

import com.example.demo.main.ubitec.RangeLocal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

public class RangeTest {

  @Test
  public void should_create_range() {
    RangeLocal range = RangeLocal.of(5, 50);
    assertThat(range.lowerbound()).isEqualTo(5);
    assertThat(range.upperbound()).isEqualTo(50);

    RangeLocal<BigDecimal> rangeLocalBD =
            RangeLocal.open(new BigDecimal("1.32432"),
                    new BigDecimal("1.324323423423423423423"));
    assertThat(rangeLocalBD.lowerbound()).isEqualTo("1.32432");
    assertThat(rangeLocalBD.upperbound()).isEqualTo("1.324323423423423423423");

    RangeLocal<LocalDate> rangeLocalLD = RangeLocal.open(
            LocalDate.of(2016, Month.SEPTEMBER, 11),
            LocalDate.of(2017, Month.JUNE, 30));
    assertThat(rangeLocalLD.lowerbound()).isEqualTo(LocalDate.of(2016, Month.SEPTEMBER, 11));
    assertThat(rangeLocalLD.upperbound()).isEqualTo(LocalDate.of(2017, Month.JUNE, 30));
  }

  @Test
  public void should_throw_error__when_create_with_lowerbound_bigger_than_upperbound() {
    try {
      RangeLocal.of(500, 1);
    } catch(RuntimeException e) {
      assertThat("Should not allow creating a invalid Range").isEqualTo(e.getMessage());
    }
  }

  //TODO check integer min max
  @Test
  public void range_should_contain_both_bounds_and_all_elements_in_between() {
    RangeLocal closedRange = RangeLocal.of(5, 50);

    assertThat(closedRange.contains(Integer.MIN_VALUE)).isEqualTo( false);
    assertThat(closedRange.contains(4)).isEqualTo( false);

    assertThat(closedRange.contains(5)).isEqualTo( true);

    assertThat(closedRange.contains(42)).isEqualTo( true);

    assertThat(closedRange.contains(50)).isEqualTo( true);

    assertThat(closedRange.contains(10000)).isEqualTo( false);
    assertThat(closedRange.contains(Integer.MAX_VALUE)).isEqualTo( false);

    RangeLocal<BigDecimal> rangeLocalBD =
            RangeLocal.open(new BigDecimal("1.32432"),
                    new BigDecimal("1.324323423423423423423"));

    assertThat(rangeLocalBD.contains(new BigDecimal("1.32432"))).isEqualTo( false);
    assertThat(rangeLocalBD.contains(new BigDecimal("1.324323423423423423423"))).isEqualTo( false);
    assertThat(rangeLocalBD.contains(new BigDecimal("1"))).isEqualTo( false);
    assertThat(rangeLocalBD.contains(new BigDecimal("1.5"))).isEqualTo( false);
    assertThat(rangeLocalBD.contains(new BigDecimal("1.3243225"))).isEqualTo( true);

    RangeLocal<LocalDate> rangeLocalLD = RangeLocal.openClosed(
            LocalDate.of(2016, Month.SEPTEMBER, 11),
            LocalDate.of(2017, Month.JUNE, 30));

    assertThat(rangeLocalLD.contains(LocalDate.of(2016, Month.SEPTEMBER, 11)))
            .isEqualTo( false);
    assertThat(rangeLocalLD.contains(LocalDate.of(2015, Month.SEPTEMBER, 12)))
            .isEqualTo( false);
    assertThat(rangeLocalLD.contains(LocalDate.of(2018, Month.SEPTEMBER, 12)))
            .isEqualTo( false);
    assertThat(rangeLocalLD.contains(LocalDate.of(2017, Month.JUNE, 30)))
            .isEqualTo( true);
    assertThat(rangeLocalLD.contains(LocalDate.of(2017, Month.FEBRUARY, 12)))
            .isEqualTo( true);

    RangeLocal<Integer> lessThanFive = RangeLocal.lessThan(5);
    assertThat(lessThanFive.contains(5)).isEqualTo(false);
    assertThat(lessThanFive.contains(-9000)).isEqualTo(true);
    assertThat(lessThanFive.contains(6)).isEqualTo(false);

    RangeLocal<Integer> atLeastFive = RangeLocal.atLeast(5);
    assertThat(atLeastFive.contains(5)).isEqualTo(true);
    assertThat(atLeastFive.contains(4)).isEqualTo(false);
    assertThat(atLeastFive.contains(400)).isEqualTo(true);

    RangeLocal<Integer> atMostFive = RangeLocal.atMost(5);
    assertThat(atMostFive.contains(5)).isEqualTo(true);
    assertThat(atMostFive.contains(-234234)).isEqualTo(true);
    assertThat(atMostFive.contains(6)).isEqualTo(false);

    RangeLocal<LocalDate> afterEpoch = RangeLocal.greaterThan(LocalDate.of(1900, Month.JANUARY, 1));
    assertThat(afterEpoch.contains(LocalDate.of(2016, Month.JULY, 28))).isEqualTo(true);
    assertThat(afterEpoch.contains(LocalDate.of(1750, Month.JANUARY, 1))).isEqualTo(false);
    assertThat(afterEpoch.contains(LocalDate.of(1900, Month.JANUARY, 1))).isEqualTo(false);

    RangeLocal<String> all = RangeLocal.all();
    assertThat(all.contains("anything")).isEqualTo(true);
    assertThat(all.contains("")).isEqualTo(true);
    assertThat(all.contains(null)).isEqualTo(true);
    assertThat(all.contains(123)).isEqualTo(true);
  }

  @Test
  public void range_should_parse_to_object_from_string() {
    RangeLocal<Integer> lessThan100 = RangeLocal.lessThan(100);
    assertThat(lessThan100.toString()).isEqualTo("[Infinitive, 100)");

    RangeLocal<LocalDate> within2020 = RangeLocal.closed(
            LocalDate.of(2020, Month.JANUARY, 1),
            LocalDate.of(2020, Month.DECEMBER, 31));
    assertThat(within2020.toString()).isEqualTo("[2020-01-01, 2020-12-31]");

    RangeLocal<BigDecimal> withinBD = RangeLocal.open(BigDecimal.valueOf(new Double("1.32432")),
            BigDecimal.valueOf(new Double("1.67334")));
    assertThat(withinBD.toString()).isEqualTo("(1.32432, 1.67334)");

    RangeLocal<Integer> parseInt = RangeLocal.parse("[Infinitive, 100)", Integer.class);
    assertThat(parseInt.contains(100)).isEqualTo(false);
    assertThat(parseInt.contains(222)).isEqualTo(false);
    assertThat(parseInt.contains(-12)).isEqualTo(true);
    RangeLocal<LocalDate> parseLD = RangeLocal.parse("[Infinitive, 2020-12-31)", LocalDate.class);
    assertThat(parseLD.contains(LocalDate.of(2020, Month.DECEMBER, 31))).isEqualTo(false);
    assertThat(parseLD.contains(LocalDate.of(1900, Month.JANUARY, 31))).isEqualTo(true);
    assertThat(parseLD.contains(LocalDate.of(3000, Month.SEPTEMBER, 01))).isEqualTo(false);

    RangeLocal<Integer> parseInt2 = RangeLocal.parse("[3, 9)", Integer.class);
    assertThat(parseInt2.contains(4)).isEqualTo(true);
    assertThat(parseInt2.contains(9)).isEqualTo(false);
    assertThat(parseInt2.contains(3)).isEqualTo(true);
    assertThat(parseInt2.contains(10)).isEqualTo(false);
    assertThat(parseInt2.contains(-22)).isEqualTo(false);
  }
}
