FROM eclipse-temurin:17-jdk-jammy AS base
WORKDIR /build
COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/
COPY pom.xml .

FROM base AS test
WORKDIR /build
COPY ./src src/
RUN apt-get update && \
    apt-get install -y xvfb \
    libglib2.0-0 \
    libnss3 \
    libnspr4 \
    libdbus-1-3 \
    libatk1.0-0 \
    libatk-bridge2.0-0 \
    libcups2 \
    libatspi2.0-0 \
    libxcomposite1 \
    libxdamage1 \
    libxrandr2 \
    libgbm1 \
    libxkbcommon0 \
    libgdk-pixbuf-2.0-0 \
    libcairo-gobject2 \
    libpangocairo-1.0-0 \
    libgtk-3-0 \
    libxcursor1 \
    libpango-1.0-0 \
    libcairo2 \
    python3 \
    libasound2 && \
    rm -rf /var/lib/apt/lists/*

RUN xvfb-run ./mvnw clean test -DitemName="gta5"

RUN ./mvnw allure:report

WORKDIR /build/target/allure-report

EXPOSE 8080
CMD ["python3", "-m", "http.server", "8080"]