// IMessageSender.aidl
package com.example.ipcdemo;

// Declare any non-default types here with import statements
import com.example.ipcdemo.MessageModel;

interface IMessageSender {
    void sendMessage(in MessageModel model);
    void setBinder(in IBinder client);
}
