package com.netty.module.chat.handler;

import com.chat.common.core.exception.ErrorCodeException;
import com.chat.common.core.model.Result;
import com.chat.common.core.model.ResultCode;
import com.chat.common.module.chat.request.PrivateChatRequest;
import com.chat.common.module.chat.request.PublicChatRequest;
import com.netty.module.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * description: 聊天
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 17:58
 */
@Component
public class ChatHandlerImpl implements ChatHandler {

    @Autowired
    private ChatService chatService;

    /**
     * 广播消息
     *
     * @param playerId 玩家id
     * @param data {@link PublicChatRequest}
     * @return {@link Result}
     */
    @Override
    public Result<?> publicChat(long playerId, byte[] data) {

        try {
            // 反序列化
            PublicChatRequest request = new PublicChatRequest();
            request.readFromBytes(data);

            String context = request.getContext();
            // 校验数据为空
            if (StringUtils.isEmpty(context)) {
                return Result.ERROR(ResultCode.AGRUMENT_ERROR);
            }
            // 执行业务
            this.chatService.publicChat(playerId, context);
            return Result.SUCCESS();
        } catch (ErrorCodeException e) {
            return Result.ERROR(e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
        }
    }

    /**
     * 私人消息
     *
     * @param playerId 玩家id
     * @param data {@link PrivateChatRequest}
     * @return {@link Result}
     */
    @Override
    public Result<?> privateChat(long playerId, byte[] data) {
        try {
            // 反序列化
            PrivateChatRequest request = new PrivateChatRequest();
            request.readFromBytes(data);

            String context = request.getContext();
            long targetPlayerId = request.getTargetPlayerId();
            // 校验数据为空
            if (StringUtils.isEmpty(context) || targetPlayerId <= 0) {
                return Result.ERROR(ResultCode.AGRUMENT_ERROR);
            }
            // 执行业务
            this.chatService.privateChat(playerId, targetPlayerId, context);
            return Result.SUCCESS();
        } catch (ErrorCodeException e) {
            return Result.ERROR(e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
        }
    }
}
