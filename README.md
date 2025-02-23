# Assignment 1 Report
Name: `Jonathan Rodriguez`
NetID: `jdr220004`
Section: `501`

### Implementation Explanation

I started by creating the LinkedList and the ArrayList. Although the inputs are all strings, I chose to use generics to future proof this program.

I added only the most necessary methods for these 2 classes, those being: add, removeLast, and getters for LinkedList and add, remove, resize, and getters for ArrayList.

After that comes the Stack and Queue classes which use their parents (Linked and Array) methods to create their own methods. Pop and Push for Stack followed by enqueue and dequeue for the queue.
> Again, only the most basic methods that are needed for the rest of the program to work are implemented.

All 4 of these classes implement Iterable and a custom iterator. The Stack Iterator is implemented in a separate class but follows the structure of the LinkedList iterator.

The main part of the program is in the BrowswerNavigation class. All of the custom methods mentioned in the instructions are in this class.

> `visitWebsite()` --> a website will always be put into history, the current website will be put on the backstack, and the current page will switch to the most recent. The forward stack should always reset here since there is a new first page.

> `openUrl()` --> method to open the URL in a real browser.

> `goBack()` and `goForward()` --> both these methods manage their forward and backward stacks independently and navigate to the proper page, either infont or behind the current page

> `showHistory()` --> uses a `StringBuilder` to more easily append the webpages together so they can be printed 

> `clearHistory()` --> ONLY clears the `historyQueue` and not any of the stacks. In a real browser, the forward and backward motion is managed within the cache and not the history, therefore clearing the stacks should not happen here.

> `closeBrowser()` and `restoreLastSession()` --> call on their respective methods to function properly

> `saveSession()` --> this class saves the current status of all stacks and queue within a txt file named `session_data.txt`. It uses a buffered write in combination with a file writer to write the information out into the session_data.txt file. I chose a simple format `DATASTRUCT:data,data,`

> `loadSession()` --> searches for the `session_data.txt` file made by `saveSession()`. Uses regex to split the txt file into readable text to be put into the respective stacks and queue. 

### Test Cases

*For test cases I have chosen to have the user interact with the program as testing. I felt this to be the best way to test this program. I have tested every possible combination of methods (1-8) and everything works.*

For the test cases, I kept it simple. A loop is continuously prompting the user with the option to test a specific function in BrowswerNavigation. After the user makes a decision, the integer choice gets put into a switch case statement, each number representing a different method. 
> the `visitPage()` function is used in options 1, 2, and 3 to prompt the user if they want to visit the webpage

Each method the user called is then called in the program, displaying in the terminal all the text the methods are supposed to display. This allows the user to navigate and read in real time what the program is doing.

As mentioned previously, with my implementation of the `main()` class, testing this program is just using the program which will be demonstrated in the project demo.

### Time Complexity
#### BrowserLinkedlist:
