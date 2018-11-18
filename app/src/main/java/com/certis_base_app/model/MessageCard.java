package com.certis_base_app.model;

public class MessageCard {
    private String date;
    private String messageText;
    private String messageSentTime;
    private Boolean messageAcknowledged;

    public MessageCard(String date, String messageText, String messageTime, Boolean messageAcknowledged) {
        this.date = date;
        this.messageText = messageText;
        this.messageSentTime = messageTime;
        this.messageAcknowledged = messageAcknowledged;
    }

    public MessageCard(String messageText, String messageTime) {
        this.messageText = messageText;
        this.messageSentTime = messageTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageSentTime() {
        return messageSentTime;
    }

    public void setMessageSentTime(String messageSentTime) {
        this.messageSentTime = messageSentTime;
    }

    public Boolean getMessageAcknowledged() {
        return messageAcknowledged;
    }

    public void setMessageAcknowledged(Boolean messageAcknowledged) {
        this.messageAcknowledged = messageAcknowledged;
    }
}
