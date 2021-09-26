---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* `t-` specifies an assignment list related command, whereas `-p` specifies a person related command.

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `p-add`

Adds a person to the address book.

Format: `p-add n/NAME e/EMAIL m/MODULES [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `p-add n/John Doe e/e1234567@u.nus.edu m/CS2100, CS1101S`
* `p-add n/Betsy Crowe e/e0234567@u.nus.edu m/CS2103T`

### Listing all persons : `p-list`

Shows a list of all persons in the address book.

Format: `p-list`

### Editing a person : `p-edit`

####Implementing [coming soon]

### Locating persons by name: `p-find`

Finds persons whose names contain any of the given keywords.

Format: `p-find n/NAME_KEYWORD... m/MODULE_KEYWORD...`

* The search is case-insensitive.<br> e.g `cs1101s` will match `CS1101S`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Can search by category.<br> e.g. `n/NAME, m/MODULE`
* Partial words will be matched e.g. `Ha` will match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `p-find n/John` returns `john` and `John Doe`
* `p-find n/alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `p-delete`

Deletes the specified person from the address book.

Format: `p-delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `p-list` followed by `p-delete 2` deletes the 2nd person in the address book.
* `p-find n/Betsy` followed by `p-delete 1` deletes the 1st person in the results of the `p-find` command.

### Adding an assignment with deadline: `t-deadline`

Adds an assignment with deadline to a person in the address book.

Format: `t-deadline n/NAME d/DESCRIPTION by/DD/MM/YYYY [HHMM]`

Examples:
* `t-deadline n/John Doe d/Lab1 by/ 21/08/2021`
* `t-deadline n/Betsy Crowe d/Assignment2 by/ 22/09/2021 1200`

### Deleting an assignment with deadline : `t-delete`

Deletes the specified assignment with deadline from a person in the address book.

Format: `t-delete n/NAME INDEX`

* Deletes the deadline of person `NAME` at the specified `INDEX`.
* The index refers to the index number shown in the displayed assignment list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `t-delete n/Wei Chang 10` deletes the 10th assignment in Wei Chang’s assignment list.

### Marking an assignment with deadline as done: `t-done`

Marks a specified assignment's deadline of a person as done in the address book.

Format: `t-done n/NAME INDEX`

Examples:
* `t-done n/John Doe 4` marks the 4th assignment in John Doe’s assignment list as done.

### Showing person’s assignment list: `t-show`

Shows the assignment list of the specified person in the address book.

Format: `t-show INDEX`

Examples:
* `t-show 2` renders the 2nd person’s assignment list on the right side of the app.
* The index refers to the index shown in the displayed person list.
  ![result for 'show assignment list'](images/showAssignmentListResult.png)

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**t-deadline** | `t-deadline n/NAME d/DESCRIPTION by/DD/MM/YYYY [HHMM]` <br> e.g., `t-deadline n/John Doe d/Lab1 by/ 21/08/2021`
**t-delete** | `t-delete n/NAME INDEX` <br> e.g., `t-delete n/Wei Chang 10`
**t-done** | `t-done n/NAME INDEX` <br> e.g., `t-done n/John Doe 4`
**t-list** | `t-show INDEX` <br> e.g., `t-show 2`