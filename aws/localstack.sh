#!/bin/bash

## jq install
apt-get install -y jq

## aws configuration
aws configure set aws_access_key_id "ID_KEY"
aws configure set aws_secret_access_key "SECRET_KEY"
aws configure set region "us-east-1"

## sns
TOPIC_EVENT_CREATED="event_created_topic"
ARN_TOPIC_EVENT_CREATED=$(aws --endpoint-url=http://localhost:4566 sns create-topic --name "$TOPIC_EVENT_CREATED" --output text)

## sqs
QUEUE_EVENT_CREATED="event_created_queue"
URL_QUEUE_EVENT_CREATED=$(aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name "$QUEUE_EVENT_CREATED" --output text)
ARN_QUEUE_EVENT_CREATED=$(aws --endpoint-url=http://localhost:4566 sqs get-queue-attributes --queue-url "$URL_QUEUE_EVENT_CREATED" --attribute-names QueueArn | jq -r ".Attributes.QueueArn")

## subscription
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn "$ARN_TOPIC_EVENT_CREATED" --protocol sqs --notification-endpoint "$ARN_QUEUE_EVENT_CREATED" --output text
