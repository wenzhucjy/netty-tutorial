package com.netty.client.module.chat.handler;

import com.chat.common.core.model.ResultCode;
import com.chat.common.module.chat.response.ChatResponse;
import com.chat.common.module.chat.response.ChatType;
import com.netty.client.swing.ResultCodeTip;
import com.netty.client.swing.SwingClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description: 聊天
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 20:11
 */
@Component
public class ChatHandlerImpl implements ChatHandler {

    @Autowired
    private SwingClient   swingClient;

    @Autowired
    private ResultCodeTip resultCodeTip;

    @Override
    public void publicChat(int resultCode, byte[] data) {
        if (resultCode == ResultCode.SUCCESS) {
            swingClient.getTips().setText("发送成功");
        } else {
            swingClient.getTips().setText(resultCodeTip.getTipContent(resultCode));
        }
    }

    @Override
    public void privateChat(int resultCode, byte[] data) {
        if (resultCode == ResultCode.SUCCESS) {
            swingClient.getTips().setText("发送成功");
        } else {
            swingClient.getTips().setText(resultCodeTip.getTipContent(resultCode));
        }
    }

    @Override
    public void receiveMessage(int resultCode, byte[] data) {
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.readFromBytes(data);

        if (chatResponse.getChatType() == ChatType.PUBLIC_CHAT) {
            String str = chatResponse.getSendPlayerName() +
                    "[" +
                    chatResponse.getSendPlayerId() +
                    "]" +
                    " 说:\n\t" +
                    chatResponse.getMessage() +
                    "\n\n";

            swingClient.getChatContext().append(str);
        } else if (chatResponse.getChatType() == ChatType.PRIVATE_CHAT) {
            StringBuilder builder = new StringBuilder();

            if (swingClient.getPlayerResponse().getPlayerId() == chatResponse.getSendPlayerId()) {
                builder.append("您悄悄对 ");
                builder.append("[");
                builder.append(chatResponse.getTargetPlayerName());
                builder.append("]");
                builder.append(" 说:\n\t");
                builder.append(chatResponse.getMessage());
                builder.append("\n\n");
            } else {
                builder.append(chatResponse.getSendPlayerName());
                builder.append("[");
                builder.append(chatResponse.getSendPlayerId());
                builder.append("]");
                builder.append(" 悄悄对你说:\n\t");
                builder.append(chatResponse.getMessage());
                builder.append("\n\n");
            }

            swingClient.getChatContext().append(builder.toString());
        }
    }
}
