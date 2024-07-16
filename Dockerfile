# Utiliser une image de base JDK 17 slim pour exécuter l'application
FROM openjdk:17-slim

# Définir le répertoire de travail dans l'image Docker
WORKDIR /app

# Installer curl pour télécharger le JAR depuis Nexus
RUN apt-get update && apt-get install -y curl jq

# Script pour récupérer le dernier JAR snapshot depuis Nexus
RUN curl -s -u admin:nexus -o latest_snapshot.json "http://10.0.0.4:8081/service/rest/v1/search/assets/download?repository=maven-snapshots&maven.groupId=com.rfc&maven.artifactId=RFCEcommerce&maven.extension=jar&maven.baseVersion=0.0.1-SNAPSHOT" && \
    LATEST_JAR_URL=$(jq -r '.items | sort_by(.downloaded) | .[-1].downloadUrl' latest_snapshot.json) && \
    curl -u admin:nexus -O $LATEST_JAR_URL

# Supprimer curl pour réduire la taille de l'image et nettoyer le cache
RUN apt-get remove -y curl jq && apt-get clean && rm -rf /var/lib/apt/lists/*

# Exposer le port sur lequel l'application écoute
EXPOSE 8089

# Définir la commande par défaut pour exécuter l'application
CMD ["java", "-jar", "RFCEcommerce-0.0.1-SNAPSHOT.jar"]
