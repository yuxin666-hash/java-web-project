package example.util;

/**
 *  基于 ThreadLocal 保存当前请求的登录用户ID。
 *  由 TokenInterceptor 在解析令牌后写入，请求结束时清理。
 */
public class CurrentUserHolder {

    private static final ThreadLocal<Integer> CURRENT_ID = new ThreadLocal<>();

    public static void set(Integer id) {
        CURRENT_ID.set(id);
    }

    public static Integer get() {
        return CURRENT_ID.get();
    }

    /**
     * 必须在请求结束时调用，否则线程池复用会导致用户ID串号
     */
    public static void remove() {
        CURRENT_ID.remove();
    }
}
