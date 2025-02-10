# Author: Raven Burkard
sudo docker-compose down || true
sudo docker rmi cardclub/server:latest || true
sudo docker build . -t cardclub/server:latest
sudo docker-compose up -d
sudo docker image prune -f --filter label=stage=builder
