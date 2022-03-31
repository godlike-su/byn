package com.utli;

import java.util.UUID;

public class UUIDGenerator {
    private static final String[] CHARS_36 = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String[] CHARS_62 = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private UUIDGenerator() {
    }

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.replaceAll("-", "");
    }

    public static String getUUID16Bit() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");

        for(int i = 0; i < 8; ++i) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(CHARS_62[x % CHARS_62.length]).append(CHARS_36[x % CHARS_36.length]);
        }

        return shortBuffer.toString();
    }

    public static String[] getUUID(int number) {
        String[] uuids = null;
        int minSize = 1;
        if (number >= minSize) {
            uuids = new String[number];

            for(int i = 0; i < number; ++i) {
                uuids[i] = getUUID();
            }
        }

        return uuids;
    }
}
