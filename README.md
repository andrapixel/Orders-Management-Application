# Orders-Management-Application
A Java Swing application that manages the client orders for a warehouse, and uses relational databases to store the information about the clients, products and placed orders.

The database used for storing the data is called WarehouseDB, was created using MySQL, and contains three tables: <b>Clients</b>, <b>Orders</b> and <b>Products</b>.

The application comes with an integrated graphical user interface, which includes four windows, one out of which represents the main "control" window - which provides the user with three main operations (for each operation a new separate window opens): 
1. managing the clients of the warehouse (adding a new client, editing information about a client, deleting a client, viewing all clients in a JTable)
2. managing the products (adding/editing/deleting a product, viewing all products in a JTable)
3. managing the placed orders: the user is able to select an existing product, select an existing client, and insert a desired quantity for the product in order to create a valid order. After the order is finalized, the product stock is decremented and a bill is generated in a text file.

### Implementation details
* This project is designed according to the <i>Layered Architecture pattern</i>. 
* Reflection techniques were used to create a generic class that contains the methods for accessing the database. The main methods that were implemented had the following functionalities: creating a new object, editing an existing object, deleting an object and finding an object. The queries for accessing the database for a specific object that corresponds to a table were generated dinamically through reflection.
* Javadoc was used for documenting the classes and their corresponding methods.

<i> For a more in-depth report on the creation of this project, check the `doc` folder.
