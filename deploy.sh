mvn clean compile assembly:single
scp target/rpg-chart-api-jar-with-dependencies.jar root@remote:/opt/rpg-chart-api/rpg-chart-api.jar