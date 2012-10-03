/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 10/2/12
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryo.Kryo;

import java.io.IOException;


public class SimpleClient {

    public static void main(String []args){

        Client client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(Circle.class);


        client.start();
        boolean stop = false;

        try{
            client.connect(5000, "localhost", 54555, 54777);
        }
        catch (IOException io)
        {

        }

        Circle circle = new Circle();
        circle.x = 1;
        circle.y = 2;
        client.sendTCP(circle);
    }
}