# Aaron's Personal Project 

## Daily Nutrition Tracker

This application tracks the **nutritional intake and goals** of the user. The 
user will input their meals along with its calories and macronutrients. At 
the end of the day, a report is produced measuring the users adherence to their 
daily eating goals (e.g. caloric intake, sugar, protein). These goals are set by 
the users and may be changed at any time. 

This application is meant for anyone who wants to **track their eating habits**. 
My hope is that myself and others may better their physical health using this 
tracker. As someone who enjoys weight training, eating is very important. The
amount of protein and calories you consume each day is strongly correlated
with your performance and recovery. 

## User Stories
- As a user, I'd like to add meals to a list of consumed items
- As a user, I'd like to see how many calories I have remaining in a day to
  reach my goal
- As a user, I'd like to see how many of my daily goals have been reached
- As a user, I'd like to see past logs of my daily eating
- As a user, I'd to be able to save my list of eating logs to file
- As a user, I'd to be able to view and add to my eating logs from file

## Instructions for Grader
- After running main, the main menu should appear, along with buttons giving 
  you options.
- To create a log, click the first button reading "New Log". From there, you can fill in
  the text fields with information about a meal. Click "add" once each field is filled and the meal 
  will be added to that logs list of meals. To end the log click "finish".
- After creating your first log, you will return to the main menu. You may create a second log by repeating 
  the previous steps.
- The visual component of the GUI may be found at the main menu. The photo of pasta under the buttons provides
  visual flair which alludes to the app's purpose. 
- You may save and load data by pressing the respective buttons on the main menu. 

## Phase 4: Task 2
- Spaghetti added to log of day 1 
- Steak added to log of day 1
- Log of day 1 added to log list
- Smoothie added to log of day 2
- Log of day 2 added to log list

## Phase 4: Task 3
Viewing this program's UML diagram, it is apparent that the classes could benefit from reorganization. In the model package 
there are two main classes, LogList and Goals. Many other classes, such as those used for data persistence and the GUI, 
call these two classes separately. Upon reflection this is unnecessary when a composite or upper level class may contain 
them both. A similar redundancy may be observed among the GUI classes. This time there is only one main class, MenuFrame, 
which contains elements of the model package. However, the panel classes which contain a field of MenuFrame additionally 
contain fields already contained MenuFrame (e.g. Goals in GoalPanel and ViewLogPanel). These classes could be streamlined 
by using a getter from the MenuFrame class to access these objects. 
