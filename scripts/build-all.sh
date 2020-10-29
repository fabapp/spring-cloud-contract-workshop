cd ..
echo "create docker container for customer"
cd ./customer
./mvnw clean package spring-boot:build-image

cd ..
echo "create docker container for cashier"
cd ./cashier
./mvnw clean package  spring-boot:build-image

cd ..
echo "create docker container for barista"
cd ./barista
./mvnw clean package spring-boot:build-image
cd ..
