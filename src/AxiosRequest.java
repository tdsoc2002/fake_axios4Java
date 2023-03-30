import java.util.Map;

public class AxiosRequest {
    private String method;
    private String url;
    private Map<String, String> headers;
    private Map<String, String> params; // 添加参数字段

    public AxiosRequest(String method, String url, Map<String, String> params, Map<String, String> headers) {
        this.method = method;
        this.url = url;
        this.params = params; // 初始化参数字段
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                url = url.replace("{" + key + "}", value);
            }
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
