import base.BaseClass;
import helper.ExceptionHandling;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ReadApiResponseOnWebpageWithUI extends BaseClass
{
    @Test(priority = 1)
    public void getresponse()
    {
        String api="https://qa134.myimagine.com/orders/CustomerBo?$dataAccess=ALL&$filter=%7B%22operator%22:%22AND%22,%22criteria%22:%5B%7B%22operator%22:%22AND%22,%22criteria%22:%5B%7B%22fieldName%22:%22active%22,%22operator%22:%22EQUALS%22,%22value%22:%22A%22%7D,%7B%22fieldName%22:%22orderedAmount%22,%22operator%22:%22GREATER_EQUALS_THAN%22,%22value%22:0.01%7D%5D%7D,%7B%22value%22:%22F%22,%22operator%22:%22NOTEQUAL%22,%22fieldName%22:%22type%22%7D%5D%7D&$selection=%5B%22id%22,%22type%22,%22custNumber%22,%22company%22,%22city%22,%22state%22,%22salesPerson.id%22,%22salesPerson.name%22,%22salesOffice.officeName%22,%22active%22,%22defaultPriority%22,%22activeOrders%22,%22orderedAmount%22,%22activeFiller%22,%22nccCustomerMap%22%5D&$skip=0&$sortBy=%5B%22-orderedAmount%22%5D&$top=100";
       try {
           // Make API request
           HttpClient httpClient = HttpClient.newHttpClient();
           HttpRequest httpRequest = HttpRequest.newBuilder()
                   .uri(URI.create(api))
                   .build();

           HttpResponse<String> apiResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
           System.out.println(apiResponse);

           // Parse API response (assuming it's in JSON format)
           String jsonResponse = apiResponse.body();
           // You may use a JSON parsing library like Gson or Jackson here
           // For simplicity, we'll just extract a value using a substring
           //String extractedValue = jsonResponse.substring(jsonResponse.indexOf("totalRows") + 9, jsonResponse.indexOf("value") - 3);
           String arr[]=jsonResponse.split("totalRows");
           String arr1[]=arr[1].split(":");
           String extractedValue =arr1[0];
           System.out.println(extractedValue);
       }
       catch (Exception e)
       {
           ExceptionHandling.handleException(e);
       }
    }
    }

