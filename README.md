# Inventory REST API

This is a basic REST API with CRUD functionalities for a simple inventory.

## Prerequisites
To run this on your system, make sure you have installed:
- Java 17 or above
- Spring Tool Suite 4 or a similar IDE
- Postman

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/sambhavmahajan/Inventory_Rest_API.git
    ```

2. Open the project in your IDE and run it.

## API Endpoints

The following endpoints are available:

- **GET**
  - Fetch item by ID: `http://localhost:8080/fetch/{id}`
  - Get all items: `http://localhost:8080/getall`

- **POST**
  - Add an item: `http://localhost:8080/add/{id}/{name}`
  - Remove an item: `http://localhost:8080/remove/{id}`
  - Delete an item: `http://localhost:8080/delete/{id}`

## License

This project is licensed under the Apache [License](LICENSE) 2.0
