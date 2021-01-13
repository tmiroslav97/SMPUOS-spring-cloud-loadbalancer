# Spring Cloud LoadBalancer

Implementacija spring cloud load balancer- a.

Aplikacije:

Naziv mikroservisa | Kratak opis | Port 
------------ | ------------- | ------------- 
EurekaService  | Za konfiguraciju service discovery je koriscena eureka. |  8761 
Zuul | Za gateway service je koriscen zuul. |  8082 
UserClient | LoadBalancer (apstrakcija za klijente) |  8088 
loadbalancer | Service aplikacija |  8090, 8091, 8092 

### Opšte o spring cloud load balancer
<br>
Spring Cloud LoadBalancer.pptx<br>
Spring Cloud LoadBalancer.pdf<br>

Autor: 
* [Miroslav Tomic](https://github.com/tmiroslav97)
