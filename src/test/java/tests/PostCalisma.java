package tests;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import pojos.calisma1Pojo.State;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

public class PostCalisma {
    Response responseBeforePost;
    Response responsePost;
    Response responseAfterPost;
    List<Map<String,Object>> statesData;
    JsonPath json,json2;
    String endpoint = "https://www.gmibank.com/api/tp-states/";

    @Test
    public void getResponseGenerator(){
        responseBeforePost = given().
                                accept(ContentType.JSON).
                                auth().
                                oauth2(ConfigurationReader.getProperty("token")).
                                when().
                                get(endpoint);
    }
    @Test
    public void PostResponseGenerator(){
        State state = new State("yalova");
        responsePost =  given().
                        contentType(ContentType.JSON).
                        auth().
                        oauth2(ConfigurationReader.getProperty("token")).
                        body(state).
                        when().
                        post(endpoint);
        responsePost.prettyPrint();
    }
    
    @Test (dependsOnMethods = "getResponseGenerator")
    public void beforePostTest1(){
        json = responseBeforePost.jsonPath();
        statesData = json.getList("$");
        System.out.println(statesData.get(0).get("name"));
        System.out.println(statesData.get(0).get("id"));
    }

    @Test (dependsOnMethods = "getResponseGenerator")
    public void afterPostTest(){
        json = responseBeforePost.jsonPath();
        statesData = json.getList("$");
        System.out.println(statesData.get(statesData.size()-1).get("name"));
    }

}
