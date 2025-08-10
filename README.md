# IPL Auction Management System

## Overview

This is a **Spring Boot-based IPL Auction Management System** that allows users to participate in real-time cricket player auctions, similar to the Indian Premier League (IPL). The application supports authentication, role-based access, live bidding via WebSockets, and essential auction features like player management, team dashboards, leaderboard, pagination, searching, and sorting.

## Features

### **User Features**

- **Live Auction**:
  - Join ongoing auctions in real time.
  - Place bids on players.
  - View the current player in auction along with stats.
- **Team Dashboard**:
  - View purchased players, total spent amount, and remaining budget.
- **Player Listings**:
  - Search for players using keywords.
  - Sort players based on different parameters.
  - Pagination for efficient data retrieval.
- **Leaderboard**:
  - View top teams and highest bids in real time.

### **Admin Features**

- Start, pause, and resume the auction.
- Move to the next player in the auction.
- Add and manage players and teams.
- Automatically finalize bids if no new bid is placed within a set time.

### **Security Features**

- **JWT Authentication**: Secure APIs with JSON Web Token.
- **Role-Based Access Control (RBAC)**:
  - **Admin**: Full control over auction and player management.
  - **Team**: Can place bids and manage their team.
  - **Viewer**: Can watch the auction progress.

## Tech Stack

- **Backend**: Java, Spring Boot, Spring Security, WebSocket, JWT, Hibernate, JPA
- **Database**: MySQL
- **Build Tool**: Maven

## Installation & Setup

### **Prerequisites**

- Java 17+
- Maven
- MySQL database
- Postman or WebSocket client (for API & live auction testing)

### **Clone the Repository**

```sh
git clone https://github.com/Sammykhan214/ipl_bidding.git
cd ipl-auction-backend

### **Configure Database**

Update `application.properties` or `application.yml` with database details:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ipl_auction
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### **Build & Run**

```sh
 mvn clean install
 mvn spring-boot:run
```

### **API Endpoints**

| Method | Endpoint                               | Description                                         | Access      |
| ------ | -------------------------------------- | --------------------------------------------------- | ----------- |
| POST   | /auction/auth/register                 | Register a new user/team/viewer                     | Public      |
| POST   | /auction/auth/login                    | Authenticate user & get JWT                         | Public      |
| POST   | /auction/startAuction                  | Start the auction                                   | Admin       |
| GET    | /auction/current-player                | Get details of the current player in auction        | Public      |
| POST   | /auction/bid                           | Place a bid                                         | Team        |
| GET    | /api/bid/player/{id}                   | Get bid history of a player                         | Public      |
| GET    | /auction/players                       | Retrieve all players (with filters)                 | Public      |
| GET    | /auction/players/{id}                  | Retrieve a specific player's details                | Public      |
| POST   | /auction/admin/player/add              | Add a new player                                    | Admin       |
| GET    | /auction/teams                         | Retrieve all teams                                  | Public      |
| GET    | /auction/teams/{id}                    | Retrieve a specific team's details                  | Public      |
| GET    | /api/leaderboard                       | Get the auction leaderboard                         | Public      |
| POST   | /auction/admin/import/players          | Import player data into the database                | Admin       |
| POST   | /auction/admin/import/stats            | Import player stats into the database               | Admin       |



### **Authentication & Authorization**

- JWT-based authentication
- Include JWT in headers:
  ```sh
  Authorization: Bearer your_jwt_token
  ```
- Role-based access control:
  - **Admin**:Manage auction, players, and teams.
  - **Team**:  Participate in bidding and manage team roster.
  - **Viewer**-watch auction updates.

  ```

## Contributing

1. Fork the repository
2. Create a new branch (`feature-branch`)
3. Commit your changes
4. Push to the branch
5. Submit a pull request

## License

This project is licensed under the **MIT License**. See the `LICENSE` file for details.

---

🚀 **Create your dream team!**
