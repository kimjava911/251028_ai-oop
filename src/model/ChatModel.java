package model;

public enum ChatModel {
    GEMINI_2_5_FLASH("gemini-2.5-flash", ChatCategory.GEMINI),
    GEMINI_2_5_PRO("gemini-2.5-pro", ChatCategory.GEMINI),
    GEMINI_2_5_FLASH_LITE("gemini-2.5-flash-lite", ChatCategory.GEMINI),
    // https://console.groq.com/docs/models
    GPT_OSS_120B("openai/gpt-oss-120b", ChatCategory.GROQ);

    public final String modelCode;
    public final ChatCategory category;
    ChatModel(String modelCode, ChatCategory category) {
        this.modelCode = modelCode;
        this.category = category;
    };
}
