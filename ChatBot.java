
import java.util.*;

public class ChatBot {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        HashMap<String, List<String>> knowledge = new HashMap<>();
        knowledge.put("java", Arrays.asList(
                "Java is a high-level, object-oriented programming language.",
                "It runs on the JVM (Java Virtual Machine), making it platform-independent.",
                "Java is widely used in enterprise applications, Android apps, and web development."
        ));

        knowledge.put("jdk", Arrays.asList(
                "JDK stands for Java Development Kit.",
                "It provides tools to develop and run Java programs.",
                "Includes compiler (javac), Java libraries, and the JRE."
        ));

        knowledge.put("jre", Arrays.asList(
                "JRE stands for Java Runtime Environment.",
                "It allows running Java applications without development tools."
        ));

        knowledge.put("javac", Arrays.asList(
                "javac is the Java compiler that converts .java source files into bytecode (.class files)."
        ));

        knowledge.put("class", Arrays.asList(
                "A class is a blueprint for creating objects.",
                "It defines attributes (fields) and behaviors (methods)."
        ));

        knowledge.put("object", Arrays.asList(
                "An object is an instance of a class.",
                "It contains state (fields) and behavior (methods)."
        ));

        knowledge.put("inheritance", Arrays.asList(
                "Inheritance allows a class to acquire properties and methods of another class.",
                "Promotes code reuse and logical hierarchy."
        ));

        knowledge.put("polymorphism", Arrays.asList(
                "Polymorphism allows objects to take multiple forms.",
                "It enables method overriding and interface implementation."
        ));

        knowledge.put("encapsulation", Arrays.asList(
                "Encapsulation is keeping fields private and providing public getters/setters.",
                "It protects data and ensures controlled access."
        ));

        knowledge.put("abstraction", Arrays.asList(
                "Abstraction hides complex implementation details and shows only necessary functionality.",
                "Achieved using abstract classes or interfaces."
        ));

        knowledge.put("interface", Arrays.asList(
                "An interface defines methods a class must implement.",
                "It allows multiple classes to share behavior without inheritance."
        ));

        knowledge.put("array", Arrays.asList(
                "An array stores multiple values of the same type in a single variable.",
                "Index starts at 0 and length is fixed."
        ));

        knowledge.put("arraylist", Arrays.asList(
                "ArrayList is a resizable array implementation of the List interface.",
                "Allows dynamic addition and removal of elements."
        ));

        knowledge.put("linkedlist", Arrays.asList(
                "LinkedList stores elements in a doubly-linked structure.",
                "Useful for frequent insertions and deletions."
        ));

        knowledge.put("hashmap", Arrays.asList(
                "HashMap stores key-value pairs for fast retrieval.",
                "Allows null keys and values, not synchronized by default."
        ));

        knowledge.put("hashset", Arrays.asList(
                "HashSet stores unique elements without order.",
                "Useful to avoid duplicates."
        ));

        knowledge.put("exception", Arrays.asList(
                "Exceptions are events that disrupt normal program flow.",
                "Handled using try, catch, finally blocks or throws keyword."
        ));

        knowledge.put("thread", Arrays.asList(
                "A thread is a lightweight process for concurrent execution.",
                "Java supports multithreading using Thread class or Runnable interface."
        ));

        knowledge.put("synchronization", Arrays.asList(
                "Synchronization controls access to shared resources in multithreaded programs.",
                "Prevents race conditions and ensures data consistency."
        ));

        knowledge.put("lambda", Arrays.asList(
                "Lambda expressions provide a concise way to represent anonymous functions.",
                "Introduced in Java 8 for functional programming."
        ));

        knowledge.put("stream", Arrays.asList(
                "Streams provide a functional approach to process collections.",
                "Supports operations like filter, map, reduce, and collect."
        ));

        knowledge.put("package", Arrays.asList(
                "A package groups related classes and interfaces.",
                "Helps in organizing code and avoiding name conflicts."
        ));

        knowledge.put("import", Arrays.asList(
                "The import statement allows using classes from other packages."
        ));

        knowledge.put("static", Arrays.asList(
                "Static members belong to the class rather than an instance.",
                "Used for constants, utility methods, and shared data."
        ));

        knowledge.put("final", Arrays.asList(
                "The final keyword makes variables, methods, or classes unchangeable.",
                "A final class cannot be extended, a final method cannot be overridden."
        ));

        knowledge.put("abstract", Arrays.asList(
                "An abstract class cannot be instantiated directly.",
                "Can contain abstract methods that must be implemented in subclasses."
        ));

        knowledge.put("constructor", Arrays.asList(
                "A constructor initializes objects when a class is instantiated.",
                "It has the same name as the class and no return type."
        ));

        knowledge.put("this", Arrays.asList(
                "The 'this' keyword refers to the current object.",
                "Used to differentiate instance variables from parameters."
        ));

        knowledge.put("super", Arrays.asList(
                "The 'super' keyword refers to the parent class.",
                "Used to call parent class methods or constructors."
        ));

        knowledge.put("overloading", Arrays.asList(
                "Method overloading allows multiple methods with the same name but different parameters.",
                "Helps in code readability and reuse."
        ));

        knowledge.put("overriding", Arrays.asList(
                "Method overriding allows a subclass to provide its own implementation of a parent method.",
                "Achieves runtime polymorphism."
        ));

        knowledge.put("scanner", Arrays.asList(
                "Scanner is used to read input from the user or files.",
                "Supports nextInt(), nextLine(), nextDouble(), etc."
        ));

        knowledge.put("system.out.println", Arrays.asList(
                "Used to print output to the console.",
                "Commonly used for debugging and displaying messages."
        ));

        knowledge.put("java8", Arrays.asList(
                "Java 8 introduced lambda expressions, streams, and the new Date/Time API.",
                "Encourages functional programming in Java."
        ));

        HashMap<String, List<String>> convo = new HashMap<>();
        convo.put("hi", Arrays.asList("Hello!", "Hi there!", "Hey! How are you?"));
        convo.put("hello", Arrays.asList("Hi!", "Hello!", "Hey! Nice to meet you."));
        convo.put("hey", Arrays.asList("Hey!", "Hi there!", "Hello!"));
        convo.put("bye", Arrays.asList("Goodbye!", "See you later!", "Bye! Take care."));
        convo.put("exit", Arrays.asList("Exiting... Goodbye!", "See you soon!", "Bye!"));

        String lastTopic = ""; // memory
        HashMap<String, Integer> topicPointer = new HashMap<>(); // tracks follow-ups

        System.out.println("🤖 JavaBot is online! Ask me about Java. Type 'exit' to quit.\n");

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine().toLowerCase().trim();
            String response = "Hmm... I didn't understand that.";
            boolean matched = false;

            if (input.startsWith("teach ")) {
                try {
                    // Format: teach <topic>: <explanation>
                    String[] parts = input.substring(6).split(":", 2);
                    if (parts.length < 2) {
                        response = "Please provide the explanation after a colon (:). Example: teach recursion: Recursion is a function calling itself.";
                    } else {
                        String topic = parts[0].trim();
                        String explanation = parts[1].trim();
                        knowledge.putIfAbsent(topic, new ArrayList<>());
                        knowledge.get(topic).add(explanation);
                        response = "Got it! I learned something new about '" + topic + "'.";
                    }
                } catch (Exception e) {
                    response = "Oops! Something went wrong while learning.";
                }
                matched = true;
            }

            if (!matched && (input.contains("what is") || input.contains("explain") || input.contains("define"))) {
                for (String key : knowledge.keySet()) {
                    if (input.contains(key)) {
                        lastTopic = key;
                        int ptr = topicPointer.getOrDefault(lastTopic, 0);
                        response = knowledge.get(key).get(ptr);
                        ptr++;
                        topicPointer.put(lastTopic, ptr);
                        matched = true;
                        break;
                    }
                }
            }

            if (!matched && (input.contains("more") || input.contains("tell me more") || input.contains("give example"))) {
                if (!lastTopic.isEmpty() && knowledge.containsKey(lastTopic)) {
                    int ptr = topicPointer.getOrDefault(lastTopic, 0);
                    List<String> details = knowledge.get(lastTopic);
                    if (ptr < details.size()) {
                        response = details.get(ptr);
                        ptr++;
                        topicPointer.put(lastTopic, ptr);
                    } else {
                        response = "No more details on '" + lastTopic + "'!";
                    }
                    matched = true;
                } else {
                    response = "I don't remember what we were talking about. Ask me a Java question first!";
                    matched = true;
                }
            }

            if (!matched) {
                for (String key : convo.keySet()) {
                    if (input.contains(key)) {
                        List<String> options = convo.get(key);
                        response = options.get(rand.nextInt(options.size()));
                        matched = true;
                        break;
                    }
                }
            }

            System.out.println("JavaBot: " + response);

            if (input.contains("exit") || input.contains("bye")) {
                break;
            }
        }

        sc.close();
    }
}
