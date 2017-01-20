import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public final class FlattenerTest {

    @Test
    public void testASingleLevelOfNestingWithNoNulls() {
        assertEquals(
          asList(1, 2, 3, 4, 5, 6, 7, 8),
          new Flattener<>().flatten(asList(
            new ValueOrList<>(1),
            new ValueOrList<>(asList(
              new ValueOrList<>(2),
              new ValueOrList<>(3),
              new ValueOrList<>(4),
              new ValueOrList<>(5),
              new ValueOrList<>(6),
              new ValueOrList<>(7))),
            new ValueOrList<>(8))));
    }

    @Test
    public void testFiveLevelsOfNestingWithNoNulls() {
        assertEquals(
          asList("0", "2", "2", "3", "8", "100", "4", "50", "-2"),
          new Flattener<>().flatten(asList(
            new ValueOrList<>("0"),
            new ValueOrList<>("2"),
            new ValueOrList<>(asList(
              new ValueOrList<>(asList(
                new ValueOrList<>("2"),
                new ValueOrList<>("3"))),
              new ValueOrList<>("8"),
              new ValueOrList<>("100"),
              new ValueOrList<>("4"),
              new ValueOrList<>(singletonList(
                new ValueOrList<>(singletonList(
                  new ValueOrList<>(singletonList(
                    new ValueOrList<>("50"))))))),
              new ValueOrList<>("-2"))))));
    }

    @Test
    public void testSixLevelsOfNestingWithNoNulls() {
        assertEquals(
          asList(1, 2, 3, 4, 5, 6, 7, 8),
          new Flattener<>().flatten(asList(
            new ValueOrList<>(1),
            new ValueOrList<>(asList(
              new ValueOrList<>(2),
              new ValueOrList<>(singletonList(
                new ValueOrList<>(singletonList(
                  new ValueOrList<>(3))))),
              new ValueOrList<>(asList(
                new ValueOrList<>(4),
                new ValueOrList<>(singletonList(
                  new ValueOrList<>(singletonList(
                    new ValueOrList<>(5))))))),
              new ValueOrList<>(6),
              new ValueOrList<>(7))),
            new ValueOrList<>(8))));
    }

    @Test
    public void testSixLevelsOfNestingWithNulls() {
        assertEquals(
          asList("0", "2", "2", "3", "8", "100", "-2"),
          new Flattener<>().flatten(asList(
            new ValueOrList<>("0"),
            new ValueOrList<>("2"),
            new ValueOrList<>(asList(
              new ValueOrList<>(asList(
                new ValueOrList<>("2"),
                new ValueOrList<>("3"))),
              new ValueOrList<>("8"),
              new ValueOrList<>(singletonList(
                new ValueOrList<>(singletonList(
                  new ValueOrList<>("100"))))),
              new ValueOrList<>((String) null),
              new ValueOrList<>(singletonList(
                new ValueOrList<>(singletonList(
                new ValueOrList<>((String) null))))),
              new ValueOrList<>("-2"))))));
    }

    @Test
    public void testNestedListsFullOfNullsOnly() {
        assertEquals(
          emptyList(),
          new Flattener<Integer>().flatten(asList(
            new ValueOrList<>((Integer) null),
            new ValueOrList<>(singletonList(
              new ValueOrList<>(singletonList(
                new ValueOrList<>(singletonList(
                  new ValueOrList<>((Integer) null))))))),
            new ValueOrList<>((Integer) null),
            new ValueOrList<>((Integer) null),
            new ValueOrList<>(asList(
              new ValueOrList<>(asList(
                new ValueOrList<>((Integer) null),
                new ValueOrList<>((Integer) null))),
              new ValueOrList<>((Integer) null))),
            new ValueOrList<>((Integer) null))));
    }

}
