docker-start:
		docker-compose -f docker/docker-compose.yml up -d

docker-stop:
		docker-compose -f docker/docker-compose.yml down

docker-clean:
		docker container prune; docker image prune; docker volume prune

docker-restart:
		@make docker-stop
		@make docker-clean
		@make docker-start