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

sns-new-event:
		aws --endpoint-url=http://localhost:4566 sns publish --topic-arn arn:aws:sns:us-east-1:000000000000:event_created_topic --message 'Test Message!'