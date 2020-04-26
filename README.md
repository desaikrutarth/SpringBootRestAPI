# SpringBootRestAPI
Products API

Products API is a rest API. It supports GET, POST, PUT and DELETE Http methods.
It accepts http call and processed by spring controller. It uses spring boot, JPA repository and stores in H2 db.

Technology
•	Spring boot
•	JPA
•	H2 db

Sample Json contract payload
   {
        "id": 1,
        "category": "Electronics",
        "brand": "Apple",
        "productName": "iPhone 11",
        "price": 999.99,
        "currencyCode": "USD"
   }

GET Method
GET method supports get by Id and get data for all the ids.

Get All:

http://localhost:8080/products
 
Get by ID:

http://localhost:8080/products/4

POST Method:

http://localhost:8080/createProduct


Json Payload body: 
    {
        "category": "Electronics",
        "brand": "Samsung",
        "productName": "TV",
        "price": 1000,
        "currencyCode": "USD"
    }
    
PUT:

http://localhost:8080/products/4

DELETE:

http://localhost:8080/products/1

Database:

H2 Database URL:

http://localhost:8080/h2/
