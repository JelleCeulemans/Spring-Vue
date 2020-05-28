## JP-micro-authentication

JP-micro-authentication is part of a larger project called **Beershop**. Beershop is web application where a user can log in or make an account, place an order for some beers, look at their orders, add products, see a list of the available products and can select products as a favorite and view them. The goal of this application is to learn us how to work with microservices, load balancing with eureka and to tie all those small applications together into one big application.

**Beershop** are 9 different projects that work together: 5 microservices, 1 edge service, 1 Zuul gateway, 1 Eureka server and a Vue.js frontend
- [Orders microservice](https://github.com/lennykr/jp-micro-orders)
- [Users microservice](https://github.com/lennykr/jp-micro-users)
- [Favorites microservice](https://github.com/lennykr/jp-micro-favorites)
- [Products microservice](https://github.com/lennykr/jp-micro-products)
- [Authentication microservice](https://github.com/lennykr/jp-micro-authentication)
- [Edge service](https://github.com/lennykr/jp-edge)
- [Zuul Gateway](https://github.com/lennykr/jp-gateway)
- [Eureka server](https://github.com/lennykr/jp-eureka)
- [Vue.js frontend](https://github.com/lennykr/jp-front)

### Micro-authentication
![Java](https://img.shields.io/badge/Written%20in%20-JAVA-red)
- Microservice that deals with the authentication when a user logs in

### How to secure a route

Add the following annotation above the controller method to secure it:

```java
@Secured({"ROLE_CUSTOMER"})
```


### Made by Team 6
- [Jelle Ceulemans](https://github.com/JelleCeulemans1997)
- [Lenny Kraaijenhof](https://github.com/lennykr)
- [Jonas Croonenborghs](https://github.com/JonasCroonenborghs)
- [Robben Praet](https://github.com/RobbenP)
