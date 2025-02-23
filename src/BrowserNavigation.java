import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;


public class BrowserNavigation {
    public BrowserStack<String> backStack;
    public BrowserStack<String> forwardStack;
    public BrowserQueue<String> historyQueue;
    public String curPage;

    public BrowserNavigation(){
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
        return "Now at: " + url;
    }

    // Very basic URL opening, it needs to be exactly as it is in the url of a browser (https://www.youtube.com)
    public void openUrl(String url){
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI(url));
        }
        catch (Exception e) {
            System.out.println("Not a valid link, cannot open webpage in search engine!");
        }
    }


    public String goBack(){
        if (backStack.isEmpty()){
            throw new java.util.EmptyStackException();
        }
        forwardStack.push(curPage);
        curPage = backStack.pop();
        return "Now at: " + curPage;
    }

    public String goForward(){
        if (forwardStack.isEmpty()){
            throw new java.util.EmptyStackException();
        }
        backStack.push(curPage);
        curPage = forwardStack.pop();
        return "Now at " + curPage;
    }

    // Uses a StringBuilder to make string appending and printing easier
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

    // Clearing history in a real browser only clears the history and not the cache, so theres no need to clear the backstack
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
        } 
        catch (IOException e) {
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

                // Parsing the string data in the session_data.txt file for adding
                String[] parts = line.split(":");
                if (parts.length < 2) {
                    continue;
                }
                String type = parts[0];
                String[] urls = parts[1].split(",");

                // Appends previous information to the current stacks instead of clearing and adding (don't want to lose current data)
                switch (type) {
                    case "BACK_STACK" -> {
                        for (String url : urls) {
                            if (!url.isEmpty()) {
                                backStack.push(url);
                            }
                        }
                    }
                    case "FORWARD_STACK" -> {
                        for (String url : urls) {
                            if (!url.isEmpty()){
                                forwardStack.push(url); 
                            } 
                        }
                    }
                    case "HISTORY_QUEUE" -> {
                        for (String url : urls) {
                            if (!url.isEmpty()) {
                                historyQueue.enqueue(url);    
                            }
                        }
                    }
                }
            }
            return true;
        } 
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
