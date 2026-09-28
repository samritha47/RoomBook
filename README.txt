ROOMBOOK - SIMPLE STUDENTMANAGEMENT-STYLE PROJECT

Technology:
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- HTML/CSS/JavaScript

IMPORTANT:
This version intentionally uses the same basic style as the StudentManagement project:
Entity -> Repository -> Service -> Controller -> MySQL

The controller uses:
- @RequestBody for object data
- @PathVariable for IDs in the URL

It does NOT use @RequestParam.

Main features:
1. Room CRUD
2. Employee CRUD
3. Booking CRUD
4. Room capacity validation
5. Time validation
6. Booking conflict detection
7. Cancel booking
8. Check-in booking

Database:
CREATE DATABASE roombook;

Then check src/main/resources/application.properties.
If your MySQL password is not root, change:
spring.datasource.password=root

Run:
mvn spring-boot:run

Or on Windows:
mvnw.cmd spring-boot:run

Open:
http://localhost:8080

Example POST /employees:
{
  "name": "Samritha",
  "email": "samritha@gmail.com",
  "department": "CSE"
}

Example POST /rooms:
{
  "name": "Conference Room A",
  "capacity": 10,
  "projector": true,
  "whiteboard": true,
  "active": true
}

Example POST /bookings:
{
  "roomId": 1,
  "employeeId": 1,
  "startTime": "2026-10-01T10:00:00",
  "endTime": "2026-10-01T11:00:00",
  "attendees": 5
}
