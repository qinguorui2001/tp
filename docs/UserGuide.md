---
layout: page
title: User Guide
---

Teaching Assistant's Assistant (TA<sup>2</sup>) is a **desktop app for managing School of Computing contacts and assignments, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TA<sup>2</sup> can get your contact and assignment management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ta^2.jar` from [here](https://github.com/Droffilc13/ip/releases/tag/A-Release).

1. Copy the file to the folder you want to use as the _home folder_ for your TA<sup>2</sup>.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`p-list`** : Lists all contacts.

   * **`p-add`**`n/John Doe m/CS2100 e/e1234567@u.nus.edu` : Adds a contact named `John Doe` to the TA<sup>2</sup>.

   * **`p-delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`p-clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* `a-` specifies an assignment list related command, whereas `-p` specifies a person related command.

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `p-add n/NAME`, `NAME` is a parameter which can be used as `p-add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME e/EMAIL`, `e/EMAIL n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specify it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `p-list`, `exit` and `p-clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `p-add`

Adds a person to the contact list.

Format: `p-add n/NAME e/EMAIL m/MODULES [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)<br><br>
A person's module must follow XX[X]1111[X], where X is any letter,
1 is any number and values in square brackets are optional, and it
should not be blank.
</div>

Examples:
* `p-add n/John Doe e/e1234567@u.nus.edu m/CS2100, CS1101S`
* `p-add n/Betsy Crowe e/e0234567@u.nus.edu m/CS2103T`

### Listing all persons : `p-list`

Shows a list of all persons in the contact list.

Format: `p-list`

### Editing a person : `p-edit`

####Implementing [coming soon]

### Locating persons by name: `p-find`

Finds persons whose names contain any of the given keywords.

Format: `p-find n/NAME_KEYWORD... m/MODULE_KEYWORD...`

* The search is case-insensitive.<br> e.g `cs1101s` will match `CS1101S`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Can search by category.<br> e.g. `n/NAME, m/MODULE`
* Partial names will be matched <br> e.g. `n/Hans` will match `Hans Bo`
* Persons matching at least one keyword will be returned (i.e. `OR` search). <br>
  e.g. `n/Hans Bo` will return `Hans Gruber`, `Bo Yang`
* We can search for multiple people at once, separated by a whitespace. <br> 
  e.g. `n/Bernice James` will return `Bernice`, `James`
* We can search for multiple module at once, separated by a whitespace. <br>
  e.g. `m/CS1101S CS2103T` will return people who take either / or both modules.
* Combining categories during search will return everyone matching either names OR module. <br>
  e.g. `n/James m/MA1521` will return `James`, `Bernice` (assuming Bernice takes MA1521).

Examples:
* `p-find n/John` returns `john` and `John Doe`
* `p-find n/alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
* `p-find n/bernice james m/ma1521 cs2103t` returns `bernice`, `james`, `christie`, `michael`, assuming christie and michael takes either module listed.

### Deleting a person : `p-delete`

Deletes the specified person from the contact list.

Format: `p-delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `p-list` followed by `p-delete 2` deletes the 2nd person in the contact list.
* `p-find n/Betsy` followed by `p-delete 1` deletes the 1st person in the results of the `p-find` command.

### Adding an assignment with deadline: `a-add`

Adds an assignment with deadline to a person in the contact list.

Format: `a-add n/NAME d/DESCRIPTION by/ D/M/YYYY,[HHMM]`

* The name is case-insensitive.
e.g. n/alex yeoh will match Alex Yeoh
  

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
* `a-add n/John Doe d/Lab1 by/ 21/8/2021`
* `a-add n/Betsy Crowe d/Assignment2 by/ 22/9/2021,1200`
* `a-add n/Alex Yeoh d/Tutorial3 by/ mon`
* `a-add n/john smith d/Report1 by/ tue, 1800`

### Deleting an assignment with deadline : `a-delete`

Deletes the specified assignment with deadline from a person in the contact list.

Format: `a-delete n/NAME INDEX`

* The name is case-insensitive.
  e.g. n/alex yeoh will match Alex Yeoh.
* Deletes the deadline of person `NAME` at the specified `INDEX`.
* The index refers to the index number shown in the displayed assignment list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `a-delete n/Wei Chang 10` deletes the 10th assignment in Wei Chang’s assignment list.

### Marking an assignment with deadline as done: `a-done`

Marks a specified assignment's deadline of a person as done.

Format: `a-done n/NAME INDEX`

Examples:
* `a-done n/John Doe 4` marks the 4th assignment in John Doe’s assignment list as done.

### Showing person’s assignment list: `a-show`

Shows the assignment list of the specified person in a separate assignment list window.
Assignment list will be sorted by status and date.
* Assignments with `COMPLETED` status will be at the bottom of the list.
* Assignments with `PENDING` status will be at the top of the list.
* Assignments with same status will be sorted by due date.

Format: `a-show INDEX`

Examples:
* `a-show 2` renders the 2nd person’s assignment list on the right side of the app.
* The index refers to the index shown in the displayed person list.
  ![result for 'show assignment list'](images/showAssignmentListResult.png)

### Clearing all entries : `clear`

Clears all entries from TA<sup>2</sup>.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TA<sup>2</sup> data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TA<sup>2</sup> data are saved as a JSON file `[JAR file location]/data/ta^2.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TA<sup>2<> will discard all data and start with an empty data file at the next run.
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
**p-add** | `p-add n/NAME e/EMAIL m/MODULES [t/TAG]…​` <br> e.g., `add n/James Ho m/CS2100 e/jamesho@example.com t/friend t/colleague`
**Clear** | `clear`
**p-delete** | `p-delete INDEX`<br> e.g., `p-delete 3`
**p-edit** | `p-edit INDEX [n/NAME] [m/MODULE] [e/EMAIL] [t/TAG]…​`<br> e.g.,`p-edit 2 n/James Lee e/jameslee@example.com`
**p-find** | `p-find KEYWORD [MORE_KEYWORDS]`<br> e.g., `p-find James Jake`
**p-list** | `list`
**Help** | `help`
**a-add** | `a-add n/NAME d/DESCRIPTION by/ D/M/YYYY [HHMM]` <br> e.g., `a-add n/John Doe d/Lab1 by/ 21/8/2021`
**a-delete** | `a-delete n/NAME INDEX` <br> e.g., `a-delete n/Wei Chang 10`
**a-done** | `a-done n/NAME INDEX` <br> e.g., `a-done n/John Doe 4`
**a-list** | `a-show INDEX` <br> e.g., `a-show 2`
