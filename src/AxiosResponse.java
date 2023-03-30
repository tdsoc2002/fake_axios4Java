import java.util.Map;

public class AxiosResponse {
    private int status;//状态
    private Map<String, String> headers;//返回头
    private Object data;//返回体

    public AxiosResponse(int status, Map<String, String> headers, Object data) {
        this.status = status;
        this.headers = headers;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
