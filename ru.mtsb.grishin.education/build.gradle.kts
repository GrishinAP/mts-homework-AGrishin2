plugins {
    id("java")
}

group = "hw10"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.codehaus.woodstox/stax2-api
    implementation("org.codehaus.woodstox:stax2-api:4.2.1")
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.2")
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.16.2")
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
    implementation("com.fasterxml.jackson.core:jackson-core:2.16.2")
// https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-xml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.16.2")
// https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-yaml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.16.2")
// https://mvnrepository.com/artifact/org.yaml/snakeyaml
    implementation("org.yaml:snakeyaml:2.2")
// https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.16.2")
    //
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test
//    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.1")
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.14")
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.18")
// https://mvnrepository.com/artifact/org.springframework/spring-context
    implementation("org.springframework:spring-context:5.3.29")
// https://mvnrepository.com/artifact/org.springframework/spring-core
    implementation("org.springframework:spring-core:5.3.29")
// https://mvnrepository.com/artifact/org.springframework/spring-beans
    implementation("org.springframework:spring-beans:5.3.29")
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-configuration-processor
    implementation("org.springframework.boot:spring-boot-configuration-processor:2.7.18")


//implementation("org.springframework.boot:spring-boot-configuration-processor:3.1.5"):3.2.1")
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-autoconfigure
            //    implementation("org.springframework.context.annotation.CommonAnnotationBeanPostProcessor")
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.1")
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security
//    implementation("org.springframework.boot:spring-boot-starter-security:3.2.1")
// https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.16.14")
//    annotationProcessor("org.projectlombok:lombok:1.16.14")
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.boot:spring-boot-starter-security")
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    compileOnly("org.projectlombok:lombok")
//    annotationProcessor("org.projectlombok:lombok")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.springframework.security:spring-security-test")


}

tasks.test {
    useJUnitPlatform()
}