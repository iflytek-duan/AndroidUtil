package crazy.zihao.androidutil.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * ClassName：AppUtils
 * Description：TODO<App工具类>
 * Author：zihao
 * Date：2017/7/12 15:30
 * Email：crazy.zihao@gmail.com
 * Version：v1.0
 */
public class AppUtils {

    private static final String TAG = AppUtils.class.getSimpleName();

    /**
     * 获取应用版本名称
     *
     * @param context 上下文对象
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String versionName = info.versionName;
            LogUtils.i(TAG, "versionName：" + versionName);
            return versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取应用版本号
     *
     * @param context 上下文对象
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int versionCode = info.versionCode;
            LogUtils.i(TAG, "versionCode：" + versionCode);
            return versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

}
