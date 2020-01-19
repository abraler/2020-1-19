package HTTPserver;

import HTTPserver.httpservlet.Hello;

import java.io.IOException;
import java.net.Socket;
public class Task implements Runnable {
    private final Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            Request request=Request.parse(socket.getInputStream());
            System.out.println(request);
            Response response =new Response();

            if(request.path.equals("/hello")) {
                HTTpServlet servlet = new Hello();
                servlet.DoGet(request, response);
            }else{

            }
            response.writeAndFlush(socket.getOutputStream());
            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
