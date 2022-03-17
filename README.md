# Notification-Service

The project will NOT be an entire software system, but rather a component within the system that fulfills a need, provides a feature, that serves a greater PURPOSE within the WHOLE purpose of the software. We are going to simulate real situation such as Notification module.

We will implement the "notification module" as API service "Src code for API exist in demo"

## Notification "Templates" management
As mentioned above, your software can send different types of "notifications", surely the "content" of the "registration activation mail" is different from the "content" of the "forget password mail", which in turn is different from the "content" of "your booking sms confirmation message", right ? 
The notifications module, manages those different notification "templates", and the languages these templates can be sent in, and of course the "placeholders" within the content of these templates to be replaced with "actual values"

ex: " Dear ? , your booking of the ? is confirmed. Thanks for using our store :) " 
this would be the template , but when the system "sends" it to the user "Ahmed" who bought the item "Mobile charger" , the actual email would be 
" Dear Ahmed, your booking of the item mobile charger is confirmed, thanks for using our store :) "

So, the "management" of those notifications templates, their subjects, content, available languages, available channels (email , sms) , and placeholders would be the focus of this part.

## 2- Notification "Queuing" & "Sending"
When your "notifications module" gets invoked to send a "notification" to an email address or phone number, it would be a good design decision to NOT actually send the notification within the scope of this "invocation", because this would be mean you will hang the "invoker" till the actual message is sent. So what should we do ? you should implement a "notifications Queue" , where you insert "notifications" that ARE TO BE SENT and that's it, your job is done, and the invoker doesn't have  to wait for anything.


# Technology:
> - Eclips IDE For Enterprise Java Developer
> - MySQL Workbench 8.0 CE
> - mysql-connector-java-5.1.49
> - java
> - Spring Boot
