# Utiliser une image de base JDK 17 slim pour exécuter l'application
FROM openjdk:17-slim

# Définir le répertoire de travail dans l'image Docker
WORKDIR /app

# Installer curl pour télécharger le JAR depuis Nexus
RUN apt-get update && apt-get install -y curl

# Télécharger le fichier JAR depuis Nexus
RUN curl -u admin:nexus -O http://10.0.0.4:8081/repository/maven-snapshots/com/rfc/RFCEcommerce/0.0.1-SNAPSHOT/RFCEcommerce-0.0.1-SNAPSHOT.jar


# Exposer le port sur lequel l'application écoute
EXPOSE 8089

# Définir la commande par défaut pour exécuter l'application
CMD ["java", "-jar", "RFCEcommerce-0.0.1-SNAPSHOT.jar"]
