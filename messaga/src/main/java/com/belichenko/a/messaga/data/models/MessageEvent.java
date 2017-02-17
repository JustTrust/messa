package com.belichenko.a.messaga.data.models;

/**
 * Created by Belichenko Anton on 16.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class MessageEvent {
    String messageType;
    String messageText;

    public MessageEvent(String messageType, String messageText) {
        this.messageType = messageType;
        this.messageText = messageText;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getMessageText() {
        return messageText;
    }

    @Override
    public String toString() {
        return String.format("msg type = %s, msg = %s", messageType, messageText);
    }
}
