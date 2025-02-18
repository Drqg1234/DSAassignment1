import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class BrowswerNavigation {
    public BrowserStack<String> backStack;
    public BrowserStack<String> forwardStack;
    public BrowserQueue<String> historyQueue;
    public String curPage;

    public BrowswerNavigation(){
        backStack = new BrowserStack<>();
        forwardStack = new BrowserStack<>();
        historyQueue = new BrowserQueue<>();
        curPage = null;
    }

    public String visitWebsite(String url){
        if (curPage != null){
            backStack.push(curPage);
        }
        curPage = url;
        forwardStack = new BrowserStack<>();
        historyQueue.enqueue(url);
        return "Now at " + url;
    }

    public String goBack(){
        if (backStack.isEmpty()){
            throw new java.util.EmptyStackException();
        }
        forwardStack.push(curPage);
        curPage = backStack.pop();
        return "Now at " + curPage;
    }

    public String goForward(){
        if (forwardStack.isEmpty()){
            throw new java.util.EmptyStackException();
        }
        backStack.push(curPage);
        curPage = forwardStack.pop();
        return "Now at " + curPage;
    }

    public String showHistory(){
        if (historyQueue.isEmpty()){
            return "No browsing history available.";
        }
        StringBuilder history = new StringBuilder("Browsing History:\n");
        for (String url : historyQueue){
            history.append(url).append("\n");
        }
        return history.toString();
    }

    public String clearHistory(){
        historyQueue = new BrowserQueue<>();
        return "Browsing history cleared.";
    }

    public String closeBrowser(){
        saveSession();
        return "Browser session saved.";
    }

    public String restoreLastSession(){
        return loadSession() ? "Previous session restored." : "No previous session found.";
    }

    private void saveSession(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("session_data.txt"))) {
            // Save back stack
            writer.write("BACK_STACK:");
            for (String url : backStack) {
                writer.write(url + ",");
            }
            writer.newLine();

            // Save forward stack
            writer.write("FORWARD_STACK:");
            for (String url : forwardStack) {
                writer.write(url + ",");
            }
            writer.newLine();

            // Save history queue
            writer.write("HISTORY_QUEUE:");
            for (String url : historyQueue) {
                writer.write(url + ",");
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private boolean loadSession(){
        File file = new File("session_data.txt");
        if (!file.exists()){
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length < 2) continue;
                String type = parts[0];
                String[] urls = parts[1].split(",");

                switch (type) {
                    case "BACK_STACK" -> {
                        for (String url : urls) {
                            if (!url.isEmpty()) backStack.push(url);
                        }
                    }
                    case "FORWARD_STACK" -> {
                        for (String url : urls) {
                            if (!url.isEmpty()) forwardStack.push(url);
                        }
                    }
                    case "HISTORY_QUEUE" -> {
                        for (String url : urls) {
                            if (!url.isEmpty()) historyQueue.enqueue(url);
                        }
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
