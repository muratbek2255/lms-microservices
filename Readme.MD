# LMS-Project
It is a simple educational portal with courses, exams, chat and payment

## Installation


```bash
git clone https://github.com/muratbek2255/lms.microservices.git
cd cinematica-demo
```

## Create Postgresql DB
```bash
CREATE DATABASE your_db;

spring.datasource.url=jdbc:postgresql://your_localhost/your_db
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=your_postgres_username
spring.datasource.password=your_password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Usage

## 1.Authentication
```auth
    http://localhost:8080/auth/register
    {
        "phoneNumber": "+996xxxxxx",
        "password": "password"
    }
          
    RESPONSE:
        {
           "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk2NzA3ODg4OTk5IiwiaWF0IjoxNjgxODgxNzQ2LCJleHAiOjE2ODE4ODMxODZ9.0Ga7KenkNhNFLPHZCH2SbBR_WBrepdm5-pZwqNw7doE",
           "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk2NzA3ODg4OTk5IiwiaWF0IjoxNjgxODgxNzQ2LCJleHAiOjE2ODI0ODY1NDZ9.iFkUd79GJ9mj1KZhYbDZp3HQdumGR1VvRnVJ2yXdFzk"
        }  
```
```auth
   http://localhost:8080/auth/authenticate
   
   Add Token in Header: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk2NzA3ODg4OTk5IiwiaWF0IjoxNjgxODgxNzQ2LCJleHAiOjE2ODE4ODMxODZ9.0Ga7KenkNhNFLPHZCH2SbBR_WBrepdm5-pZwqNw7doE
   
   {
        "phoneNumber": "some_number",
        "password": "password",
        "deviceInfo": {
            "deviceId": "1",
            "deviceType": "DEVICE_TYPE_ANDROID"
        }
   }
   RRESPONSE:
        {
           "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk2NzA3ODg4OTk5IiwiaWF0IjoxNjgxODgxNzQ2LCJleHAiOjE2ODE4ODMxODZ9.0Ga7KenkNhNFLPHZCH2SbBR_WBrepdm5-pZwqNw7doE",
           "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk2NzA3ODg4OTk5IiwiaWF0IjoxNjgxODgxNzQ2LCJleHAiOjE2ODI0ODY1NDZ9.iFkUd79GJ9mj1KZhYbDZp3HQdumGR1VvRnVJ2yXdFzk"
        }  
```
```auth
   http://localhost:8080/auth/logout
   Add Token in Header: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk2NzA3ODg4OTk5IiwiaWF0IjoxNjgxODgxNzQ2LCJleHAiOjE2ODE4ODMxODZ9.0Ga7KenkNhNFLPHZCH2SbBR_WBrepdm5-pZwqNw7doE
```
```auth
   http://localhost:8080/auth/changePassword/{id}
   Add Token in Header: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk2NzA3ODg4OTk5IiwiaWF0IjoxNjgxODgxNzQ2LCJleHAiOjE2ODE4ODMxODZ9.0Ga7KenkNhNFLPHZCH2SbBR_WBrepdm5-pZwqNw7doE
   {
        "password": "somepassword"
   }
   
   Response: "Change Password"
```
```token
   http://localhost:8080/auth/token/refresh-token
   Add Token in Header: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk2NzA3ODg4OTk5IiwiaWF0IjoxNjgxODgxNzQ2LCJleHAiOjE2ODE4ODMxODZ9.0Ga7KenkNhNFLPHZCH2SbBR_WBrepdm5-pZwqNw7doE
```
```token
   http://localhost:8080/auth/token/validate
   Add Token in Param: token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk2NzA3ODg4OTk5IiwiaWF0IjoxNjgxODgxNzQ2LCJleHAiOjE2ODE4ODMxODZ9.0Ga7KenkNhNFLPHZCH2SbBR_WBrepdm5-pZwqNw7doE
```
```token
   http://localhost:8080/auth/token/parse
   Add Token in RequestBody: token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk2NzA3ODg4OTk5IiwiaWF0IjoxNjgxODgxNzQ2LCJleHAiOjE2ODE4ODMxODZ9.0Ga7KenkNhNFLPHZCH2SbBR_WBrepdm5-pZwqNw7doE
```

## 2.Course
```course
   http://localhost:8080/courses/addOwnerToCourse
   Add OwnerId To Course-Service in RequestBody: ownerId: 10
```
```course
   http://localhost:8080/courses/byOwnerId/{ownerId}
   Get By ownerId course
```
```course
   http://localhost:8080/courses/byCategoryName?categoryName=Something
   Get By categoryName course
```
```course
   http://localhost:8080/courses/addTransaction/{id}
   Add courseId to PaymentId
```
```course
   http://localhost:8080/courses/addCourseToExam
   add CourseId To exam-service Entity Exam
```
```course
   http://localhost:8080/courses/addCourseToQuestion
   add CourseId To exam-service Entity Question
```


## 3.Exam
```exam
   http://localhost:8080/exam_question/takeExam?password=
   Take Exam: Param("password"), RequestBody answer
```

## 4.Payment
```rent
  http://localhost:8000/api/v1/payment:
        Post-method: Payment is checked
```    
      http://localhost:8000/api/v1/payment/check/{id}:
        Post-method: Payment is checked
```
  http://localhost:8000/api/v1/payment
          Put-method:Result: Payment Created!
```              
      http://localhost:8000/api/v1/payment/setStatus/{id}
          Put-method: Result: Your status in payment: STATUS_SUCCESS
``` 
  http://localhost:8000/api/v1/payment/rollbackStatus/{id}
          Put-method: Result: Rollback payment
