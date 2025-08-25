
import java.util.*;

public class TestChatbot {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input, response;
        String[] greetings = {"hi", "hello", "hey"};
        String[] farewells = {"bye", "exit"};

        HashMap<String, List<String>> knowledge = new HashMap<>();
        knowledge.put("java", Arrays.asList(
                "Java is a high-level, object-oriented programming language.",
                "It runs on the JVM, making it platform-independent."
        ));
        knowledge.put("jdk", Arrays.asList(
                "JDK is the Java Development Kit with tools to develop Java programs."
        ));
        knowledge.put("jre", Arrays.asList(
                "JRE is the Java Runtime Environment, used to run Java programs."
        ));
        String lastTopic = "";
        System.out.println("Welcome to JavaBot! Type 'exit' to quit.");

        while (true) {
            System.out.print("You: ");
            input = sc.nextLine().toLowerCase().trim();
            response = "Hmm... I didn't understand that.";
            for (String g : greetings) {
                if (input.contains(g)) {
                    response = "Hello! How can I help you with Java?";
                    break;
                }
            }
            for (String f : farewells) {
                if (input.contains(f)) {
                    response = "Goodbye! Happy coding!";
                    System.out.println("JavaBot: " + response);
                    return; // Exit program
                }
            }
            for (String key : knowledge.keySet()) {
                if (input.contains(key)) {
                    response = knowledge.get(key).get(0);
                    lastTopic = key;
                    break;
                }
            }
            if (input.contains("tell me more") && !lastTopic.isEmpty()) {
                List<String> parts = knowledge.get(lastTopic);
                if (parts.size() > 1) {
                    response = parts.get(1);
                } else {
                    response = "No more info available.";
                }
            }
            System.out.println("JavaBot: " + response);
        }
    }
}
