import java.io.*;
import org.json.*;
import okhttp3.*;


public class OkHttpTest {
	
  OkHttpClient client = new OkHttpClient();
 
  String run(HttpUrl url) throws IOException {
    Request request = new Request.Builder()
      .url(url)
      .build();
        
    System.out.println("url = " + url);
 
    Response response = client.newCall(request).execute();
    return response.body().string();
  }
  
  public static void main(String[] args) throws IOException {
    
    // {"board":["","","","","","","","",""],"currentPlayer":"X"}
    
    JSONArray boardArray = new JSONArray();
    for(int i = 0; i < 9; i++) {
      boardArray.put("");
    }
    
    JSONObject board2 = new JSONObject();
    board2.put("board", boardArray);
    board2.put("currentPlayer", "X");
    
    System.out.println("board2 = " + board2);
    
    
    
      
    HttpUrl url = new HttpUrl.Builder()
      .scheme("http")
      .host("bada.ai")
      .addPathSegment("tictactoe")
      .addPathSegment("linkRequestStd.jsp")
      .addQueryParameter("board", board2.toString())
      .build();

    while(true) {
      String responseString = new OkHttpTest().run(url);
      System.out.println(responseString);
    }
    
  }
}