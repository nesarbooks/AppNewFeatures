package restasure.restaureapi;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class googleapi {
	private static final String YOUR_AUTHENTICATION_TOKEN_GOES_HERE = null;
	static WebDriver driver;
  @Test
  public void test_NumberOfCircuitsFor2017Season_ShouldBe20() {
      
	    given().
	    when().
	        get("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=mongolian%20grill&inputtype=textquery&fields=photos,formatted_address,name,opening_hours,rating&locationbias=circle:2000@47.6918452,-122.2226413&key=YOUR_API_KEY").
	    then().
	        assertThat().
	        body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
	}
  @Test
  public void test_ResponseHeaderData_ShouldBeCorrect() {
          
      given().
      when().
          get("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=mongolian%20grill&inputtype=textquery&fields=photos,formatted_address,name,opening_hours,rating&locationbias=circle:2000@47.6918452,-122.2226413&key=YOUR_API_KEY").
      then().
          assertThat().
          statusCode(200).
      and().
          contentType(ContentType.JSON).
      and().
          header("Content-Length",equalTo("4567"));
  }
  @Test
  public void test_Md5CheckSumForTest_ShouldBe098f6bcd4621d373cade4e832627b4f6() {
      
      String originalText = "test";
      String expectedMd5CheckSum = "098f6bcd4621d373cade4e832627b4f6";
          
      given().
          param("text",originalText).
      when().
          get("http://md5.jsontest.com").
      then().
          assertThat().
          body("md5",equalTo(expectedMd5CheckSum));
  }
  @Test
  public void test_NumberOfCircuits_ShouldBe20_Parameterized() {
          
      String season = "2017";
      int numberOfRaces = 20;
          
      given().
          pathParam("raceSeason",season).
      when().
          get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
      then().
          assertThat().
          body("MRData.CircuitTable.Circuits.circuitId",hasSize(numberOfRaces));
  }
  @DataProvider(name="seasonsAndNumberOfRaces")
  public Object[][] createTestDataRecords() {
      return new Object[][] {
          {"2017",20},
          {"2016",21},
          {"1966",9}
      };
  }
  @Test(dataProvider="seasonsAndNumberOfRaces")
  public void test_NumberOfCircuits_ShouldBe_DataDriven(String season, int numberOfRaces) {
                  
      given().
          pathParam("raceSeason",season).
      when().
          get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
      then().
          assertThat().
          body("MRData.CircuitTable.Circuits.circuitId",hasSize(numberOfRaces));
  }
  @Test
  public void test_APIWithBasicAuthentication_ShouldBeGivenAccess() {
          
      given().
          auth().
          preemptive().
          basic("username", "password").
      when().
          get("http://path.to/basic/secured/api").
      then().
          assertThat().
          statusCode(200);
  }
  @Test
  public void test_APIWithOAuth2Authentication_ShouldBeGivenAccess() {
          
      given().
          auth().
          oauth2(YOUR_AUTHENTICATION_TOKEN_GOES_HERE).
      when().
          get("http://path.to/oath2/secured/api").
      then().
          assertThat().
          statusCode(200);
  }
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Yoga\\eclipse-workspace\\Automation_aps_code\\Drivers\\chromedriver.exe");
      driver=new ChromeDriver();
      driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
  }

}
