rootProject.name = "app"

// Отключаем неявную компиляцию для SonarQube
gradle.startParameter.systemPropertiesArgs["sonar.gradle.skipCompile"] = "true"
