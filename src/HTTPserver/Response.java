package HTTPserver;


import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


public class Response {
    String status="200 OK";
    Map<String,String >headers=new HashMap<>();
    StringBuilder bodyBuilder=new StringBuilder();

    Response(){
        headers.put("Content-Type","text/html;charset=utf-8");
    }
    public void setStatus(String status){
        this.status=status;
    }
    public void setHeaders(String k,String v){
        headers.put(k,v);
    }
    public void print(String s){
        bodyBuilder.append(s);
    }
    public void println(String s){
        bodyBuilder.append(s);
        bodyBuilder.append("\r\n");
    }
    public void writeAndFlush(OutputStream os) throws IOException {
        String response=buildResponse();
        os.write(response.getBytes("utf-8"));
        os.flush();
    }
    private String buildResponse()throws IOException {
        StringBuilder sb=new StringBuilder();
        //响应行
        sb.append("HTTP/1.0 ");
        sb.append(status);
        sb.append("\r\n");
        //响应头
        int ContentLength=bodyBuilder.toString().getBytes("utf-8").length;
        setHeaders("Content-Length",String.valueOf(ContentLength));
        for(Map.Entry<String,String >entry:headers.entrySet()){
            sb.append(entry.getKey());
            sb.append(":");
            sb.append(entry.getValue());
            sb.append("\r\n");
        }
        sb.append("\r\n");
        sb.append(bodyBuilder);
        return sb.toString();
    }
}
