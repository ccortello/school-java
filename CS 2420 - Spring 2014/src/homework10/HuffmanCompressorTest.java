package homework10;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Cody on 4/21/14.
 */
public class HuffmanCompressorTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test creation
     */
    @Test
    public void test01() {
        HuffmanCompressor compressor = new HuffmanCompressor();
    }

    /**
     * Test creation
     */
    @Test
    public void test02() {
        HuffmanCompressor compressor = new HuffmanCompressor();
        HuffmanTools.readBytesFromFile("Yankee.txt");
    }
}
