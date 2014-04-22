package homework10;

import java.util.ArrayList;

/**
 * Created by Cody on 4/16/14.
 */
public class HuffmanTester {
    public static void main(String[] args) {
        HuffmanCompressor compressor = new HuffmanCompressor();
        byte[] byteArray = HuffmanTools.readBytesFromFile("C:\\Users\\Cody\\workspace\\CS 2420 - Spring 2014\\src\\homework10\\Yankee.txt");
//        HuffmanTools.dumpBytesAsValues(byteArray, 0, 100); System.out.println();
//        HuffmanTools.dumpBytesAsIntegers(byteArray, 0, 100); System.out.println();
//        HuffmanTools.dumpBytesAsCharacters(byteArray, 0, 100); System.out.println();
//        HuffmanTools.dumpBytesAsBits(byteArray, 0, 100); System.out.println();

        ArrayList<HuffmanToken> huffmanTokens = compressor.countTokens(byteArray);

// debug: output each token, its frequency, and its code
//        for (HuffmanToken currentToken : huffmanTokens) {
//            System.out.println(((char) currentToken.getValue())+" has frequency "+currentToken.getFrequency()+" and code "
//                    +currentToken.getCode());
//        }

        HuffmanNode huffmanRoot = compressor.buildHuffmanCodeTree(huffmanTokens);
    }
}
