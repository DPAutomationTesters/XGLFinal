import base.BaseClass;
import dataProvider.ConfigReader;
import helper.ExceptionHandling;
import org.junit.runner.Description;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class pingTest extends BaseClass
{
    public static void sendPingRequest(String iPAddress) throws IOException {
        InetAddress in= InetAddress.getByName(iPAddress);
        Reporter.log("Sending ping request to "+iPAddress);
        if(in.isReachable(5000))
            System.out.println("Host is reachable");
        else
            System.out.println("Sorry ! We can't reach to this host");
    }

    @Test(groups = {"Ping tests"})
    public void pingTest()
    {
        String ip= ConfigReader.getPropertyvalue("pingip");
        String arr[]=ip.split(";");
        for(int i=0;i<=arr.length-1;i++)
        {
            try{
                sendPingRequest(arr[i]);
            }catch (Exception e)
            {
                System.out.println(ExceptionHandling.handleException(e));
            }
        }

    }
}
