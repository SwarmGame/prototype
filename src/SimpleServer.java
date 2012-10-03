/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 9/28/12
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;


import java.io.IOException;


public class SimpleServer {

    public static void main(String []args){
        com.esotericsoftware.kryonet.Server server = new com.esotericsoftware.kryonet.Server();
        Kryo kryo = server.getKryo();
        kryo.register(Circle.class);

        server.start();
        try{
            server.bind(54555, 54777);
        }
        catch (IOException io)
        {

        }
        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof Circle) {
                    Circle circle = (Circle)object;
                    System.out.println(circle.x);
                    System.out.println(circle.y);
                }
                }
        });
        while(true){}
    }
}
