package HTTPserver.httpservlet;


import HTTPserver.HTTpServlet;
import HTTPserver.Request;
import HTTPserver.Response;

public class Hello extends HTTpServlet {
    @Override
    public void DoGet(Request req, Response resp) {
        resp.setHeaders("Content-Type","application/javascript;charset=utf-8");
        resp.println("alert('你好')");
    }
}
