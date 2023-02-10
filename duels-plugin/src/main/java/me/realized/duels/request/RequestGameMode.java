package me.realized.duels.request;

public enum RequestGameMode {
    MIX,
    INDIVIDUAL,
    PARTY;


    public static RequestGameMode fromString(String string) {
        for (RequestGameMode gameMode : values()) {
            if (gameMode.name().equalsIgnoreCase(string)) {
                return gameMode;
            }
        }
        return MIX;
    }

}
