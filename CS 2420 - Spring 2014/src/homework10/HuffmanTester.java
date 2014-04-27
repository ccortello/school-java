package homework10;

import java.util.Arrays;

/**
 * Created by Cody Cortello and Nick Houle on 4/16/14.
 */
public class HuffmanTester {
    public static void main(String[] args) {
        new HuffmanTester();
    }

    public HuffmanTester() {
        HuffmanCompressor compressor = new HuffmanCompressor();
        byte[] byteArray = HuffmanTools.readBytesFromFile("C:\\Users\\Cody\\workspace\\CS 2420 - Spring 2014\\src\\homework10\\Ulysses.txt");

        // attempt top down compression and decompression
        byte[] compressedBytes = compressor.compress(byteArray);
        byte[] decompressedBytes = compressor.decompress(compressedBytes);
        HuffmanTools.writeBytesToFile("Compression output.txt", decompressedBytes);
        if (Arrays.equals(byteArray, decompressedBytes))
            System.out.println("Compression successful!");
        else
            System.out.println("Compression failed");
    }
}
