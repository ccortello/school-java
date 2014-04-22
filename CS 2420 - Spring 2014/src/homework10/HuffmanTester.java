package homework10;

import java.util.ArrayList;

/**
 * Created by Cody on 4/16/14.
 */
public class HuffmanTester {
    public static void main(String[] args) {
        HuffmanCompressor compressor = new HuffmanCompressor();
        byte[] byteArray = HuffmanTools.readBytesFromFile("C:\\Users\\Cody\\workspace\\CS 2420 - Spring 2014\\src\\homework10\\Test.txt");
        HuffmanTools.dumpBytesAsValues(byteArray, 0, 10);
        System.out.println();
//        HuffmanTools.dumpBytesAsCharacters(byteArray, 0, 100);
//        HuffmanTools.dumpBytesAsBits(byteArray, 0, 100);

        ArrayList<HuffmanToken> huffmanTokens = compressor.countTokens(byteArray);
        HuffmanNode huffmanRoot = compressor.buildHuffmanCodeTree(huffmanTokens);

    }
}
