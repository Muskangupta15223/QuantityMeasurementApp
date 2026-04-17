# ── Build stage ──────────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /build

# Copy Maven wrapper and project descriptor first for layer caching
COPY mvnw mvnw.cmd ./
COPY .mvn .mvn
COPY pom.xml ./

# Download dependencies (cached unless pom.xml changes)
RUN ./mvnw dependency:go-offline -q

# Copy source and build the JAR with the production profile
COPY src ./src
RUN ./mvnw clean package -DskipTests -Pproduction

# ── Runtime stage ─────────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=build /build/target/quantity-measurement-app-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
