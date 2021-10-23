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

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

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

* If a parameter is expected only once in the command but you specify it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `m/cs2103 m/cs2101`, only `m/cs2101` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the contact list with the person's relevant information.

Format: `add n/NAME e/EMAIL m/MODULES [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

* A person can have any number of tags (including 0).<br><br>
* A person's module must follow XX[X]1111[X], where X is any letter,
1 is any number and values in square brackets are optional.
* Only person's initials and module code will be capitalised on the displayed person list.<br>
  * e.g. `n/alex yeoh`, `n/DAVID LI` will be converted to `Alex Yeoh` and `David Li` respectively.
  * e.g. `cs2100`, `GER1000t` will be converted to `CS2100` and `GER1000T` respectively.
</div>

Examples:
* `add n/John Doe e/e1234567@u.nus.edu m/CS2100, CS1101S`
* `add n/Betsy Crowe e/e0234567@u.nus.edu m/CS2103T`
* `add n/alex yeoh e/1234123@u.nus.edu m/GEQ1000, T17`

### Listing all persons : `list`

Shows a list of all persons in the contact list.

Format: `list`

### Editing a person : `edit`

####Implementing [coming soon]

### Locating persons by name or module: `find`

Finds persons whose names or modules contain any of the given keywords.

Format: `find n/NAME_KEYWORD... m/MODULE_KEYWORD...`

* The search is case-insensitive.<br> e.g. `cs1101s` will match `CS1101S`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Can only search by name and module.<br> e.g. `n/NAME, m/MODULE`.
* Partial names will be matched. <br> e.g. `n/Hans` will match `Hans Bo`.
* Persons matching at least one keyword will be returned (i.e. `OR` search). <br>
  e.g. `n/Hans Bo` will return `Hans Gruber`, `Bo Yang`.
* We can search for multiple people at once, separated by a whitespace. <br> 
  e.g. `n/Bernice James` will return `Bernice`, `James`.
* We can search for multiple modules at once, separated by a whitespace. <br>
  e.g. `m/CS1101S CS2103T` will return people who take either / or both modules.
* Combining categories during search will return people who take at least one of the modules. <br>
  e.g. `n/James m/MA1521` will return `James`, `Bernice` (assuming Bernice takes MA1521).

Examples:
* `find n/John` returns `john` and `John Doe`.
* `find n/alex david` returns `Alex Yeoh`, `David Li`<br>.
* `find n/alex david m/cs1231 cs2103t` returns `alex`, `charlotte`, `david`, `james`, assuming charlotte and james takes either module listed.
  ![result for 'find Example'](images/userguide/findExample.png)

### Deleting a person : `delete`

Deletes the specified person from the contact list.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the contact list.
* `find n/Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Showing person’s assignment list: `show`

Shows the assignment list of the specified person in a separate assignment list window.
Assignment list will be sorted by status and date.
* Assignments with `COMPLETED` status will be at the bottom of the list.
* Assignments with `PENDING` status will be at the top of the list.
* Assignments with same status will be sorted by due date.

Format: `show INDEX`

Examples:
* `show 1` renders the first person’s assignment list on the right side of the app.
* The index refers to the index shown in the displayed person list.
  ![result for 'show assignment list'](images/userguide/showAssignmentListResult.png)

### Adding assignments : `give` `giveall`

#### Adding an assignment : `give`

Gives an assignment with deadline to the person specified by the index in the contact list.
If `time` is not specified, `time` will be set to `11:59 pm` by default.

Format: `INDEX d/DESCRIPTION by/ D/M/YYYY,[HHMM]`

#### Adding an assignment with specified module: `giveall`

Adds an assignment with deadline to all persons in the specified module .

Format: `giveall m/MODULE d/DESCRIPTION by/ D/M/YYYY,[HHMM]`
  
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
* `give 1 d/Lab1 by/ 21/8/2021`
* `give 2 d/Assignment2 by/ 22/9/2021,1200`
* `give 1 d/Tutorial3 by/ mon`
* `give 2 d/Report1 by/ tue, 1800`
* `giveall m/CS2100 d/Assignment 2 by/ 15/10/2021,1300`
* `giveall m/CS2103T d/iP by/ 02/09/2021,2359`

### Deleting an assignment with deadline : `remove`

Removes the specified assignment from the displayed assignment list.

Format: `remove INDEX`

* Removes the specified assignment of a person at the specified `INDEX`.
* The index refers to the index number shown in the displayed assignment list.
* The index **must be a positive integer** 1, 2, 3, …​


Examples:
* `remove 10` deletes the 10th assignment in the displayed assignment list

### Marking an assignment : `done`

Marks a specified assignment's deadline of a person as completed.
* Assignments with uncompleted/pending status will have an orange tag.
* Assignments with completed status will have a green tag.

Format: `done INDEX`

Examples:
* `done 3` marks the 3rd assignment in the displayed assignment list as completed.


### Clearing all entries : `clear`

Clears all entries from TA<sup>2</sup>.

Format: `clear`

### Undoing the last command : `undo`

Undo the last command entered.

Format: `undo` 

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
**give** | `give INDEX d/DESCRIPTION by/ D/M/YYYY [HHMM]` <br> e.g., `give 1 d/Lab1 by/ 21/8/2021`
**giveall** | `giveall m/module d/DESCRIPTION by/ D/M/YYYY [HHMM]` <br> e.g., `give m/CS2100 d/Lab1 by/ 21/8/2021`
**remove** | `remove INDEX` <br> e.g., `remove 10`
**done** | `done INDEX` <br> e.g., `done 4`
**undo** | `undo`
**exit** | `exit`

