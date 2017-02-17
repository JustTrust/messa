package com.belichenko.a.messaga;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Belichenko Anton on 17.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class Contract {

    // Message types
    enum MessageType {
        TEXT("Text message", 0),
        IMAGE("Image", 1),
        LINK("Link",2),
        LOCATION("Location", 3),
        VOICE("Voice", 4);

        private String name;
        private int code;
        private static Map<Integer, MessageType> map = new HashMap<>();

        static {
            for (MessageType messageEnum : MessageType.values()) {
                map.put(messageEnum.code, messageEnum);
            }
        }

        MessageType(String toString, int value) {
            name = toString;
            code = value;
        }

        @Override
        public String toString() {
            return name;
        }

        MessageType getType(int code){
            return map.get(code);
        }
    }
}
