
# Inditex

This is the technical test for inditex




## API Reference

#### Get the price of a product on a dateTime

```http
  GET /price/${productId}/${brandId}/${date}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `productId`      | `Long` | **Required**. product identificator |
| `brandId`      | `Long` | **Required**. brand identificator |
| `date`      | `String` | **Required**. application date |



# To execute:
1) .\mvnw install(on project's directory)
2) .\mvnw spring-boot:run
3) go to http://localhost:8080/swagger-ui.html on your internet browser of your choice
4) if you want to acces to the DB go to http://localhost:8080/h2-console/ (user:a,password:a, JDBC URL: jdbc:h2:mem:inditex)

# Extra
- this project was made with spring boot 4.5.8, loombook, jpa, junit, mockito, java 17, h2 and swagger 
- is made with a simple api rest architecture and folloowing clean code and solid principles
- on test folder you can find multiple test separated by service, repository and controller
- on PriceControllerTest.class you can find the five test cases presented on the document or you can tested by yourself using the swagger-ui(http://localhost:8080/swagger-ui.html)




## Author

- Alexander Barros

