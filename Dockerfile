# ── Build stage ─────────────────────────────────────────────
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /build

# Copy Maven wrapper + config
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# FIX: make mvnw executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -q

# Copy source code
COPY src ./src

# Build jar
RUN ./mvnw clean package -DskipTests

# ── Runtime stage ───────────────────────────────────────────
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# safer than fixed jar name
COPY --from=build /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
