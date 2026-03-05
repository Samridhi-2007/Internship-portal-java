Internship-portal-java
📘 Part of Course: FSAD (Full Stack Application Development)

This project is developed as a part of the FSAD course.
It is a Remote Internship Management Portal built using Spring Boot that helps manage internships, track progress, assign tasks, and collect feedback.

🚀 Overview

The Remote Internship Management Portal is designed to simplify the internship process for students, mentors, and administrators.

It provides a centralized platform to:

Manage internship opportunities

Track intern progress

Assign and monitor tasks

Collect mentor feedback

Manage users and internship records

🏗 Project Architecture

The application follows a layered Spring Boot architecture.

1️⃣ Controller Layer

Handles HTTP requests and API endpoints.

Examples:

UserController

InternshipController

TaskController

FeedbackController

2️⃣ Service Layer

Contains the business logic of the application.

Responsibilities:

Processing internship workflows

Managing tasks and feedback

Handling user operations

3️⃣ Repository Layer

Handles database operations using Spring Data JPA.

Responsibilities:

CRUD operations

Querying internship and user data

4️⃣ Model Layer

Contains Entity Classes representing the database tables.

Examples:

User

Internship

Task

Feedback

🎯 Problem This Project Solves

Students often face challenges such as:

Difficulty finding internships in one place

No easy way to track internship applications

Lack of a structured system to communicate with mentors

Difficulty managing internship tasks and feedback

This portal provides a centralized digital solution for managing the entire internship lifecycle.

⚙️ Technologies Used

Java

Spring Boot

Spring Data JPA

Maven

REST APIs

MySQL / H2 Database

▶️ Running the Project

Run the application using Maven:

mvn spring-boot:run

The application will start on:

http://localhost:8080
