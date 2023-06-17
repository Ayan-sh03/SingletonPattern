package myVac;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileReader;

public class JsonFileReader {
    private String name;
    private String email;
    private int age;
    private String address;
    
    private static JsonFileReader instance;
    
    // Private constructor to prevent instantiation from outside the class
    private JsonFileReader() {
    }
    
    public static JsonFileReader getInstance() {
        if (instance == null) {
            synchronized (JsonFileReader.class) {
                if (instance == null) {
                    instance = new JsonFileReader();
                }
            }
        }
        return instance;
    }

    public void readJsonFile(String filePath) {
        String jsonContent = readFileContent(filePath);
        if (jsonContent != null) {
            JsonObject jsonObject = JsonParser.parseString(jsonContent).getAsJsonObject();
            setName(jsonObject.get("name").getAsString());
            setAge(jsonObject.get("age").getAsInt());
            setEmail(jsonObject.get("email").getAsString());
            setAddress(jsonObject.get("address").getAsString());
        }
    }

    private static String readFileContent(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
    
    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public static void main(String[] args) {
    	String filePath = "C:\\Users\\SAMSUNG\\OneDrive\\Desktop\\temp.json";
    	JsonFileReader.getInstance().readJsonFile(filePath);

    	// Accessing the values
    	String name = JsonFileReader.getInstance().getName();
    	String email = JsonFileReader.getInstance().getEmail();
    	int age = JsonFileReader.getInstance().getAge();
    	String address = JsonFileReader.getInstance().getAddress();

    	System.out.println(name+"\n"+email+"\n"+age+"\n"+address);
	}
}
