package utility;

public class Payload {
    public static String CreateBody(){
        return "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
    }
    public static String UpdateBody(){
        return "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
    }
    public static String InvalidRegisterBody(){
        return "{\n" +
                "    \"email\": \"sydney@fife\"\n" +
                "}";
    }
    public static String ValidRegisterBody(){
        return "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
    }
    public static String LoginBody(){
     return   "{\n" +
                "\"email\": \"eve.holt@reqres.in\",\n" +
                "\"password\": \"cityslicka\"\n" + "}";
    }
    public static String InvalidLoginBody(){
     return "{\n" +
             "\"email\": \"peter@klaven\",\n" +
             "}";
    }
}
