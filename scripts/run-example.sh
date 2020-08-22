echo "starting services and infrastructure using docker compose"
docker-compose up

curl -X POST -d ""{  "product": "coffee",  "qty": }"" localhost:8083/order