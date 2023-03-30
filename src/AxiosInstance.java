import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AxiosInstance {
    private String baseUrl;//基础url
    private Map<String, String> headers;//请求头
    private int timeout;//超时时间

    public AxiosInstance(String baseUrl, Map<String, String> headers, int timeout) {
        this.baseUrl = baseUrl;
        this.headers = headers;
        this.timeout = timeout;
    }

    public static Map<String, Object> jsonToMap(String jsonStr) {
        Map<String, Object> map = new HashMap<>();
        int len = jsonStr.length();
        if (len == 0) {
            return map;
        }
        int i = 0;
        while (i < len) {
            char c = jsonStr.charAt(i);
            if (c == '{') {
                int start = i;
                int end = jsonStr.indexOf('}', start);
                if (end == -1) {
                    throw new IllegalArgumentException("Invalid JSON string: " + jsonStr);
                }
                String content = jsonStr.substring(start + 1, end);
                String[] parts = content.split(",");
                for (String part : parts) {
                    String[] kv = part.split(":");
                    if (kv.length != 2) {
                        throw new IllegalArgumentException("Invalid JSON string: " + jsonStr);
                    }
                    String key = kv[0].trim();
                    if (key.startsWith("\"")) {
                        key = key.substring(1, key.length() - 1);
                    }
                    String valueStr = kv[1].trim();
                    if ("null".equals(valueStr)) {
                        map.put(key, null);
                    } else if (valueStr.startsWith("\"") && valueStr.endsWith("\"")) {
                        String value = valueStr.substring(1, valueStr.length() - 1);
                        map.put(key, value);
                    } else if (valueStr.contains(".")) {
                        double value = Double.parseDouble(valueStr);
                        map.put(key, value);
                    } else {
                        long value = Long.parseLong(valueStr);
                        map.put(key, value);
                    }
                }
                i = end + 1;
            } else if (c == ',' || c == ' ' || c == '\t' || c == '\n' || c == '\r') {
                i++;
            } else {
                throw new IllegalArgumentException("Invalid JSON string: " + jsonStr);
            }
        }
        return map;
    }

    public AxiosResponse request(AxiosRequest request) {
        StringBuffer response = null;
        int responseCode = 0;
        try {
            // 创建 URL 对象，指定发送请求的目标地址
            URL url = new URL(baseUrl + request.getUrl());
            // 创建 HttpURLConnection 对象，设置请求方式为 GET
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(request.getMethod());
            // 设置请求头信息
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }
            // 获取服务器响应码
            responseCode = con.getResponseCode();

            // 读取响应内容
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new AxiosResponse(responseCode, new HashMap<>(), jsonToMap(response.toString()));
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
