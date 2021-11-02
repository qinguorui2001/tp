---
layout: page
title: User Guide
---

Teaching Assistant's Assistant (TA<sup>2</sup>) is a **desktop app designed for teaching assistants/tutors/professors 
from the School of Computing to help manage student contacts and keep track of students' assignment submissions. TA<sup>2</sup> is  
optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, TA<sup>2</sup> can get your contact and assignment management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ta2.jar` from [here](https://github.com/AY2122S1-CS2103T-T13-2/tp/releases/tag/v1.4).

1. Copy the file to the folder you want to use as the _home folder_ for your TA<sup>2</sup>.

1. Double-click the file to start the app. The GUI similar to the one shown below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/userguide/ta^2_ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe m/CS2100 e/e1234567@u.nus.edu` : Adds a contact named `John Doe` with his/her relevant information to TA<sup>2</sup>.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current displayed contact list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friends` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friends`, `t/Leader t/friends` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME e/EMAIL`, `e/EMAIL n/NAME` is also acceptable.

* If a command requires `INDEX` as an input, only one input for `INDEX` is expected.

* If a parameter is expected only once in the command but you specify it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `m/cs2103 m/cs2101`, only `m/cs2101` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a student: `add`

Adds a student to the contact list with the student's relevant information.

Format: `add n/NAME e/EMAIL m/MODULES [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

* A student can have any number of tags (including 0).<br><br>
* A student's module must follow XX[X]1111[X], where X is any letter,
1 is any number and values in square brackets are optional.
* Only student's initials and module code will be capitalised on the displayed student list.<br>
  * e.g. `n/alex yeoh`, `n/DAVID LI` will be converted to `Alex Yeoh` and `David Li` respectively.
  * e.g. `cs2100`, `GER1000t` will be converted to `CS2100` and `GER1000T` respectively.
</div>

Examples:
* `add n/John Doe m/CS1010 e/e1234567@u.nus.edu`
* `add n/Betsy Crowe e/e0234567@u.nus.edu m/CS2103T`
* `add n/alex yeoh e/1234123@u.nus.edu m/GEQ1000 t/T17`

### Listing all students : `list`

Shows a list of all students in the contact list.

Format: `list`

### Editing a student : `edit`

Modifies any part of student's information.

Format: `edit INDEX [m/MODULE] [e/EMAIL] [t/TAG] [n/NAME]`

:bulb: **Tip:**
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer within the number of students in list** 1, 2, (till the index of last student)​ 
* You can modify several parts of student information at the same time.
* If there are two identical prefixes in one edit command, only the last one works. 
* **At least one modification** is required for each edit.

Examples:
* `edit 2 e/e00111@u.nus.edu` replaces the 2nd student's email with `e00111@u.nus.edu` in contact list.
* `edit 1 n/Brob` changes 1st student's name to `Brob` in the results of the `find` command.
* `edit 1 n/Brob n/New m/cs1101 m/cs1231s` changes 1st student's name to `New` and module to `cs1231s` in the results of the `find` command.

### Deleting a student : `delete`

Deletes the specified student from the contact list.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.

Examples:
* `list` followed by `delete 2` deletes the 2nd student in the contact list.
* `find n/Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Showing student’s assignment list: `show`

Shows the assignment list of the specified student in a separate assignment list window.
Assignment list will be sorted by status and date.

* Assignments with `COMPLETED` status will be at the bottom of the list.
* Assignments with `PENDING` status will be at the top of the list.
* Assignments with same status will be sorted by due date.

Format: `show INDEX`

* Shows the assignment list of student at the specified `INDEX`.

Examples:
* `show 1` renders the first student’s assignment list on the right side of the app.
* The index refers to the index shown in the displayed student list.
  ![result for 'show assignment list'](images/userguide/showAssignmentListResult.png)

### Adding assignments : `give` `giveall`

#### Adding an assignment : `give`

Gives an assignment with deadline to the student specified by the index in the contact list.
If `time` is not specified, `time` will be set to `11:59 pm` by default.

Format: `give INDEX d/DESCRIPTION by/ D/M/YYYY [,HHMM]`

* Date can be replaced by friendly commands.
    * `today` - sets due date to tonight.
    * `tmr` - sets due date to tomorrow.
    * `week` - sets due date to a week(7 days) from now.
    * `mon` - sets due date to the coming monday.
    * `tue` - sets due date to the coming tuesday.
    * `wed` - sets due date to the coming wednesday.
    * `thu` - sets due date to the coming thursday.
    * `fri` - sets due date to the coming friday.
    * `sat` - sets due date to the coming saturday.
    * `sun` - sets due date to the coming sunday.
  
Examples:
* `give 1 d/Lab1 by/ 21/8/2021` gives the first student in current displayed contact list `Lab1` with deadline 2021, Aug 21.
* `give 2 d/Assignment2 by/ 22/9/2021,1200` gives the second student in current displayed contact list `Assignment2` with deadline 2021, Sep 22, 1200hrs.
* `give 1 d/Tutorial3 by/ mon` gives the first student in current displayed contact list `Tutorial3` with deadline next monday 2359hrs.
* `give 2 d/Report1 by/ tue, 1800` gives the second student in current displayed contact list `Report1` with deadline next tuesday 1800hrs.

#### Adding an assignment with specified module: `giveall`

Adds an assignment with deadline to all students in the specified module .

Format: `giveall m/MODULE d/DESCRIPTION by/ D/M/YYYY [,HHMM]`

**Note:**
1. It is possible to add an assignment using `giveall` even if some students have the assignment already. Students who have the 
assignment will not receive a duplicate assignment.
2. When using `giveall` in the situation stated in point 1, the specified assignment in the input needs to have the same 
due date as the existing assignment. **The command will not be valid if the due date is different.** 
Point 3 of the examples section illustrates this.

Examples:
* `giveall m/CS2100 d/Assignment 2 by/ 15/10/2021,1300` gives all students of module CS2100 `Assignment 2` with deadline 2021, Oct 15, 1300hrs.
* `giveall m/CS2103T d/iP by/ 02/09/2021,2359` gives all students of module CS2103T `iP` with deadline 2021, Sep 2, 2359hrs.
* `giveall m/CS2100 d/Assignment 2 by/ 15/10/2021,1300` is an invalid input if some students in `CS2100` has the assignment
`Assignment 2` with deadline `11:59 pm`.

### Finding people who match with input keywords: `find`

Displays the list of people who match any of the input keywords. The matching is based
on an ***OR*** basis, where if a student matches at **least one keyword**, that student will
be considered as matched and thus displayed.

Format: `find [n/NAME] [m/MODULE] [t/TUTORIAL_NUMBER]...`

**Note:**
1. At least one prefix is required.
2. Ordering of prefixes are not strict, and allows for multiple keywords
3. Keywords are **case-insensitive** <br> e.g. `cs1101s` will match `CS1101S`.
4. Partial names will be matched. <br> e.g. `n/Hans` will match `Hans Bo`.
5. If there are two identical prefixes in the command, only the last one works.
6. Students' names matching at least one keyword will be returned (i.e. `OR` search). <br>
   e.g. `n/Hans Bo` will return `Hans Gruber`, `Bo Yang`.
7. We can search for multiple fields, e.g., modules at once, separated by a whitespace. <br>
   e.g. `m/CS1101S CS2103T` will return people who take either / or both modules.

Examples:
* `find n/Bernice` returns the students with name `Bernice`. 
* `find m/CS1101S` returns the students with module `CS1101S`.
* `find t/Lab15` returns the students with tutorial `Lab15`.
* `find n/Bernice m/MA1521` returns the students with name `Bernice` or study module `MA1521`.
* `find m/CM1417 t/Group04` returns the students with module `CM1417` or in tutorial `Group04`.
* `find n/Evian m/CS2103T t/Group10` returns the students who at least satisfy one of requirements: with name `Evian`, study module`CS2103T` and in tutorial `Group10`.
* `find t/E34 n/Brian m/GEQ1000` returns the students who at least satisfy one of requirements: with name `Brian`, study module`GEQ1000` and in tutorial `E34`.
* `find n/alex david m/cs1231 cs2103t` returns the students who at least satisfy one of requirements: with name contains `david` or `alex`, study module`cs1231` or `cs2103t`.


![result for 'find Example'](images/userguide/findExample.png)

### Deleting an assignment with deadline : `remove`

Removes the specified assignment from the displayed assignment list.

Format: `remove INDEX`

* Removes the assignment at `INDEX` in current displayed assignment list of a student.

Examples:
* `remove 10` deletes the 10th assignment in the displayed assignment list

### Marking an assignment : `done`

Marks a specified assignment's deadline of a student as completed.
* Assignments with uncompleted/pending status will have an orange tag.
* Assignments with completed status will have a green tag.
* Only works if current displayed assignment list is non-empty.

Format: `done INDEX`

Examples:
* `done 3` marks the 3rd assignment in the displayed assignment list as completed.

### Delete completed assignments from all students: `clean`

Deletes all completed assignments from TA<sup>2</sup>.

Format: `clean`

### Clearing all entries : `clear`

Clears all entries from TA<sup>2</sup>.

Format: `clear`

### Undoing the last command : `undo`

Undoes the last command entered.
* Undo all kinds of command except for `undo` and `redo`.
* At the start of program, nothing can be undone.

Format: `undo` 

### Redoing the command : `redo`

Recovers the effect of last `undo` command.
* Redo all kinds of command except for `undo` and `redo`.
* Once a new command except for `undo` and `redo` is entered, nothing can be redone. 

Format: `redo`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TA<sup>2</sup> data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TA<sup>2</sup> data are saved as a JSON file `[JAR file location]/data/ta2.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TA<sup>2</sup> will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TA<sup>2</sup> home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**add** | `add n/NAME e/EMAIL m/MODULES [t/TAG]…​` <br> e.g., `add n/James Ho m/CS2100 e/jamesho@example.com t/friend t/colleague`
**clear** | `clear`
**delete** | `delete INDEX`<br> e.g., `delete 3`
**edit** | `edit INDEX [n/NAME] [m/MODULE] [e/EMAIL] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**list** | `list`
**help** | `help`
**show** | `show INDEX` <br> e.g., `show 2`
**give** | `give INDEX d/DESCRIPTION by/ D/M/YYYY [,HHMM]` <br> e.g., `give 1 d/Lab1 by/ 21/8/2021`
**giveall** | `giveall m/module d/DESCRIPTION by/ D/M/YYYY [,HHMM]` <br> e.g., `give m/CS2100 d/Lab1 by/ 21/8/2021`
**clean** | `clean`
**remove** | `remove INDEX` <br> e.g., `remove 10`
**done** | `done INDEX` <br> e.g., `done 4`
**undo** | `undo`
**redo** | `redo`
**exit** | `exit`

