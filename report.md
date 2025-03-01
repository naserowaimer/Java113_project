# Library Management System

## Cover Sheet
- **Project Title**: Library Management System
- **Team Members**: [Your names and student IDs here]
- **Division of Work**: [Division of work among team members here]

## Introduction
The Library Management System is a Java program designed to manage a library's book inventory and user information. The system supports user interaction through a menu-style interface and provides various services such as adding, removing, and searching for books and users. The system is implemented using object-oriented principles, including inheritance, polymorphism, composition, and aggregation.

## UML Diagram
![UML Diagram](https://img.plantuml.biz/plantuml/dsvg/hLLDRzGm4Btlhx3s5BhEWIj2g6NXWBHKg8KBY8EJcAv5Og_iIM7v-Ezud5axJim5qKDiltdpvaCptMi7mi8uz9ks5ywnWsgii6VsSyFyNyrU6lFrqsVM-CCHzC59ww7H7nT8QGYKlHar92nm4hQxg5Te6yHt57USqGjrtHDYFXhLCT5rIAdm4_GpgvM3UPHbGW2S5BOz1i8zMAKV62ZevJ84ngtm4z8jX2WJ4A0GWK0Xj1XY1EhKWuHGMpNFRiBnXlZl3VKVWTZyUTOv94Ou6fjZoZKw1SXhvhaZd_jGLWHXLk_HfLhZDy6vO7ZZPQOgHEF0YXROL0xMdPL37IBafkkiz3GndOJkZOOWCPqKRTballJcIyTrdhTYI7MHE-9qZIOIGXDTeoaM60Rav9NXplGoL9_ja1AQAjybvDyA-7kejt9eOeTtzFpVGGucbbApk6hqZdj1xrS29Ekz1rxcc9KmseJkWcpQN9wKU2x2b_xPajCILTbQqiP54BqGF93aod0NnUU5K-kM0-p0IWavrYeuBrbUBOT-gDFUT3AVlofJvND1dcdnXSnwlE-3H64_zrwiAl1Hje1_306oLj3GB5iX1w7w73eTZPQt8mvYlatLt8SNwEj4NYEraE2xv3GbUVdPsZxTihf-mRPNssaydarZhhGhSd1AvXny1y9LNUCPHm0vmyausJ7p9T4CqQX4JnFEcLO9yCHl2euJzValpul_11962xkvbheRX_uF)

## Implementation and Design Details

### Class Library:
- **Instance Attributes**:
  - `books`: An array to store the books in the library.
  - `users`: An array to store the users of the library.
  - `bookCount`: The current count of books in the library.
  - `userCount`: The current count of users in the library.
- **Methods**:
  - `Library(int maxBooks, int maxUsers)`: Constructor to initialize the library with a maximum number of books and users.
  - `addBook(Book book)`: Adds a book to the library if there is space available.
  - `removeBook(Book book)`: Removes a book from the library if it exists.
  - `searchBook(String title)`: Searches for a book by its title and returns the book if found.
  - `addUser(User user)`: Adds a user to the library if there is space available.
  - `removeUser(User user)`: Removes a user from the library if they exist.
  - `searchUser(String name)`: Searches for a user by their name and returns the user if found.
  - `getUsers()`: Returns an array of all users in the library.
  - `getBooks()`: Returns an array of all books in the library.

### Class Book:
- **Instance Attributes**:
  - `title`: The title of the book.
  - `author`: The author of the book.
  - `isbn`: The ISBN of the book.
- **Methods**:
  - `Book(String title, String author, String isbn)`: Constructor to initialize the book with a title, author, and ISBN.
  - `getTitle()`: Returns the title of the book.
  - `getAuthor()`: Returns the author of the book.
  - `getIsbn()`: Returns the ISBN of the book.

### Abstract Class User:
- **Instance Attributes**:
  - `name`: The name of the user.
  - `address`: The address of the user.
  - `contact`: The contact information of the user.
- **Methods**:
  - `User(String name, Address address, Contact contact)`: Constructor to initialize the user with a name, address, and contact information.
  - `getName()`: Returns the name of the user.
  - `getAddress()`: Returns the address of the user.
  - `getContact()`: Returns the contact information of the user.
  - `abstract printRole()`: Abstract method to print the role of the user.

### Class Librarian:
- **Methods**:
  - `Librarian(String name, Address address, Contact contact)`: Constructor to initialize the librarian with a name, address, and contact information.
  - `printRole()`: Prints that the user is a librarian.

### Class Member:
- **Methods**:
  - `Member(String name, Address address, Contact contact)`: Constructor to initialize the member with a name, address, and contact information.
  - `printRole()`: Prints that the user is a member.

### Class Loan:
- **Instance Attributes**:
  - `book`: The book that is loaned.
  - `user`: The user who loaned the book.
  - `loanDate`: The date the book was loaned.
  - `returnDate`: The date the book is to be returned.
- **Methods**:
  - `Loan(Book book, User user, Date loanDate, Date returnDate)`: Constructor to initialize the loan with a book, user, loan date, and return date.
  - `getBook()`: Returns the book that is loaned.
  - `getUser()`: Returns the user who loaned the book.
  - `getLoanDate()`: Returns the loan date.
  - `getReturnDate()`: Returns the return date.

### Class Address:
- **Instance Attributes**:
  - `street`: The street address.
  - `city`: The city.
  - `state`: The state.
  - `zipCode`: The zip code.
- **Methods**:
  - `Address(String street, String city, String state, String zipCode)`: Constructor to initialize the address with street, city, state, and zip code.
  - `getStreet()`: Returns the street address.
  - `getCity()`: Returns the city.
  - `getState()`: Returns the state.
  - `getZipCode()`: Returns the zip code.

### Class Contact:
- **Instance Attributes**:
  - `email`: The email address.
  - `phoneNumber`: The phone number.
- **Methods**:
  - `Contact(String email, String phoneNumber)`: Constructor to initialize the contact with email and phone number.
  - `getEmail()`: Returns the email address.
  - `getPhoneNumber()`: Returns the phone number.

## Source Code
- Submit the source code in a compressed ZIP format.
- [Place source_code.zip file here]

## Sample Run Screenshot
- Provide a screenshot of a sample run of your program.
- ![Sample Run Screenshot](path_to_sample_run_screenshot)

## Conclusion
The Library Management System project demonstrates the application of object-oriented programming concepts in Java. It effectively manages a library's book inventory and user information through a user-friendly interface. The project highlights the importance of inheritance, polymorphism, composition, and aggregation in designing a robust and maintainable system. Future enhancements could include additional features such as user authentication, advanced search capabilities, and an improved graphical user interface.

**Good luck with your project!**