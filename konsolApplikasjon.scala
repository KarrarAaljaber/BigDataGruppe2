import java.io._
import org.apache.commons._

import org.apache.http.{ io => _, _ }
import org.apache.http.client._
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import java.util.ArrayList
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import com.google.gson.Gson



case class ConsoleApp( name: String, country: String, income: Int, age: Int)


object konsolApplikasjon extends App {


    println("Welcome to this program :)")
    println("Please enter your name, country, income and age ")
    val name = readLine("Enter your name: ")
    val country = readLine("Enter your country: ")
    print("Enter your yearly income: ")
    val income = readInt()
    print("Enter your age: ")
    val age = readInt()

    val app = new ConsoleApp(name, country, income, age)
    
    val post = new HttpPost("http://127.0.0.1:2379/v2/keys/App/" + name.replaceAll("\\s", "-"))
    val appJson = new Gson().toJson(app)

    val nameValuePairs = new ArrayList[NameValuePair]()
    nameValuePairs.add(new BasicNameValuePair("value", appJson))
    post.setEntity(new UrlEncodedFormEntity(nameValuePairs))

    val client = new DefaultHttpClient
    val response = client.execute(post)
    println("--- HEADERS ---")
    response.getAllHeaders.foreach(arg => println(arg))


}
