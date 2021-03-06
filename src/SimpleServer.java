/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 9/28/12
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.geom.Circle;


import java.io.IOException;


public class SimpleServer extends BasicGame
{
    private static SimpleCircle simpleCircle;

    public SimpleServer()
    {
        super("Simple Server");
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        simpleCircle = new SimpleCircle();
    }

    // This method is called every time the game window is redrawn
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        Circle serverCircle = new Circle(simpleCircle.x, simpleCircle.y, simpleCircle.r);
        g.draw(serverCircle);
    }

    // This method would handle events such as mouse movement and keypresses,
    // but we have no event based actions yet. The method is still included
    // because it must be implemented in order to extend BasicGame.
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {

    }

    public static void main(String []args) throws SlickException
    {
        com.esotericsoftware.kryonet.Server server = new com.esotericsoftware.kryonet.Server();
        Kryo kryo = server.getKryo();
        kryo.register(SimpleCircle.class);

        server.start();
        try
        {
            server.bind(54555, 54777);
        }
        catch (IOException io)
        {

        }

        server.addListener(new Listener()
        {
            public void received (Connection connection, Object object)
            {
                if (object instanceof SimpleCircle)
                {
                    // A simple circle is sent across the network, and the
                    // Slick circle object is created from the simple circle.
                    simpleCircle = (SimpleCircle)object;
                }
            }
        });

        AppGameContainer app = new AppGameContainer( new SimpleServer() );
        app.setDisplayMode(800, 600, false);
        app.start();
    }
}
