# booklist

(Artisan bootcamp learning project)

This is a small CRUD web application that stores and retrieves book information. The title, author, date published, and whether the book has been read is stored. The purpose of `booklist` is to have a means of remembering which books you have read and want to read in order to better share your reading interests.

The booklist server must be run locally at port 8769 (`http://localhost:8769/`). 

## Endpoints

The booklist server can be contacted through the following API endpoints (all requests and responses must be in JSON):

- GET endpoints
  - `/`: welcome message 
  - `/books`: get all book information
  - `/books/read`: get all read books
  - `/books/unread`: get all unread books
  - `/titles`: get all titles 
- POST endpoints:
  - `/books/add`: add a book to the database
  - `/books/remove`: remove a book from the database
  - `/books/markRead`: mark the given book as read
  
## Architecture

`booklist` uses the Spark framework and MySQL database. Travis was used as a CI build tool.
  
