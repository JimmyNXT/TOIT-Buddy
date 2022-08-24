docker volume create --name maven-repo
docker run -it --rm -v "$(pwd):/usr/app" -v maven-repo:/root/.m2 -w /usr/app maven mvn clean package
docker-compose up -d --build