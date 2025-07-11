import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapTest {
    @Test
    public void testInt2IntMapOperations() {
        Int2IntMap map = new Int2IntOpenHashMap();
        map.put(1, 5);
        map.put(2, 10);
        assertEquals(5, map.get(1));
        assertEquals(10, map.get(2));
        map.put(1, map.get(1) | 4);
        assertEquals(5 | 4, map.get(1));
    }
}
