import java.io.*;
import java.util.*;
import org.json.*;

public class Data {

    // Create a method to add a new user to the users.json file
    public static void addUser(String username, String password) {
        // Add to the users.json file in the format: { "username": "John", "password": "john01", "high_score": 0, "coins": 0 }
        JSONObject user = new JSONObject();
        user.put("username", username);
        user.put("password", password);
        user.put("high_score", 0);
        user.put("coins", 0);

        // Read the users.json file
        JSONArray users = new JSONArray();
        try {
            FileReader reader = new FileReader("users.json");
            JSONTokener tokener = new JSONTokener(reader);
            users = new JSONArray(tokener);
            users.put(user);
            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading users.json file");
        }

        // Delete the contents of the users.json file
        try {
            FileWriter writer = new FileWriter("users.json");
            writer.write("");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error deleting users.json file");
        }

        // Write to the users.json file
        try {
            FileWriter writer = new FileWriter("users.json", true);
            writer.write(users.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("Error writing to users.json file");
        }

    }

    // Create a method to check if a user exists in the users.json file
    public static boolean userExists(String username) {
        // Read the users.json file
        try {
            FileReader reader = new FileReader("users.json");
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray users = new JSONArray(tokener);
            reader.close();
            // Check if the username exists in the users.json file
            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                if (user.getString("username").equals(username)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading users.json file");
        }
        return false;
    }

    // Create a method to get a user's high_score from the users.json file
    public static int getHighScore(String username) {
        // Read the users.json file
        try {
            FileReader reader = new FileReader("users.json");
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray users = new JSONArray(tokener);
            reader.close();
            // Check if the username exists in the users.json file
            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                if (user.getString("username").equals(username)) {
                    return user.getInt("high_score");
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading users.json file");
        }
        return -1;
    }

    // Create a method to get a user's coins from the users.json file
    public static int getCoins(String username) {
        // Read the users.json file
        try {
            FileReader reader = new FileReader("users.json");
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray users = new JSONArray(tokener);
            reader.close();
            // Check if the username exists in the users.json file
            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                if (user.getString("username").equals(username)) {
                    return user.getInt("coins");
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading users.json file");
        }
        return -1;
    }

    // Create a method to check if both an inputted username and password exist, and if they match (login)
    public static boolean login(String username, String password) {
        // Read the users.json file
        try {
            FileReader reader = new FileReader("users.json");
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray users = new JSONArray(tokener);
            reader.close();
            // Check if the username exists in the users.json file
            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                if (user.getString("username").equals(username)) {
                    // Check if the password matches the username
                    if (user.getString("password").equals(password)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading users.json file");
        }
        return false;
    }

    // Create a method to update a user's high_score in the users.json file
    public static void updateHighScore(String username, int high_score) {
        // Read the users.json file
        try {
            FileReader reader = new FileReader("users.json");
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray users = new JSONArray(tokener);
            reader.close();
            // Check if the username exists in the users.json file
            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                if (user.getString("username").equals(username)) {
                    // Update the user's high_score
                    user.put("high_score", high_score);
                }
            }
            // Delete the contents of the users.json file
            try {
                FileWriter writer = new FileWriter("users.json");
                writer.write("");
                writer.close();
            } catch (Exception e) {
                System.out.println("Error deleting users.json file");
            }
            // Write to the users.json file
            try {
                FileWriter writer = new FileWriter("users.json", true);
                writer.write(users.toString());
                writer.close();
            } catch (Exception e) {
                System.out.println("Error writing to users.json file");
            }
        } catch (Exception e) {
            System.out.println("Error reading users.json file");
        }
    }

    // Create a method to update a user's coins in the users.json file
    public static void updateCoins(String username, int coins) {
        // Read the users.json file
        try {
            FileReader reader = new FileReader("users.json");
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray users = new JSONArray(tokener);
            reader.close();
            // Check if the username exists in the users.json file
            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                if (user.getString("username").equals(username)) {
                    // Update the user's coins
                    user.put("coins", coins);
                }
            }
            // Delete the contents of the users.json file
            try {
                FileWriter writer = new FileWriter("users.json");
                writer.write("");
                writer.close();
            } catch (Exception e) {
                System.out.println("Error deleting users.json file");
            }
            // Write to the users.json file
            try {
                FileWriter writer = new FileWriter("users.json", true);
                writer.write(users.toString());
                writer.close();
            } catch (Exception e) {
                System.out.println("Error writing to users.json file");
            }
        } catch (Exception e) {
            System.out.println("Error reading users.json file");
        }
    }

    // Create a method to generate and return a leaderboard by high scores.
    public static String getLeaderboard() {
        // Read the users.json file
        try {
            FileReader reader = new FileReader("users.json");
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray users = new JSONArray(tokener);
            reader.close();
            // Sort the users by high score
            for (int i = 0; i < users.length(); i++) {
                for (int j = 0; j < users.length() - 1; j++) {
                    JSONObject user1 = users.getJSONObject(j);
                    JSONObject user2 = users.getJSONObject(j + 1);
                    if (user1.getInt("high_score") < user2.getInt("high_score")) {
                        users.put(j, user2);
                        users.put(j + 1, user1);
                    }
                }
            }
            // Create a leaderboard string
            String leaderboard = "";
            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                leaderboard += user.getString("username") + " " + user.getInt("high_score") + "\n";
            }
            return leaderboard;
        } catch (Exception e) {
            System.out.println("Error reading users.json file");
        }
        return "";
    }
}
