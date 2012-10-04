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


public class SimpleClient
{

    public static void main(String []args)
    {

        Client client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(SimpleCircle.class);


        client.start();
        boolean stop = false;

        try
        {
            client.connect(5000, "localhost", 54555, 54777);
        }
        catch (IOException io)
        {

        }

        SimpleCircle simpleCircle = new SimpleCircle();
        simpleCircle.x = 500;
        simpleCircle.y = 500;
        simpleCircle.r = 10;
        client.sendTCP(simpleCircle);
    }
}