package com.project.bountymission.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordUtil {
    // 推荐参数配置（根据服务器性能调整）
    private static final int ITERATIONS = 3;     // 迭代次数
    private static final int MEMORY = 65536;     // 内存使用量(KB)
    private static final int PARALLELISM = 1;    // 并行线程数

    private static final Argon2 argon2 = Argon2Factory.create();

    public static String hash(String password) {
        return argon2.hash(ITERATIONS, MEMORY, PARALLELISM, password.toCharArray());
    }

    public static boolean verify(String hash, String password) {
        return argon2.verify(hash, password.toCharArray());
    }
}