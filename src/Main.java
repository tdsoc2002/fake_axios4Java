import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 创建请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        // 创建 Axios 实例
        AxiosInstance axios = new AxiosInstance("https://v1.hitokoto.cn", headers, 5000);
        // 创建请求参数
        /*
        句子类型（参数）
        参数	说明
        a	动画
        b	漫画
        c	游戏
        d	文学
        e	原创
        f	来自网络
        g	其他
        h	影视
        i	诗词
        j	网易云
        k	哲学
        l	抖机灵
        其他	作为 动画 类型处理
         */
        Map<String, String> params = new HashMap<>();
        params.put("c", "c");
        // 创建 Axios 请求
        AxiosRequest request = new AxiosRequest("GET", "/?c={c}", params, null);
        // 发送请求
        AxiosResponse response = axios.request(request);
        // 打印响应内容
        System.out.println(response.getData());
        //获取文字内容
        HashMap data = (HashMap) response.getData();
        System.out.println(data.get("hitokoto"));
    }
}
