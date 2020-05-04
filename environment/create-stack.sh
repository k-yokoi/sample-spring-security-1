#!/usr/bin/env bash

stack_name="sample-spring-security-s3"
#stack_name="sample-spring-security-dynamodb-dev"
#stack_name="sample-spring-security-vpc"
template_path="s3-cfn.yml"
#template_path="message-dynamodb-cfn.yml"
#template_path="vpc-cfn.yml"
parameters="EnvType=Dev"
#aws cloudformation create-stack --stack-name ${stack_name} --template-body file://${template_path} --capabilities CAPABILITY_IAM
# It is better cloudformation deploy option because command can execute even if stack existing(no need to delete existing stack).

if [ "$parameters" == "" ]; then
    aws cloudformation deploy --stack-name ${stack_name} --template-file ${template_path} --capabilities CAPABILITY_IAM
else
    aws cloudformation deploy --stack-name ${stack_name} --template-file ${template_path} --parameter-overrides ${parameters} --capabilities CAPABILITY_NAMED_IAM
fi