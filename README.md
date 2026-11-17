# Project Name
Free Food Reporter

## Team Information
[//]: # (todo)

### Team Members
- Lucy Wang
- Anna Wang
- Cynthia Ji
- Yuchen Han

---

## Project Description

### Domain

Campus Life / Food Sharing

Free Food Tracker is a campus-focused application designed to help university students discover and share free food 
opportunities. Free food is often available at various campus activities, but information about these opportunities can 
be scattered across different platforms, making them difficult to find.

Our application provides a centralized platform where students can post free food alerts, browse available 
opportunities, and search or filter events based on their preferences. Students can also report inaccurate or 
unavailable listings, helping keep the information relevant and up to date.

By making free food opportunities easier to discover and share, Free Food Tracker aims to reduce food waste and help 
students make better use of resources available on campus. It also encourages students to join the events through the 
benefit of free food.

---

## User Stories

### User Story 1
As a UofT student, I want to browse available free food events and search or filter them based on my preferences, 
such as food type, location, and registration requirements, so that I can quickly find free food opportunities according
to my preferences.

### User Story 2
As a UofT student, I want to set my notification preferences in advance and receive an in-app notification with the event’s
information when a newly published free food event matches my saved preferences, so that I do not miss relevant free food opportunities.

### User Story 3
As a UofT student, I want to post some free food events on campus, so that other students can discover and participate in these 
events and enjoy free food.

### User Story 4
As a UofT student, I want to report a Food Alert when I notice an issue with its food status or information, so that other users
can stay informed about its current availability. I also want Food Alerts to be automatically removed from active listings after
two hours, so that I do not see outdated food offers.

---

## Use Cases

### Use Case 1
Main Success Scenario:

1. The student opens the free food event search page.

2. The system displays all available free food events.

3. The student optionally enters a search keyword or selects filters.

4. The student submits the search.

5. The system finds available events that match the specified search criteria.

6. The system displays the matching events, including relevant event details.

Alternative Flows:

1. No search criteria: The system displays all available free food events.

2. No matching events: The system displays a message that no events match the selected criteria.

3. No available events: The system displays a message that there are currently no available free food events.


### Use Case 2
1. The student opens the notification page.

2. The system shows the available options and the student’s current preferences (if any).
   
3. The student selects their preference.

4. The student asks to save the preferences.
  
5. The system saves the preferences and confirms to the student that they are saved.

6. Later, a new free food event is published. The system checks the event against the saved preferences of the student.

7. The system adds an unread notification with the event’s information to the notification the student.

8. The student sees the new notification in their notifications list.


Alternative Flows:

4a. The user leaves without saving the preferences.
The system keeps the previous preferences unchanged.
New events are still checked against the previous preferences, and the use case continues at step 6.


6a. The user has no saved preferences.
The system does not match the event with the user, so no notification is added.
The user’s notification list stays empty, and the use case ends.


### Use Case 3
Main Success Scenario:

1. The student opens the Create Food Event page.

2. The student enters event title, description, location, food types, related subjects, registration requirement,
and expiration time.

3. The student submits the form.

4. The system validates the input information.

5. The system generates a unique event ID and records the creation time.

6. The system creates and saves the FoodEvent.

7. The system displays a success message.

Alternative Flows:

1. Invalid input: The system displays message that title cannot be empty or location is required, and does not create the event.

2. Invalid expiration time: The system displays message that expiration time must be in the future and not create the event.


### Use Case 4:Report a Food Alert
Primary Actor: Uoft Students
Preconditions:
The Food Alert exists in the system.
The Food Alert is currently visible to the user.
Trigger:
The user notices an issue with a Food Alert and decides to report it.
Main Success Scenario:

1. The user opens a Food Alert.

2. The user selects the "Report" option.

3. The system displays three report reasons: Incorrect food information, No longer available, Other

4. The user selects a report reason.

5. If the user selects "Other", the system allows the user to enter a custom reason.

6. The user submits the report.

7. The system validates the submitted report.

8. The system creates and saves a Report associated with the Food Alert.

9. The system updates the Food Alert status to REPORTED.

10. The system displays a confirmation message.

11. The Food Alert remains visible to other users.
Extensions:
4a. The user selects "Other" but does not provide a reason, then submitted it.
   4a1. The system displays an error message and asks the user to enter a reason.
Postconditions:
A Report record is stored and associated with the Food Alert.

The Food Alert status is updated to REPORTED.

The original Food Alert information remains unchanged.

The Food Alert remains visible to other users.

### Use Case 5:Automatically Expire a Food Alert
Primary Actor: System
Preconditions:

The Food Alert has been successfully published.

The Food Alert has a recorded publication time.

The Food Alert is currently in the active listings.
Trigger:
Two hours have passed since the Food Alert was published.

Main Success Scenario:
The system checks the publication time of active Food Alerts.

The system compares each Food Alert's publication time with the current time.

The system identifies Food Alerts that have been published for two hours or longer.

The system removes the identified Food Alerts from the active listings.

Extensions:

3a. Less than two hours have passed since the Food Alert was published.

   3a1. The Food Alert remains in the active listings.

3b. The Food Alert has already been removed from the active listings.

   3b1. The system skips the Food Alert.
Postconditions:
The Food Alert is no longer displayed in active listings.

---

## Minimal Viable Product (MVP)

| Team Member | MVP Use Case |
|-------------|--------------|
| Lucy | Filter and search for events |
| Yuchen | Receive Personalized notification |
| Cynthia | Create and publish an event |
| Name | Use Case |


---

## Entities

### Entity 1: FoodEvent
The entity includes relevant features of an event, 
including the given title, its location, the food types, and time that it's added.

### Entity 2: Location
The entity includes information about the location, including the descriptions and 
the latitude and longitude coordinates. 

### Entity 3


(Add additional entities as needed.)

---

## API Usage

**API Name:**

**Documentation:** [Link]

**How it is used in the program:**

---

## Design Diagrams

### UML Class Diagram


### Sequence Diagrams

---

## How to Run

[Instructions for running the program.]

---

## Notes

[Any additional project information.]
