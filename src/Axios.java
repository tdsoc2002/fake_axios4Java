import java.util.Map;

public class Axios {
    private String baseUrl;
    private Map<String, String> headers;
    private int timeout;

    public Axios() {
        // 默认构造函数
    }

    public Axios(String baseUrl, Map<String, String> headers, int timeout) {
        this.baseUrl = baseUrl;
        this.headers = headers;
        this.timeout = timeout;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
