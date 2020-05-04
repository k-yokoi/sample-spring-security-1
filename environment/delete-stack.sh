#!/usr/bin/env bash

stack_name="sample-spring-security-s3"
#stack_name="sample-spring-security-dynamodb-dev"

aws cloudformation delete-stack --stack-name ${stack_name}