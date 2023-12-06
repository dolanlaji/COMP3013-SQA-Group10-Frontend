package com.example.group10_sqa_mentalhealthapp.user;

// todo: adjust and refactor
public class LevelHandler {
    // Growth rate i
    private static final float i = 20.0f;

    // Growth factor f
    private static final long f = 3;

    // XP -> Level
    public static long GetLevel(UserEntry user) {
        if (user.xp < i) {
            return 0;
        }
        return (long) (Math.log(user.xp / i) / Math.log(f) + 1);
    }

    // 0.0f -> 1.0f
    public static float GetLevelProgress(UserEntry user) {
        long level = GetLevel(user);
        if (level == 0) {
            return user.xp / i;
        }
        double xpForCurrentLevel = Math.pow(f, level - 1) * i;
        double xpForNextLevel = Math.pow(f, level) * i;
        return (float)((user.xp - xpForCurrentLevel) / (xpForNextLevel - xpForCurrentLevel));
    }

    // Remainder of XP -> Level
    public static long GetXPRemaining(UserEntry user) {
        long level = GetLevel(user);
        double xpForNextLevel = Math.pow(f, level) * i;
        return (long)(xpForNextLevel - user.xp);
    }
}