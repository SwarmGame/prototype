/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 9/28/12
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
import static com.esotericsoftware.kryonet.*;

import java.io.IOException;

public class hello {

    public static void main(String []args){
        Server server = new Server();
        server.start();
        try{
            server.bind(54555, 54777);
        }
        catch (IOException io)
        {

        }
        while(true){}
    }
}
