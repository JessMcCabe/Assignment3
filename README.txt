
Name : Jessica McCabe

Student Number : 05000246

Which level unit tests succeed completely: Outstanding Level of unit tests complete successfully (82/82 tests pass)

Which level unit tests succeed partially: N/A (highest level tests all successful)

Self reflection - Grading Spectrum Level:
------------------------------------------------
Data Model- Person super class.
            Member and Trainer extend Person.
            StudentMember and Premium Member extend Member

API - Fully Implemented.

Menu - Login or Register working (Login or Register as Trainer or Member)
       Registered members or trainers are saved to the members.xml file.
       Login as Member or Trainer. The email address must match that of an existing member if member login is chosen,
       likewise, the email must match that of an existing trainer if trainer login is chosen.

       A logged in member can view their profile, update their profile, view their progress or logout
       Viewing progress can be done in two ways, view progress by weight or by waist

       A logged in trainer can add a new member, list all members, search for members by email , view assessment submenu or logout
       The assessment submenu will allow the trainer to add an assessment to a member or add a comment to an assessment
       When adding a comment to an assessment, the trainer can enter the date of the assessment if known, or list all the assessment dates if it is unknown

       The app can be closed from the outer most menu by typing close

Tests - Fully-featured. All unit tests pass.

Known bugs/problems :
When a member updates their details and then views their profile, they will see their updated details, however, there is an issue saving the updated member, caused by an exception in the save() method
When the user closes out of the app there is an extra call to a sub menu that should not appear

Any sources referred to during the development of the assignment (no need to reference lecture/lab materials):

Reading in file error:
https://stackoverflow.com/questions/30812293/com-thoughtworks-xstream-security-forbiddenclassexception
Plus or minus a certain value
https://stackoverflow.com/questions/10264313/java-if-statement-a-is-equal-to-b-plus-or-minus-2
Metrics
https://www.metric-conversions.org/length/feet-to-inches.htm
https://www.rapidtables.com/convert/weight/how-kg-to-pound.html
Rounding of floats
https://stackoverflow.com/questions/8753959/round-a-floating-point-number-to-the-next-integer-value-in-java
https://www.quora.com/How-can-I-round-a-number-to-1-decimal-digit-in-Java
Sorted Sets
http://www.java2s.com/Tutorial/Java/0140__Collections/CreatingaSortedSet.htm
https://www.quora.com/How-do-I-sort-an-ArrayList-of-dates-based-on-the-current-date-in-Java
https://kodejava.org/how-do-i-format-a-date-into-ddmmyyyy/
Instance Of
https://www.javatpoint.com/downcasting-with-instanceof-operator