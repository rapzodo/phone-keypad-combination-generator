# phone-keypad-combination-generator
REST api that generates all possible combinations given a input numeric phone number

request: must be valid number sequence with length between 7 and 10 digits

response: A payload with a list of alpha-numeric combinations, total of Combinations, number of results returned from pagination and the pageSize.

example : http://localhost:8081/keypad/v1/generator/phone/2345666?pageOffSet=0&pageSize=5

{
  "originalInput": "2345666",
  "combinationsList": [
    "adgjmmm",
    "adgjmmn",
    "adgjmmo",
    "adgjmnm",
    "adgjmnn"
  ],
  "pageOffset": 5,
  "combinationsPerPage": 5,
  "currentResults": 5,
  "totalNumberOfCombinations": 2187
}

build application as maven : mvn install

build docker image
docker image build -t keypad-combinations .
Launch image - docker run -d --rm -p 8081:8081 keypad-combinations:latest

The Service is also deployed on AWS ECS cluster under Application Load balancer on 2 AZs:
http://keypad-combinations-lb-162994211.us-west-2.elb.amazonaws.com/swagger-ui.html
