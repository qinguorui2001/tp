---
layout: page
title: QIN GUORUI's Project Portfolio Page
---

### Project: TA<sup>2</sup>

TA<sup>2</sup> is a desktop application designed for teaching assistants from the School of Computing (SoC) at the National University of Singapore (NUS) to manage student information and keep track of students’ assignment submissions.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
    * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
    * Credits: *{https://stackoverflow.com/questions/11530276/how-do-i-implement-a-simple-undo-redo-for-actions-in-java}*

* **New Feature**: Added the ability to add assignments into the student's assignment list.
    * What it does: allows the user to add a specific assignment to a student. Newly added assignment will be immediately shown in the assignment list.
    * Justification: This feature ensures the basic function of the product because tutors are responsible for allocating assignments to their students.
    * Highlights: This enhancement affects commands to be added in the future. The implementation was straightforward as it refers to the command pattern from existing commands.
    * Credits: *{no}*

* **New Feature**: Added the ability to delete assignments from the student's assignment list.
    * What it does: allows the user to delete a specific assignment from a student's assignments. Newly deleted assignment will be immediately removed from the assignment list.
    * Justification: This feature ensures the basic function of the product because tutors are responsible for managing assignments for their students.
    * Highlights: This enhancement affects commands to be added in the future. The implementation was straightforward as it refers to the command pattern from existing commands.
    * Credits: *{no}*

* **New Feature**: Added the ability to mark assignments as completed in the student's assignment list.
  * What it does: allows the user to mark a specific assignment of a student as completed. Newly marked assignment will be immediately shown as `Completed` in the assignment list.
  * Justification: This feature ensures the basic function of the product because tutors are responsible for marking assignments of their students.
  * Highlights: This enhancement affects commands to be added in the future. The implementation was straightforward as it refers to the command pattern from existing commands.
  * Credits: *{no}*


* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete` and `find` [\#72]()
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Added implementation details of the `delete` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
