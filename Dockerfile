# Используем официальный образ OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл сборки (JAR)
COPY target/footballEvents.jar footballEvents.jar

# Открываем порт (по умолчанию Spring Boot использует 8080)
EXPOSE 8080

# Команда запуска
CMD ["java", "-jar", "app.jar"]