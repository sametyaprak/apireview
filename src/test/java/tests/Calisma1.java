package tests;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import pojos.calisma1Pojo.Booking;
import pojos.calisma1Pojo.User;
import utilities.ConfigurationReader;
import utilities.TestBase;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

public class Calisma1 extends TestBase {

    Response responseGet;
    Response responsePost;
    Response responseGetAfterPost;
    String endopoint = "https://www.gmibank.com/api/tp-customers/";
    JsonPath jsonPath;

    @Test
    public void test1(){
        responseGet = given().
                accept(ContentType.JSON).
                spec(spec02).
                when().
                get();

        responseGet.prettyPrint();
        responseGet.then().assertThat().statusCode(200);
        responseGet.then().assertThat().body("data[0].id", Matchers.equalTo("1"));

        JsonPath json = responseGet.jsonPath();
        List<String> myList = json.getList("data.employee_salary");
        System.out.println(myList);
        int sum =0;
        int lowestMoney =Integer.valueOf(myList.get(0));
        for(int i=0;i<myList.size();i++){
            sum = sum +Integer.valueOf(myList.get(i));
            if(lowestMoney>Integer.valueOf(myList.get(i))){
                lowestMoney=Integer.valueOf(myList.get(i));
            }
        }
        System.out.println("toplam maaslar: " + sum);
        System.out.println("ortalama: " + sum/myList.size());
        System.out.println("en dusuk maas: " + lowestMoney);
    }

    @Test
    public void testGetMitJsonPath(){
        User user = new User();
        Booking booking = new Booking();
        responseGet = given().
                accept(ContentType.JSON).
                auth().oauth2(ConfigurationReader.getProperty("token")).
                when().
                get(endopoint);
        //response.prettyPrint();
        jsonPath = responseGet.jsonPath();
        List<Map<String,Object>> myAllData = jsonPath.getList("$");
        //System.out.println(myAllData);
        System.out.println(myAllData.get(2).get("phoneNumber"));
    }
    @Test
    public void testPostMitJsonPath(){


    }
}
