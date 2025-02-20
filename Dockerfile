# Sử dụng OpenJDK 17 làm môi trường chạy
FROM openjdk:22-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /app

# Copy toàn bộ code vào container
COPY . /app

# Cấp quyền thực thi cho Maven Wrapper
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Chạy ứng dụng Java
CMD ["java", "-jar", "target/*.jar"]
