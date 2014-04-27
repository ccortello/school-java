package homework10;

import java.io.*;
import java.util.*;

/**
 * A HuffmanCompressor object contains no data - it is just an
 * implementation of the Compressor interface.  It contains the compress
 * and decompress methods, along with a series of helper methods
 * for counting tokens, building the Huffman tree, and storing data
 * in byte arrays.
 * <p/>
 * The only methods that should be public are the constructor and the
 * Compressor interface methods, the rest should be private.  I have
 * left them public, though, for testing.
 *
 * @author Peter Jensen - CS 2420
 * @version Spring 2014
 */
public class HuffmanCompressor implements Compressor {
    // There are NO fields in the compressor class.  If you need
    //   to get data to or from the methods, use a parameter!  (Of course,
    //   you shouldn't need to add any, the definitions below are complete.)

    /**
     * This constructor does nothing.  There are no fields to initialize.
     * It is provided simply for testing.  (You must make a HuffmanCompressor
     * object in order to test the compress and decompress methods.)
     */
    public HuffmanCompressor() {
    }

    /**
     * This helper method counts the number of times each data value occurs in
     * the given byte array.  For each different value, a HuffmanToken is
     * created and stored.  When the same value is seen again, its token's frequency
     * is increased.  After all the different data values have been counted
     * this method will return an ArrayList of HuffmanToken objects.
     * Each token will contain a count of how many times that token occurred
     * in the byte array.  (If you summed up the counts, the total would be
     * same as the length of the data array.)
     * <p/>
     * Note that the returned tokens in the ArrayList may be in any order.
     *
     * @param data A list of data bytes
     * @return A list of HuffmanTokens that contain token counts
     */
    public ArrayList<HuffmanToken> countTokens(byte[] data) {

        // initialize the return list and a map to track the frequencies of each byte in the input array
        ArrayList<HuffmanToken> returnList = new ArrayList<HuffmanToken>();
        HashMap<Byte, Integer> frequencyMap = new HashMap<Byte, Integer>();

        // iterate through the input array and update the map accordingly
        //  note: a HashMap is implemented instead of just the ArrayList to minimize the access complexity
        for (byte currentByte : data) {

            // check if the byte already exists in the map, and either create an entry or increment the frequency accordingly
            if (!frequencyMap.containsKey(currentByte))
                frequencyMap.put(currentByte, 1);
            else frequencyMap.put(currentByte, frequencyMap.get(currentByte) + 1);
        }

        // use the HashMap to create HuffmanTokens and add them to the returnList
        for (Byte mapKey : frequencyMap.keySet()) {
            HuffmanToken addToken = new HuffmanToken(mapKey);
            addToken.setFrequency(frequencyMap.get(mapKey));
            returnList.add(addToken);
        }

        // return the substantiated list
        return returnList;
    }

    /**
     * This helper method builds and returns a Huffman tree that contains the
     * given tokens in its leaf nodes.
     * <p/>
     * The Huffman tree-building algorithm is used here.  You may find it
     * in the book or in your notes from lecture.  Remember to first
     * create leaf nodes for all the tokens, and add these leaf nodes to a
     * priority queue.  You may then build and return the tree.
     * <p/>
     * It is assumed that the tokens do not have Huffman codes when this method is
     * called.  Due to the side-effect of one of the HuffmanToken constructors,
     * the HuffmanToken objects will have correct Huffman codes when this method
     * finishes building the tree.  (They are built as the tree is built.)
     *
     * @param tokens A list of Tokens, each one with a frequency count
     * @return The root node of a Huffman tree
     */
    public HuffmanNode buildHuffmanCodeTree(ArrayList<HuffmanToken> tokens) {

        // initialize the queue
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(tokens.size());

        // create a node for each token and add the tokens to the queue
        for (HuffmanToken addToken : tokens)
            queue.add(new HuffmanNode(addToken));

        // combine the nodes in the queue until there is only one node left
        while (queue.size() > 1) {

            // poll the bottom two nodes and combine them
            HuffmanNode firstNode = queue.poll();
            HuffmanNode secondNode = queue.poll();
            HuffmanNode combineNode = new HuffmanNode(firstNode, secondNode);

            // add the combined node back to the queue
            queue.add(combineNode);
        }

        // return the root of the new tree, being the only node left in the queue
        return queue.poll();
    }

    /**
     * This helper method creates a dictionary of Huffman codes from a list
     * of Huffman tokens.  It is assumed that the Huffman tokens have
     * correct Huffman codes stored in them.
     * <p/>
     * This method is for convenience only.  Values and Huffman codes are extracted
     * from the tokens and added to a Map object so that they can be quickly
     * retrieved when needed.
     *
     * @param tokens A list of Tokens, each one with a Huffman code
     * @return A map that maps byte values to their Huffman codes
     */
    public Map<Byte, ArrayList<Boolean>> createEncodingMap(ArrayList<HuffmanToken> tokens) {

        // initialize return map
        TreeMap<Byte, ArrayList<Boolean>> returnMap = new TreeMap<Byte, ArrayList<Boolean>>();

        // substantiate the map using the values from each input token
        for (HuffmanToken currentToken : tokens)
            returnMap.put(currentToken.getValue(), currentToken.getCode());

        // return the map
        return returnMap;
    }

    /**
     * This helper method encodes an array of data bytes as an ArrayList of
     * bits (Boolean values).  Huffman codes are used to translate the bytes
     * into bits.<p>&nbsp;<p>
     * <p/>
     * For every value in the data array, the corresponding Huffman code is
     * retrieved from the map and added to a new ArrayList that will be returned.
     *
     * @param data        An array of data bytes that will be encoded (compressed)
     * @param encodingMap A map that maps byte values to Huffman codes (bits)
     * @return An ArrayList of bits (Booleans) that represent the compressed (Huffman encoded) data
     */
    public ArrayList<Boolean> encodeBytes(byte[] data, Map<Byte, ArrayList<Boolean>> encodingMap) {

        // initialize return list
        ArrayList<Boolean> returnList = new ArrayList<Boolean>();

        // for each byte in the input array find the uncompressed value from the map and write the byte to the array
        for (byte currentByte : data)
            returnList.addAll(encodingMap.get(currentByte));

        return returnList;
    }

    /**
     * This helper method decodes a list of bits (which are Huffman codes) into
     * an array of bytes.  In order to do the decoding, a Huffman tree
     * containing the tokens is required.
     *
     * To do the decoding, follow the decoding algorithm given in the book
     * or review your notes from lecture.
     *
     * (You will need
     * to build a Huffman tree prior to calling this method, and the Huffman
     * tree you build should be exactly the same as the one that was used to
     * encode the data.)
     *
     * @param bitCodes   An ArrayList of bits (Booleans) that represent the compressed (Huffman encoded) data
     * @param codeTree   A Huffman tree that can be used to decode the bits
     * @param dataLength The number of bytes that will be in the decoded byte array
     * @return An array of bytes that represent the uncompressed data
     */
    public byte[] decodeBits(ArrayList<Boolean> bitCodes, HuffmanNode codeTree, int dataLength) {

        // intialize the return array using the input dataLength
        byte[] returnArray = new byte[dataLength];

        // create two markers to indicate where the return array should be written to and the position where the next bit
        //  should be read from
        int writePosition = 0, bitPos = 0;

        // use the bits in the input array to traverse the tree and output the bytes when a leaf node is encountered
        HuffmanNode currentNode = codeTree;
        while (writePosition < dataLength) {

            // until the current node is a leaf keep traversing down the tree and incrementing the position
            while (!currentNode.isLeafNode()) {
                if (bitCodes.get(bitPos)) currentNode = currentNode.getRightSubtree();
                else currentNode = currentNode.getLeftSubtree();
                bitPos++;
            }

            // once a leaf node has been found write the token to the output array, increment the write position, and
            //  reset currentNode to reset the looping
            returnArray[writePosition] = currentNode.getToken().getValue();
            writePosition++;
            currentNode = codeTree;
        }

        // return the array of uncompressed bits
        return returnArray;
    }

    /**
     * Given any array of bytes that contain some data, this method returns a
     * compressed form of the original data.  The returned, compressed bytes must
     * contain sufficient information so that the decompress method below can
     * reconstruct the original data.
     * <p/>
     * Huffman coding is used to compress the data.<p>&nbsp;<p>
     * <p/>
     * Some of the code for this method has been provided for you.  You should figure out
     * what it does, it will significantly help you.
     *
     * @param data An array of bytes that contains some data that should be compressed
     * @return An array of bytes that contains the compressed form of the original data
     */
    public byte[] compress(byte[] data) {

        // initialize the output array
        byte[] compressedBytes = new byte[]{};

        // use the input array to substantiate a list of HuffmanTokens
        ArrayList<HuffmanToken> tokens = countTokens(data);

        // create a tree with the tokens to set their codes correctly
        buildHuffmanCodeTree(tokens);

        // use the tokens to encode the bits
        ArrayList<Boolean> encodedBits = encodeBytes(data, createEncodingMap(tokens));

        HuffmanTools.dumpHuffmanCodes(tokens);  // Useful for debugging

        // You need to set up the appropriate variables before this code begins.  This
        //   code will place various data elements of the compressed data into
        //   a byte array for you.

        try {
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            DataOutputStream output = new DataOutputStream(byteOutput);

            // write the compressed data: first the size of the uncompressed data, then the code table of values and
            //  frequencies of the bytes, and fin
            output.writeInt(data.length);
            writeTokenList(output, tokens);
            writeBitCodes(output, encodedBits);

            compressedBytes = byteOutput.toByteArray();
        } catch (IOException e) {
            System.out.println("Fatal compression error: " + e.getMessage());
            e.printStackTrace();
        }

        return compressedBytes;
    }

    /**
     * Given an array of bytes that contain compressed data that
     * was compressed using this compressor, this method will reconstruct and return
     * the original, uncompressed data.  The compressed bytes must contain sufficient
     * information so that this method can reconstruct the original data bytes.
     * <p/>
     * Huffman coding is used to decompress the data.<p>&nbsp;<p>
     * <p/>
     * Some of the code for this method has been provided for you.  You should figure out
     * what it does, it will significantly help you.
     *
     * @param compressedData An array of bytes that contains some data in compressed form
     * @return An array of bytes that contains the original, uncompressed data
     */
    public byte[] decompress(byte[] compressedData) {

        // Variable initialization stubbed out here.
        int dataLength;
        ArrayList<HuffmanToken> tokens;
        ArrayList<Boolean> encodedBits;

        // You need to set up the appropriate variables before this code begins.  This
        //   code will extract various data elements from the compressedData bytes for you.

        try {
            ByteArrayInputStream byteInput = new ByteArrayInputStream(compressedData);
            DataInputStream input = new DataInputStream(byteInput);

            dataLength = input.readInt();
            tokens = readTokenList(input);
            encodedBits = readBitCodes(input);
        } catch (IOException e) {
            System.out.println("Fatal compression error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        // Decompression steps stubbed out here.

        // create a tree from the tokens
        HuffmanNode root = buildHuffmanCodeTree(tokens);

        // decode the given bits using the substantiated tree
        byte[] decompressedData = decodeBits(encodedBits, root, dataLength);

//        HuffmanTools.dumpHuffmanCodes(tokens);  // Useful for debugging

        // Return statement stubbed out.
        return decompressedData;
    }

    // The following methods read and write data values from a ByteArray Streams.  Because I'm giving you
    //   this code, you should try to comment these methods yourself.

    /**
     * The method writes the code table of a given ArrayList of HuffmanToken objects to the given DataOutputStream. This
     * is used as the second segment of output when compressing data
     *
     * @param output    the DataOutputStream to be written to
     * @param tokens    the HuffmanTokens which are used to write the output code table
     *
     * @throws java.io.IOException
     */
    public void writeTokenList(DataOutputStream output, ArrayList<HuffmanToken> tokens) throws IOException {
        // write the number of tokens to the output
        output.writeInt(tokens.size());

        // write each token's value and frequency to the output
        for (HuffmanToken token : tokens) {
            output.writeByte(token.getValue());
            output.writeInt(token.getFrequency());
        }
    }

    /**
     * This method substantiates an ArrayList of HuffmanTokens by reading the DataInputStream (which represents a
     * compressed code table of values and frequencies).
     *
     * @param input     the DataInputStream to be read
     * @return an ArrayList of HuffmanTokens
     */
    public ArrayList<HuffmanToken> readTokenList(DataInputStream input) throws IOException {

        // initialize the return list
        ArrayList<HuffmanToken> tokens = new ArrayList<HuffmanToken>();

        // use the first integer in the input stream to set a count of tokens to read from the DataInputStream
        int count = input.readInt();

        // use each value and frequency in the code table (again, existing in the given DataInputStream) to create
        // HuffmanTokens.
        //  note: the DataInputStream will stop reading exactly where the code table ends and the compressed data begins
        //        since the count of tokens determines the number of values and frequencies which are read
        for (int i = 0; i < count; i++) {
            HuffmanToken token = new HuffmanToken(input.readByte());
            token.setFrequency(input.readInt());
            tokens.add(token);
        }

        // return the substantited list of HuffmanTokens
        return tokens;
    }

    /**
     * This method writes the bytes represented by an ArrayList of booleans to the given DataOutputStream object and
     * returns a count of the bytes written.
     *
     * @param output    an DataOutputStream object which the bytes should be written to
     * @param bits      an ArrayList of booleans which represent bytes
     * @return the number of bytes written to the DataOutputStream
     *
     * @throws java.io.IOException
     */
    public int writeBitCodes(DataOutputStream output, ArrayList<Boolean> bits) throws IOException {
        int bytesWritten = 0;

        // loop through input ArrayList 8 booleans (1 byte) at a time
        for (int pos = 0; pos < bits.size(); pos += 8) {
            int b = 0;

            // use the next 8 positions to substantiate a return byte
            for (int i = 0; i < 8; i++) {
                b = b * 2;

                // increment b if the boolean at the relevant position is true
                if (pos + i < bits.size() && bits.get(pos + i))
                    b = b + 1;
            }

            // write the substantiated byte to the output stream and update the bytesWritten counter
            output.writeByte((byte) b);
            bytesWritten++;
        }

        return bytesWritten;
    }

    /**
     * Given a DataInputStream this function returns an ArrayList<Boolean> of the bits in the DataInputStream
     *
     * @param input a DataInputStream object to read bits from
     * @return an ArrayList which represents the bits available in the DataInputStream
     *
     * @throws java.io.IOException
     */
    public ArrayList<Boolean> readBitCodes(DataInputStream input) throws IOException {
        ArrayList<Boolean> bits = new ArrayList<Boolean>();

        // read each of the bytes from the input one at a time
        while (input.available() > 0) {
            int b = input.readByte();

            // shift the current byte and AND the byte with 1 to write each bit of the byte to the output ArrayList
            //  note: the '==1' returns true iff the bit is a one, which makes it effectively a cast from int to boolean
            for (int i = 7; i >= 0; i--)
                bits.add(((b >> i) & 1) == 1);
        }

        return bits;
    }
}
