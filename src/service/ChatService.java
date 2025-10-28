package service;

import model.ChatModel;

public interface ChatService {
    // 메서드 위주로 생각
    String chat(String input, ChatModel model, String instruction);
}
