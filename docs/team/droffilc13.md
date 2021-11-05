---
layout: page
title: Clifford's Project Portfolio Page
---

### Project: TA<sup>2</sup>

TA2 is a desktop application that offers Teaching Assistants (TA) from the School of Computing (SoC) at the National University of Singapore (NUS) a convenient and efficient way to manage student information and keep track of students' assignment submissions.

Given below are my contributions to the project.

* **New Feature**: Redesign the whole interface to accommodate assignments in TA<sup>2</sup>. 
  * What it does: Added the assignment list panel to view students' assignments. Design the assignment card. Wrote the backend code for the `Assignmenmt` class and most of its components.
  * Justification: This feature is the main feature that differentiates TA<sup>2</sup> from AB3 which this project is based on. It forms the foundation for future commands that manipulate assignments.
  * Highlights: This enhancement forms the bedrock of what TA<sup>2</sup> is. Any future commands added will need assignments and assignment list panel to perform their operations.  This implementation is challenging because a lot of research and trial and error to fit the panel and make sure it scales with the window. Also, analysis of how backend code is reflected on frontend is necessary to understand how they are linked.

* **New Feature**: Update assignment list display upon successful execution of commands.
  * What it does: Bridge the outcome that happens at back end to front end. For example, deleting a student whose assignment is displayed should remove all his/her assignments in the assignment list panel as well.
  * Justification: This feature enhances the **user experience** because the user should not be using the `show` command to "refresh" the assignment list panel manually. Instead, this should be built in to make the design more intuitive.
  * Highlights: Any future commands related to assignment manipulation added will need this to perform their operations so that the front end reflects automatically the result as well. This implementation is challenging because it requires me to step into the shoes of the user and understand which operations should be automated to provide a more intuitive user experience.

* **New Feature**: Improve `give`, `remove` and `mark` assignments input format to make it less cumbersome
  * What it does: Change from `NAME` to `INDEX` to reference the student to `give` assignments to. For `remove` and `mark`, change from `NAME` to reference 
  * Justification: This feature improves the product significantly because it is faster, less cumbersome and less error-prone  to type the `INDEX` of student/assignments compared to the `NAME` of the student.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*
  * 
* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=droffilc13&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Droffilc13&tabRepo=AY2122S1-CS2103T-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases [`v1.2`](https://github.com/AY2122S1-CS2103T-T13-2/tp/releases/tag/untagged-201b35d412632ea069df), [`v1.3.trial`](https://github.com/AY2122S1-CS2103T-T13-2/tp/releases/tag/v1.3.trial), [`v1.3(final)`](https://github.com/AY2122S1-CS2103T-T13-2/tp/releases/tag/v1.3(final)), [`v1.4(final)`](https://github.com/AY2122S1-CS2103T-T13-2/tp/releases/tag/v1.4) (4 releases) on GitHub. 
  * Maintaining the issue tracker and ensure internal deadlines are met.
  * Renaming product name of application in `.fxml` file and `.jar` file in gradle.

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
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  