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

1. Download the latest `ta^2.jar` from [here](https://github.com/AY2122S1-CS2103T-T13-2/tp/releases/tag/v1.3.trial).

1. Copy the file to the folder you want to use as the _home folder_ for your TA<sup>2</sup>.

1. Double-click the file to start the app. The GUI similar to the one shown below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/userguide/ta^2_ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe m/CS2100 e/e1234567@u.nus.edu` : Adds a contact named `John Doe` with his/her relevant information to TA<sup>2</sup>.

   * **`delete`**`3` : Deletes the 3rd contact shown in the currently displayed contact list.

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

* If a command requires `INDEX` as an input, only one input for `INDEX` is expected.<br>

* If a parameter is expected only once in the command, but you specify it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `m/cs2103 m/cs2101`, only `m/cs2101` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help: `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a student: `add`

Adds a student to the contact list with the student's relevant information.

Format: `add n/NAME e/EMAIL m/MODULES [t/TAG]…​`

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

* A student can have any number of tags (including 0).<br>

* A student's module must follow XX[X]1111[X], where X is any letter, 1 is any number and values in square brackets are optional.<br>

* Only student's initials and module code will be capitalised on the displayed student list.<br>
  * e.g. `n/alex yeoh`, `n/DAVID LI` will be converted to `Alex Yeoh` and `David Li` respectively.<br>
  * e.g. `cs2100`, `GER1000t` will be converted to `CS2100` and `GER1000T` respectively.<br>

</div>

Examples:
* `add n/John Doe m/CS1010 e/e1234567@u.nus.edu`
* `add n/Betsy Crowe e/e0234567@u.nus.edu m/CS2103T`
* `add n/alex yeoh e/e1234123@u.nus.edu m/GEQ1000 t/T17`

### Listing all students: `list`

Shows a list of all students in the contact list.

Format: `list`

### Editing a student: `edit`

Modifies any part of the student's information.

Format: `edit INDEX [m/MODULE] [e/EMAIL] [t/TAG] [n/NAME]...`

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

* The index refers to the index number shown in the displayed student list.

* The index **must be a positive integer within the number of students in list** 1, 2, (till the index of the last student)​

* If there are two identical prefixes in one edit command, only the last one works. 

* **At least one modification** is required for each edit.

</div>

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**
* You can modify several parts of student information at the same time.
</div>

Examples:
* `edit 2 e/e00111@u.nus.edu` replaces the 2nd student's email with `e00111@u.nus.edu` in the contact list.
* `edit 1 n/Brob` changes 1st student's name to `Brob` in the results of the `find` command.
* `edit 1 n/Brob n/New m/cs1101 m/cs1231s` changes 1st student's name to `New` and module to `cs1231s` in the results of the `find` command.

### Deleting a student: `delete`

Deletes the specified student from the contact list.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.

Examples:
* `list` followed by `delete 2` deletes the 2nd student in the contact list.
* `find n/Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Showing a student’s assignment list: `show`

Shows the assignment list of the specified student in a separate assignment list window.
The assignment list will be sorted by status and date.

Format: `show INDEX`

* Shows the assignment list of the student at the specified `INDEX`.

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

* Assignments with `COMPLETED` status will be at the bottom of the list.<br>

* Assignments with `PENDING` status will be at the top of the list.<br>

* Assignments with the same status will be sorted by due date.<br>

</div>

Examples:
* `show 1` renders the first student’s assignment list on the assignment list panel.
* The index refers to the index shown in the displayed student list.
  ![result for 'show assignment list'](images/userguide/showAssignmentListResult.png)

### Adding assignments: `give` `giveall`

#### Adding an assignment: `give`

Gives an assignment with a deadline to the student specified by the index in the contact list.
If `time` is not specified, `time` will be set to `11:59 pm` by default.

Format: `give INDEX d/DESCRIPTION by/ D/M/YYYY [,HHMM]`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**
* The date can be replaced by friendly commands.
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
</div>

Examples:
* `give 1 d/Lab1 by/ 21/8/2021` gives the first student displayed in your contact list an assignment of description `Lab1` with a deadline 2021, Aug 21.
* `give 2 d/Assignment2 by/ 22/9/2021,1200` gives the second student displayed in your contact list an assignment of description `Assignment2` with a deadline 2021, Sep 22, 1200hrs.
* `give 1 d/Tutorial3 by/ mon` gives the first student displayed in your contact list an assignment of description `Tutorial3` with a deadline next monday 2359hrs.
* `give 2 d/Report1 by/ tue, 1800` gives the second student in your contact list an assignment of description `Report1` with a deadline next tuesday 1800hrs.

#### Adding an assignment to all students in a module: `giveall`

Adds an assignment with a deadline to all students in the specified module .

Format: `giveall m/MODULE d/DESCRIPTION by/ D/M/YYYY [,HHMM]`

Examples:
* `giveall m/CS2100 d/Assignment 2 by/ 15/10/2021,1300` gives all students of module CS2100 an assignment of description `Assignment 2` with a deadline 2021, Oct 15, 1300hrs.
* `giveall m/CS2103T d/iP by/ 02/09/2021,2359` gives all students of module CS2103T an assignment of description `iP` with a deadline 2021, Sep 2, 2359hrs.

### Finding people who match with input keywords: `find`

Displays the list of people who match any of the input keywords. The matching is based
on an ***OR*** basis, where if a student matches at **least one keyword**, that student will
be considered as matched and thus displayed.

Format: `find [n/NAME] [m/MODULE] [t/TAG]...`

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

1. There should be at least one prefix.

2. Ordering of prefixes are not strict and presence of multiple keywords are acceptable.

3. Keywords are **case-insensitive** <br> e.g. `cs1101s` will match `CS1101S`.

4. Each part of name separated by space will match the full name. <br> e.g. `n/Hans` or `n/Bo` will both match `Hans Bo`.

5. Students' names matching at least one keyword will be returned (i.e. `OR` search). <br>
   e.g. `n/Hans Bo` will return `Hans Gruber`, `Bo Yang`.

</div>

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**
* We can search for multiple fields, e.g., modules at once, separated by a whitespace. 
   e.g. `m/CS1101S CS2103T` will return people who take either modules.
</div>

Examples:
* `find n/Bernice` returns the students with name of `Bernice`. 
* `find m/CS1101S` returns the students with module `CS1101S`.
* `find t/Lab15` returns the students with tutorial `Lab15`.
* `find n/Bernice m/MA1521` returns the students with name of `Bernice` or study module `MA1521`.
* `find m/CM1417 t/Group04` returns the students with module `CM1417` or in tutorial `Group04`.
* `find n/Evian m/CS2103T t/Group10` returns the students who at least satisfy one of the requirements: with name of `Evian`, study module`CS2103T` and in tutorial `Group10`.
* `find t/E34 n/Brian m/GEQ1000` returns the students who at least satisfy one of the requirements: with name of `Brian`, study module`GEQ1000` and in tutorial `E34`.
* `find n/alex david m/cs1231 cs2103t` returns the students who at least satisfy one of the requirements: `david` or `alex` or both can be part of their names seperated by space, study module`cs1231` or `cs2103t`.


![result for 'find Example'](images/userguide/findExample.png)

### Deleting an assignment with a deadline: `remove`

Removes the specified assignment from the displayed assignment list.

Format: `remove INDEX`

* Removes the assignment at `INDEX` in the currently displayed assignment list of a student.

Examples:
* `remove 10` deletes the 10th assignment in the displayed assignment list

### Marking an assignment: `done`

Marks a specified assignment's deadline of a student as completed.

Format: `done INDEX`

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

* Assignments with pending status will have an orange tag.

* Assignments with completed status will have a green tag.

* Only works if currently displayed assignment list is not empty.

</div>

<div markdown="span" class="alert alert-warning">:exclamation: 
**Caution:**
You can not mark the already completed assignment as done any more.
</div>

Examples:
* `done 3` marks the 3rd assignment in the displayed assignment list as completed.

### Delete completed assignments from all students: `clean`

Deletes all completed assignments from TA<sup>2</sup>.

Format: `clean`

### Clearing all entries: `clear`

Clears all entries from TA<sup>2</sup>.

Format: `clear`

### Undoing a command: `undo`

Undoes the last command entered.

Format: `undo`

<div markdown="span" class="alert alert-warning">:exclamation: 
**Caution:**
* Undo all kinds of commands except for `undo` and `redo`.
* At the start of the program, you can not undo anything.
</div>

### Redoing a command: `redo`

Recovers the effect of the last `undo` command.

Format: `redo`

<div markdown="span" class="alert alert-warning">:exclamation: 
**Caution:**
* Redo all kinds of commands except for `undo` and `redo`.
* Once you enter a new command except for `undo` and `redo`, you can not redo anymore. 
</div>

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

TA<sup>2</sup> data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TA<sup>2</sup> data are saved as a JSON file `[JAR file location]/data/ta2.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: 
**Caution:**
If your changes to the data file make its format invalid, TA<sup>2</sup> will discard all data and start with an empty data file at the next run.
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

