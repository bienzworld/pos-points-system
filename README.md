## POS ECommerce integrated API that offers points system
* This API manages POS integrated ecommerce that offers point system and has a vast collection of payment method.

## Requirements
* Java 8
* PostgreSQL
* Spring
* Maven

## How to use this spring-boot project
* Install packages with `mvn package`
* Run `mvn spring-boot:run` for starting the application (or use your IDE)
* Install PostgreSQL

### Application (with PostgreSQL) You can access the url below for testing it :
* Swagger UI : http://localhost:8080/swagger-ui.html

### Restrictions
- use java 8

## Features
<table>
  <tr><th>No</th><th>Feature</th><th>Method</th><th>URI</th><th>Note</th></tr>
  <tr><td>1</td><td>POST Recieve Payment</td><td>POST</td><td>/api/v1/pos/payment</td><td>Required HEADER:{price}, {priceModifier}, {paymentMethod}, {datetime}</td></tr>
  <tr><td>2</td><td>GET Payment history based on the specified date</td><td>GET</td><td>/api/v1/pos/history</td><td>Required HEADER:{startDate}, {endDate}</td></tr>
</table>

## Accepted Payment Method
<table>
  <tr><th>No</th><th>Payment Methode</th><th>Possible Final Price</th><th>Points</th></tr>
  <tr><td>1</td><td>CASH</td><td>price * 0.9 ~ price</td><td>price * 0.05</td></tr>
  <tr><td>2</td><td>CASH ON DELIVERY</td><td>price ~ price * 1.02</td><td>price * 0.05</td></tr>
  <tr><td>3</td><td>VISA</td><td>price * 0.95 ~ price * 1</td><td>price * 0.03</td></tr>
  <tr><td>4</td><td>MASTER CARD</td><td>price * 0.95 ~ price * 1</td><td>price * 0.03</td></tr>
  <tr><td>5</td><td>AMEX</td><td>price * 0.98 ~ price * 1.01</td><td>price * 0.02</td></tr>
  <tr><td>6</td><td>JCB</td><td>price * 0.95 ~ price * 1</td><td>price * 0.05</td></tr>
</table>

## How to start development
1. Get source code from [repository](https://github.com/bienzworld/pos-points-system.git)
    ```bash
    $ git clone https://github.com/bienzworld/pos-points-system.git
    ``` 