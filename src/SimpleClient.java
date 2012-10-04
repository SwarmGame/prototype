/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 10/2/12
 * Time: 9:16 PM
 *
 * A simple client class that creates a circle object and passes it to the server
 */

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryo.Kryo;

import java.io.IOException;


public class SimpleClient
{
    public static void main(String []args)
    {
        // Set up the server
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

        SimpleCircle simpleCircle = createCircle();
        client.sendTCP(simpleCircle);
    }

    // Creates a circle in a random position
    private static SimpleCircle createCircle()
    {
        SimpleCircle simpleCircle = new SimpleCircle();
        simpleCircle.x = (int)(Math.random()*500);
        simpleCircle.y = (int)(Math.random()*500);
        simpleCircle.r = 10;
        return simpleCircle;
    }
}