---
layout: page
title: Yeo Yiheng's Project Portfolio Page
---

### Project: TA<sup>2</sup>

TA<sup>2</sup> is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to find information based on various tags.
    * What it does: This feature allows the user to find students based on names, modules and / or tags.
    * Justification: This feature allows users to filter the list of students should they need to find someone specific.
    * Highlights: This command was challenging to implement due to the fact that many scenarios had to be considered to protect the application from malicious use or unintentional erroneous input. An example is finding with an empty prefix.
    * Credits: The command is an existing overhaul on the original `FindCommand` in the [AddressBook-Level3 (AB3)](https://github.com/nus-cs2103-AY2122S1/tp) project. The implementation has been largely revamped, though the concept of finding remains the same.

* **New Feature**: Added a user-friendly command input termed **Friendly Commands** to the giving of assignments.
  * What it does: This feature allows the user to set deadlines for assignments in a very simplified manner
  * Justification: This feature further improves efficiency in information management by reducing the amount of text a user has to input to set a deadline.
  * Pull Requests: [\#102](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/102)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=yeo-yiheng)

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Changed the attributes of a defined Person (Pull requests [\#62](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/62))
    * Improved command tolerance to incorrect user inputs such as random alphabet casing
(Pull requests [\#138](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/138), [\#214](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/214)
, [\#214](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/214), [\#220](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/220), [\#228](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/228))
    * Standardized representation of information in the GUI (Pull requests [\#98](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/98))

* **Documentation**:
    * User Guide:
        * Added documentation for the features and `find` [\#89](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/89), [\#131](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/131)
        * Did cosmetic tweaks to existing documentation of features `give`, `find`: [\#237](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/237)
    * Developer Guide:
        * Added documentation for the features of `find` [\#89](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/89)
        * Updated documentation for the implementation of `give`, formerly named `a-add` [\#115](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/115)
        * Updated documentation for the **Logic** component.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#130](https://github.com/AY2122S1-CS2103T-T13-2/tp/pull/130#discussion_r735115596)
