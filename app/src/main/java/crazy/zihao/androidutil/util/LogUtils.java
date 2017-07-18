package crazy.zihao.androidutil.util;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * ClassName：LogUtils
 * Description：TODO<便于日志打印/保存操作的工具类>
 * Author：zihao
 * Date：2017/7/12 15:31
 * Email：crazy.zihao@gmail.com
 * Version：v1.0
 */
public class LogUtils {

    private LogUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static final int MAX_FILE_SIZE = 20 * 1024 * 1024; // 最大保存20MB
    private static boolean mLoggingEnabled = false;
    private static boolean mMscLogging = false;
    private static SimpleDateFormat mDateFormat = null;

    public static void setDebugLogging(boolean enabled) {
        mLoggingEnabled = enabled;
    }

    public static void setMscLogging(boolean flag) {
        mMscLogging = flag;
    }

    public static boolean isMscLogging() {
        return mMscLogging;
    }

    public static boolean isDebugLogging() {
        return mLoggingEnabled;
    }

    public static int v(String tag, String msg) {
        int result = 0;
        if (mLoggingEnabled) {
            result = Log.v(tag, msg);
        }
        return result;
    }

    public static int v(String tag, String msg, Throwable tr) {
        int result = 0;
        if (mLoggingEnabled) {
            result = Log.v(tag, msg, tr);
        }
        return result;
    }

    public static int d(String tag, String msg) {
        int result = 0;
        if (mLoggingEnabled) {
            result = Log.d(tag, msg);
        }
        return result;
    }

    public static int d(String tag, String msg, Throwable tr) {
        int result = 0;
        if (mLoggingEnabled) {
            result = Log.d(tag, msg, tr);
        }
        return result;
    }

    public static int i(String tag, String msg) {
        int result = 0;
        if (mLoggingEnabled) {
            result = Log.i(tag, msg);
        }
        return result;
    }

    public static int i(String tag, String msg, Throwable tr) {
        int result = 0;
        if (mLoggingEnabled) {
            result = Log.i(tag, msg, tr);
        }
        return result;
    }

    public static int w(String tag, String msg) {
        int result = 0;
        if (mLoggingEnabled) {
            result = Log.w(tag, msg);
        }
        return result;
    }

    public static int w(String tag, String msg, Throwable tr) {
        int result = 0;
        if (mLoggingEnabled) {
            result = Log.w(tag, msg, tr);
        }
        return result;
    }

    public static int w(String tag, Throwable tr) {
        int result = 0;
        if (mLoggingEnabled) {
            result = Log.w(tag, tr);
        }
        return result;
    }

    public static int e(String tag, String msg) {
        int result = 0;
        if (mLoggingEnabled) {
            result = Log.e(tag, msg);
        }
        return result;
    }

    public static int e(String tag, String msg, Throwable tr) {
        int result = 0;
        if (mLoggingEnabled) {
            result = Log.e(tag, msg, tr);
        }
        return result;
    }

    /**
     * 保存日志到文件 不区分发布版本与测试版本，统一保存
     *
     * @param filePath    Log文件存放路径
     * @param logFileName Log文件名，每日保存一个
     * @param log         日志信息
     */
    public synchronized static void saveFileLog(String filePath, String logFileName, String log) {
        String file = filePath + "/" + logFileName;
        LogUtils.d("LogUtils", log);
        String logStr = getDate() + " " + log + "\n";
        int len = writeString(file, logStr, false);
        if (len > MAX_FILE_SIZE) {
            writeString(file, logStr, true);
        }
    }

    /**
     * 获取日期
     *
     * @return String
     */
    private static String getDate() {
        if (mDateFormat == null) {
            mDateFormat = new SimpleDateFormat("yyyy-MM-dd",
                    Locale.getDefault());
        }
        return mDateFormat.format(new Date());
    }

    /**
     * 保存文本到文件
     *
     * @param fileName // 文件名
     * @param text     // 文本内容
     * @param isWipe   // 是否擦除旧内容
     * @return int
     */
    private static int writeString(String fileName, String text, boolean isWipe) {
        int file_len = 0;
        try {
            File file = new File(fileName);
            // 增加目录判断
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile tmp_file = new RandomAccessFile(file, "rw");
            if (isWipe) {
                tmp_file.setLength(0);
            }
            tmp_file.seek(tmp_file.length());
            tmp_file.write(text.getBytes("utf-8"));
            file_len = (int) tmp_file.length();
            tmp_file.close();
        } catch (IOException e) {
            return file_len;
        }
        return file_len;
    }

}
