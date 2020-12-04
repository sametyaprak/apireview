package tests;


import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class GetCalismasi01 {

    Response response;
    JsonPath jsonPath;
    String endPoint = "https://www.gmibank.com/api/tp-customers";
    int statusCOde =200;
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrYXJhayIsImF1dGgiOiJST0x" +
            "FX0FETUlOLFJPTEVfRU1QTE9ZRUUsUk9MRV9NQU5BR0VSIiwiZXhwIjoxNjA3MDI2M" +
            "DAwfQ.CdDFo_4PqPIHJZXuLai_w-rlq4B5xL_xVVjS1BHsC5MLVYqXVyiNuUSYuIv7SR7mRWev5dq2Bl7cDN6huUBeZQ";


    @Test
    public void test01(){
            response = given().
                        accept(ContentType.JSON).
                        auth().oauth2(token).
                        when().
                        get(endPoint);
        Assert.assertEquals(response.getStatusCode(),statusCOde);
        jsonPath = response.jsonPath();
        List <Integer> myIdList = jsonPath.getList("id");
        System.out.println(myIdList);
        System.out.println(myIdList.size());
        int sum =0;
        for(Integer w : myIdList){
            if(w>22000){
                sum++;
            }
        }
        System.out.println(sum);
        Response responseSeven = given().auth().oauth2(token).when().get(endPoint+"/"+myIdList.get(7));
        responseSeven.prettyPrint();
    }
    @Test
    public void test02(){
        response = given().
                accept(ContentType.JSON).
                auth().oauth2(token).
                when().
                get(endPoint);
        Assert.assertEquals(response.getStatusCode(),statusCOde);
        jsonPath = response.jsonPath();
        List<Map<String,Object>> myAllData = jsonPath.getList("$");
        System.out.println(myAllData.get(2).get("user"));
        String data = jsonPath.getString("user[2].login");
        System.out.println("jason pat ile: " + data);

    }
}
