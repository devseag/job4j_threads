package ru.job4j.threads;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CountTest {

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        var count = new Count();
        var first = new Thread(count::increment);
        var second = new Thread(count::increment);
        /* Zapuskaem niti. */
        first.start();
        second.start();
        /* Zastavljaem glavnuju nit' dozhdat'sja vypolnenija nashih nitej. */
        first.join();
        second.join();
        /* Proverjaem rezul'tat. */
        assertThat(count.get()).isEqualTo(2);
    }
}