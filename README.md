# Learning-Question-Game
* This program implements a game similar to 20 Questions. The user thinks of an object and the computer then tries to guess the object based off of yes or no questions. 
* This is done by using and extending a binary decision tree read in NLR (pre-order) from a .txt file.
* The decision tree contains nodes with a string of text, a type flag (question/answer), and pointers to a left (yes) and right (no) node. The game begins at the root of the tree by asking a question. 
* Based on the user's response, it travels to the left sub-tree (yes) or right sub-tree (no), which repeats until it reaches an answer. Upon reaching an answer, the computer will ask if it is the object you are thinking of. 
* If the object is guessed correctly, no action in necessary and the computer wins. If the guessed object is wrong, it asks for the object you were thinking of, a new question to discriminate the correct answer from the incorrect answer, and then makes a new node from the question and two given answers, and inserts it into the tree in place of the correct answer. 
* The modified tree is then re-written accordingly to the same file the tree was initially read from.
# Screenshots
## Example Binary Decision Tree (Question/Answer Database)
![Alt text](/screenshots/sc1.png?raw=true "sc1")
## If Comp guesses correctly, nothing is changed
![Alt text](/screenshots/sc2.png?raw=true "sc2")
## If Comp guesses incorrectly, a new entry must be added/learned
![Alt text](/screenshots/sc3.png?raw=true "sc3")
![Alt text](/screenshots/sc4.png?raw=true "sc4")
## The next time the program runs, it will have learned a new answer!
![Alt text](/screenshots/sc5.png?raw=true "sc5")