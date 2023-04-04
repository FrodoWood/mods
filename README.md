<a name="readme-top"></a>
<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/FrodoWood/mods">
    <img src="src/main/resources/static/images/automods-logo-white-transparent-300.png" alt="Logo" height="80">
  </a>
  
  <h1 align="center">Auto Mods</h1>

</div>

<!-- ABOUT THE PROJECT -->
## About The Project
### We are Group 5

Auto Mods is an e-commerce website that specializes in high-performance aftermarket car parts. The website contains a feature-rich store page that allows the user to filter items by category, max price, car make/model and search products by any keyword such as name, description, category, and sort the items from low-high and high-low price. There is also an Admin section of the website only accessible by admin users which allows the admin to add/edit products, view/update orders and much more! The website has been built to scale up, as all the features are built using OOP principles and have been extensively tested through Unit tests and Integration tests.
#### This is a group project for the Aston University Computer Science CS2TP Team Project module 2022-2023. 
#### Link to live website on Azure: https://automods.azurewebsites.net/
#### Link to task tracker: https://trello.com/b/qJCZBw3O/team-5-board

### Team Members
| Name              | ID        |
| ---               | ---       |
| Jasraj Singh      | 210151813 |
| Jacek Wojtowicz   | 210220027 |
| Shah Kar          | 210060700 |
| Serg Barseghyan   | 190234548 |
| Harun Mahamed     | 210090327 |
| Muhammad Huraira  | 220244709 |

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

[![My Skills](https://skills.thijs.gg/icons?i=java&theme=light)](https://skills.thijs.gg)
[![My Skills](https://skills.thijs.gg/icons?i=spring&theme=light)](https://skills.thijs.gg)
[![My Skills](https://skills.thijs.gg/icons?i=mysql&theme=light)](https://skills.thijs.gg)
[![My Skills](https://skills.thijs.gg/icons?i=html&theme=light)](https://skills.thijs.gg)
[![My Skills](https://skills.thijs.gg/icons?i=css&theme=light)](https://skills.thijs.gg)
[![My Skills](https://skills.thijs.gg/icons?i=javascript&theme=light)](https://skills.thijs.gg)
[![My Skills](https://skills.thijs.gg/icons?i=bootstrap&theme=light)](https://skills.thijs.gg)

We built this website using Java, Spring Boot, Thymeleaf, MySQL, HTML5, CSS, JavaScript, Bootstrap 5

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

* You need to have a Java jdk installed on your machine.
* You also need a MySQL server
 * You can download and install XAMPP which includes a MySQL server from https://www.apachefriends.org/download.html
 * Or you can download and install MySQL Workbench from https://www.apachefriends.org/download.html

### Installation

Follow these instructions to set up this project on your local machine

1. Clone the repo
   ```sh
   git clone https://github.com/FrodoWood/mods.git
   ```
2. Ensure you have latest Java jdk (such as version 17.0.1) by running the following command in your terminal
   ```sh
   java -version
   ```
3. Start your MySQL server

4. You can either:
 * Create a new database called 'mods'.
 * Or import the mods.sql file provided in the home directory of this repository
 
5. In both cases ensure your spring.datasource username and password match the ones defined in the <b>application.properties</b> file found here: "src/main/resources/application.properties"

```
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/mods
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql: true

spring.flyway.url=jdbc:mysql://localhost:3306/mods
spring.flyway.user=root
spring.flyway.password=

spring.thymeleaf.mode=DEBUG
spring.servlet.multipart.max-file-size=1MB

```
 


6. If using VsCode, then install the Spring Boot and Java official extensions
7. Run the application through your IDE's graphical user interface, or
8. Run via Maven (this requires Maven to be installed on your machine)

```sh
mvn spring-boot:run
```
9. Open your website by entering the address http://localhost:8080/ in your preferred browser

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

Once your website is running, you can navigate the home, store, product, about, and contact pages without needing an account. Whereas you would need to create an account, by clicking on the Register link the navbar, to add items to the basket, checkout and view order history.

### Admin panel
If you have created a new database, then you will have to create a new user who will have the ROLE_USER by default and modify the record to ROLE_ADMIN, from that point on, you don't have to open the database at all. 

If you have imported the given database, then simply use the following credentials to access the admin panel:
- Username  = admin
- Password  = Mods@admin23

All the admin actions such as the following can be performed from the admin panel:
* adding a new product 
* updating product details (name, stock, image, etc.)
* changing user roles
* updating order status
* hiding review


<p align="right">(<a href="#readme-top">back to top</a>)</p>
