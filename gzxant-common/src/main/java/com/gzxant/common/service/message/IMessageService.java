package com.gzxant.common.service.message;

import com.gzxant.common.entity.message.SendMessage;

public interface IMessageService {
    boolean send(SendMessage msg);
}
