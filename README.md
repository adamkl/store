# store
Programming Exercise for Oracle/NetSuite

I decided to structure the codebase follwing a [Ports & Adapters](https://fideloper.com/hexagonal-architecture)/[Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)/[Onion Architecture](http://jeffreypalermo.com/blog/the-onion-architecture-part-1/) approach, and using Domain Driven Design to build out the business logic. This isolates the business domain from external concerns, making it easy to integrate with different systems by "plugging" in adaptors to back-end databases (i.e. Repository pattern) and layering decoupled API layers on top (i.e. REST, GraphQL, SOAP/XML).

## Logical package structure is as follows

### Framework

Contains the code required to interact with the outside world (e.g. this would be where code for GraphQL schema/resolvers, REST handlers, Server-rendered UI, Database connections, etc. would go). In this example, we have simple repository implementations for managing carts and inventory items

### Application

Contains the code required execute application use cases. Orchestrates operations using objects/logic defined in the business domain model. I decided this would be a good fit for the `Store` interface; an application layer service that acts as an entry point, providing use-case based methods such as `addItemToCart` and `addItemToStore`. The `Store` executes its functionality by using `repositories` for data management, and domain objects that encapsulate the business logic

### Domain

Contains the code that defines the business domain logic. Should be made up of data structures, functions, or classes that encapsulate business functionality. Here I created new objects like `Cart`, `InventoryItem`, `TaxCalculator`, and `DiscountCalculator`

### Utils
Contains crosscutting functionality that can be used across all application layers (authZ, logging, etc.)
