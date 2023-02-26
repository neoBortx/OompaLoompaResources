# OompaLoompaResources

## Architecture

This application has been build following the Clean architecture splitting the responsibilities into several layers.

- Data: Defines the domain entities uses for the business logic. (Doesn't depends on anything)
- Infrastructure: Platform specific layer. In charge to access and store the data. (Depends on Data)
- Repository: This layer manages and coordinates the data retrieved from the infrastructure. (Depends on Data and Infrastructure)
- UserCases: This layer applies the business logic. Because this is a very simple application this layer is anemic. (Depends on Data and Repository)
- App: Another platform specific layer. Contains the presentation layer Because this is a very simple application I've put the presentation layer
  here. (Depends on Data and UserCases)

## Libraries

### Network

- Retrofit: The rest client

### Data

- Paging 3: Allow us to cache the rest data into a Room data base and implements the single source of truth pattern in the repository layer
- Room: The storage system introduced in Jetpack compose. Abstract us of managing the data base.

### Presentation

- compose: The new framework
- navigation: Used to navigate between views.
- constraint layout: Allow us to build layouts putting elements by the size and position of his parent and siblings of the visual three.
- glide: For access, print and cache remote images.

### Concurrency

- Coroutines: To manage threads and RX programming

### Dependency injection

- Hilt: Allow us to apply dependency injection into our project and apply the inversion of dependencies pattern

### Testing

- mockk: fFr unit testing
- compose.ui-test: For ui and end to end testing 
