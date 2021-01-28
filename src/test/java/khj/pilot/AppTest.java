package khj.pilot;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void TestIsQuit() {
        assertEquals(App.isQuit("a"), false);
        assertEquals(App.isQuit("q"), true);
        assertEquals(App.isQuit("quit"), true);
    }

    @Test
    public void TestIsEnter() {
        assertEquals(App.isEnter("o"), false);
        assertEquals(App.isEnter("e"), true);
        assertEquals(App.isEnter("enter"), true);
    }
}
