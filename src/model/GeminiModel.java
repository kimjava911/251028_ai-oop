package model;

public enum GeminiModel {
//    GEMINI_2_5_FLASH,
//    GEMINI_2_5_PRO,
//    GEMINI_2_5_FLASH_LITE;
    GEMINI_2_5_FLASH("gemini-2.5-flash"),
    GEMINI_2_5_PRO("gemini-2.5-pro"),
    GEMINI_2_5_FLASH_LITE("gemini-2.5-flash-lite");

    public final String modelCode;
    GeminiModel(String modelCode) {
        this.modelCode = modelCode;
    };
}
