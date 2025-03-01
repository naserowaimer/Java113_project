# Library Management System

## Cover Sheet
- **Project Title**: Library Management System
- **Course**: CSC113
- **Student**: Nasser Alowaymir
- **KSU ID number**: 445102817
- **Division number**: 77965

## Introduction
The Library Management System is a Java program designed to manage a library's book inventory and user information. The system supports user interaction through a menu-style interface and provides various services such as adding, removing, and searching for books and users. The system is implemented using object-oriented principles, including inheritance, polymorphism, composition, and aggregation.

## UML Diagram
![UML Diagram](https://img.plantuml.biz/plantuml/dsvg/hLLDRzGm4Btlhx3s5BhEWIj2g6NXWBHKg8KBY8EJcAv5Og_iIM7v-Ezud5axJim5qKDiltdpvaCptMi7mi8uz9ks5ywnWsgii6VsSyFyNyrU6lFrqsVM-CCHzC59ww7H7nT8QGYKlHar92nm4hQxg5Te6yHt57USqGjrtHDYFXhLCT5rIAdm4_GpgvM3UPHbGW2S5BOz1i8zMAKV62ZevJ84ngtm4z8jX2WJ4A0GWK0Xj1XY1EhKWuHGMpNFRiBnXlZl3VKVWTZyUTOv94Ou6fjZoZKw1SXhvhaZd_jGLWHXLk_HfLhZDy6vO7ZZPQOgHEF0YXROL0xMdPL37IBafkkiz3GndOJkZOOWCPqKRTballJcIyTrdhTYI7MHE-9qZIOIGXDTeoaM60Rav9NXplGoL9_ja1AQAjybvDyA-7kejt9eOeTtzFpVGGucbbApk6hqZdj1xrS29Ekz1rxcc9KmseJkWcpQN9wKU2x2b_xPajCILTbQqiP54BqGF93aod0NnUU5K-kM0-p0IWavrYeuBrbUBOT-gDFUT3AVlofJvND1dcdnXSnwlE-3H64_zrwiAl1Hje1_306oLj3GB5iX1w7w73eTZPQt8mvYlatLt8SNwEj4NYEraE2xv3GbUVdPsZxTihf-mRPNssaydarZhhGhSd1AvXny1y9LNUCPHm0vmyausJ7p9T4CqQX4JnFEcLO9yCHl2euJzValpul_11962xkvbheRX_uF)

## Implementation and Design Details

### Class Library:
- **Instance Attributes**:
  - `books`:  array to store the books in the library.
  - `users`:  array to store the users of the library.
  - `bookCount`:  current count of books in the library.
  - `userCount`:  current count of users in the library.
- **Methods**:
  - `Library(int maxBooks, int maxUsers)`: Constructor to initialize the library with maximum number of books and users.
  - `addBook(Book book)`: Add a book to the library if there is space available.
  - `removeBook(Book book)`: Remove a book from the library if it exist.
  - `searchBook(String title)`: Searche for book by its title and returns the book if found.
  - `addUser(User user)`: Add user to the library if there is space available.
  - `removeUser(User user)`: Remove user from the library if they exist.
  - `searchUser(String name)`: Searche for user by his name and return the user if found.
  - `getUsers()`: Return array of all users in the library.
  - `getBooks()`: Return array of all books in the library.

### Class Book:
- **Instance Attributes**:
  - `title`:  title of the book.
  - `author`: author of the book.
  - `isbn`: ISBN number of the book.
- **Methods**:
  - `Book(String title, String author, String isbn)`: Constructor to initialize the book with title, author, ISBN number.
  - `getTitle()`: Return the title of the book.
  - `getAuthor()`: Return the author of the book.
  - `getIsbn()`: Return the ISBN of the book.

### Abstract Class User:
- **Instance Attributes**:
  - `name`: The name of the user.
  - `address`: The address of the user.
  - `contact`: The contact information of the user.
- **Methods**:
  - `User(String name, Address address, Contact contact)`: Constructor to initialize the user with name, address, contact information.
  - `getName()`: Return the name of the user.
  - `getAddress()`: Return the address of the user.
  - `getContact()`: Return the contact information of the user.
  - `abstract printRole()`: Abstract method to print the role of the user.

### Class Librarian:
- **Methods**:
  - `Librarian(String name, Address address, Contact contact)`: Constructor to initialize the librarian with name, address, contact information.
  - `printRole()`: Print that the user is a librarian.

### Class Member:
- **Methods**:
  - `Member(String name, Address address, Contact contact)`: Constructor to initialize the member with name, address and contact information.
  - `printRole()`: Print that the user is a member.

### Class Loan:
- **Instance Attributes**:
  - `book`: The loaned book.
  - `user`: The user who loaned the book.
  - `loanDate`: The date when the book loaned.
  - `returnDate`: book return date .
- **Methods**:
  - `Loan(Book book, User user, Date loanDate, Date returnDate)`: Constructor to initialize the loan with book, user, loan date, and return date.
  - `getBook()`: Return the loaned book
  - `getUser()`: Return user who loaned the book.
  - `getLoanDate()`: Return loan date.
  - `getReturnDate()`: Return return date

### Class Address:
- **Instance Attributes**:
  - `street`: the street address.
  - `city`: The city.
  - `state`: The state.
  - `zipCode`: The zip code.
- **Methods**:
  - `Address(String street, String city, String state, String zipCode)`: Constructor to initialize the address with street, city, state, and zip code.
  - `getStreet()`: Return the street address.
  - `getCity()`: Return the city.
  - `getState()`: Return the state.
  - `getZipCode()`: Returnthe zip code.

### Class Contact:
- **Instance Attributes**:
  - `email`: The email address.
  - `phoneNumber`: The phone number.
- **Methods**:
  - `Contact(String email, String phoneNumber)`: Constructor to initialize the contact with email and phone number.
  - `getEmail()`: Return the email address.
  - `getPhoneNumber()`: Return the phone number.

## Source Code
Download the source code [here](https://github.com/naserowaimer/Java113_project/blob/main/phase_1/Source.zip).

## Sample Run Screenshot
![Sample Run Screenshot]([path_to_sample_run_screenshot](https://github.com/naserowaimer/Java113_project/blob/main/phase_1/sample_run.jpg)
