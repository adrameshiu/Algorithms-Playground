import java.util.*;

public class HuffmanCoding {
    static class HuffmanNode implements Comparable<HuffmanNode> {
        int frequency;
        char data;
        HuffmanNode left, right;

        public int compareTo(HuffmanNode node) {
            return frequency - node.frequency;
        }
    }

    private static HuffmanNode initCharNode(String text, char c) {
        HuffmanNode charNode = new HuffmanNode();
        charNode.data = c;
        charNode.frequency = findFrequencyOfCharacter(text, c);
        charNode.left = null;
        charNode.right = null;
        return charNode;
    }

    private static int findFrequencyOfCharacter(String text, char x) {
        int charFrequency = 0;
        for(int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == x)
                charFrequency = charFrequency + 1;
        }
        return charFrequency;
    }

    public static Map<Character, String> generateHuffmanCodes(Object node, Map<Character, String> codeMap, StringBuilder codeStringBuilder) {
        //starting from the root, if node is not null and the left and right nodes of it are not null, it means it is not a leaf node..
        // keep recursively traversing through the path for each string till we reach leaf(where there is no null left right and assign that value to the string
        HuffmanNode huffNode = (HuffmanNode) node;
        if (huffNode != null) {
            if (huffNode.left == null && huffNode.right == null) {  //means we are at leaf
                codeMap.put(huffNode.data,codeStringBuilder.toString());
            } else {
                codeStringBuilder.append('0'); //when we are going left, append 0
                generateHuffmanCodes(huffNode.left, codeMap, codeStringBuilder); //recursively run the same for left sub node
                //for the right sub node that we traverse, the character we just added should be removed as we EITHER go left OR right
                //it will be the last character that was added to the string
                codeStringBuilder.deleteCharAt(codeStringBuilder.length() - 1);

                codeStringBuilder.append('1'); //when we are going right, append 1
                generateHuffmanCodes(huffNode.right, codeMap, codeStringBuilder);
                //we need to similarly remove the last character that was added, before getting out of this condition
                codeStringBuilder.deleteCharAt(codeStringBuilder.length() - 1);
            }
        }
        return codeMap;

    }

    public static String getDecodedString(Object node, String encodedString) {
        StringBuilder decodedStringBuilder = new StringBuilder();
        HuffmanNode rootNode = (HuffmanNode) node;

        HuffmanNode currentNode = rootNode;

        for (int i=0;i<encodedString.length();i++) {
            char currentCodeCharacter = encodedString.charAt(i);

            currentNode = (currentCodeCharacter == '0') ? currentNode.left : currentNode.right; //if we have a 0 in string, that means we go left; if it is 1, we go right

            //if this is a leaf node(as in the left and right sub nodes of this current node is null, we have reached the intended character
            if((currentNode.left == null) && (currentNode.right == null)) {
                decodedStringBuilder.append(currentNode.data);
                currentNode = rootNode; //we start trace again from root node for the next character
            }
        }

        return decodedStringBuilder.toString();

    }
    public static Object build(String text) {

        //by default, java uses min heap in priority queue. initially we want to compare letters with lesser frequency so that they go in the bottom of our final heap
        //so we first use this as a min heap, so that pop/poll give us the min element
        PriorityQueue<HuffmanNode> huffmanPriorityQueue = new PriorityQueue<HuffmanNode>();
        ArrayList<Character> uniqueCharInText = new ArrayList<Character>();

        // Base case: empty string
        if (text == null || text.length() == 0) {
            return huffmanPriorityQueue;
        }

        for(int i = 0; i < text.length(); i++) {
            if (!uniqueCharInText.contains(text.charAt(i))) { //only count occurrences for new character found
                //charNodes.add(initCharNode(text, text.charAt(i)));
                huffmanPriorityQueue.add(initCharNode(text, text.charAt(i)));
                uniqueCharInText.add(text.charAt(i));
            }
        }

        while (huffmanPriorityQueue.size() > 1) {
            //we compare the elements at the top of the tree(will be the least occuring elements initially) and keep combining them to get 1 element with left and right sub nodes ultimately
            HuffmanNode x = huffmanPriorityQueue.poll();
            HuffmanNode y = huffmanPriorityQueue.poll();
            HuffmanNode sum = new HuffmanNode();

            sum.frequency = x.frequency + y.frequency;
            sum.data = '!'; //setting this as internal node character for data
            sum.left = x;
            sum.right = y;

            huffmanPriorityQueue.add(sum);
        }

        //now return the priority queue which just has the root node and the rest of the nodes will be either to left or right
        return huffmanPriorityQueue.poll();
    }

    public static String encode(Object dic, String text) {
        Map<Character, String> huffmanCodeMap = new HashMap<>(); //each character will be mapped to a binary huffman code
        StringBuilder codeStringBuilder = new StringBuilder();
        StringBuilder encodedStringBuilder = new StringBuilder();

        //generate huffman codes from the priority queue tree
        huffmanCodeMap = generateHuffmanCodes(dic, huffmanCodeMap, codeStringBuilder);

        //encode the string based on code map we just constructed
        for (int i=0;i<text.length();i++) {
            char currentCharacter = text.charAt(i);
            String encodedCharacter = huffmanCodeMap.get(currentCharacter);
            encodedStringBuilder.append(encodedCharacter);
        }

        return  encodedStringBuilder.toString();
    }

    public static String decode(Object dic, String text) {
        String decodedString = getDecodedString(dic, text);

        return decodedString;
    }


}
