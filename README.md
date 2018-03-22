# GestionTallerMecanico
proyecto para la gestion de un taller mecánico

Para ejecutar este proyecto en local necesitas:
    - Maven 3.5.0
    - Java 1.8
    
En el CDM de Windows o linea de comandos de Linux pones:
    - mvn clean jetty:run
    - En caso de que no quieras ejecutar los test
     
Si queires desplegarlo en internet necesitaras descargarte Heroku
    - https://devcenter.heroku.com/

Cuando lo tengas instalado te tienes que hacer una cuenta y seguir las instrucciones de la documentación oficial

cuando lo tengas subido (hasta el paso del push) al servidor de Heroku necesitarás

necesitarás ejecutar esto en la linea de comandos preferida:
    - mvn clean heroku:deploy-war
    NOTA: necesitas tener en las dependencias el plugin de heroku fijate en el pom en la parte de plugins está marcado
    
En mi caso esta aplicación está desplegada aqui mismo: https://fast-depths-35905.herokuapp.com/
Si elegis el servidor gratuito como yo la 1º vez os tardará en arrancar porque si no tiene tráfico a los 30 min se suspende y cada mes solo obtendreis de 23 días reales de servicio
