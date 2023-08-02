# Usa la imagen de Java que incluya el JRE
FROM adoptopenjdk:11-jre-hotspot

# Copia el JAR de la aplicación al contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar /app.jar

# Comando para ejecutar la aplicación cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "/app.jar"]