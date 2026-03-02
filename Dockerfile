# Используем официальный образ OpenJDK

FROM openjdk:21-ea-1-buster
# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл сборки (JAR)
COPY target/footballEvents-0.0.1-SNAPSHOT.jar footballEvents.jar

# Открываем порт (по умолчанию Spring Boot использует 8080)
EXPOSE 8080

# Команда запуска
CMD ["java", "-jar", "footballEvents.jar"]