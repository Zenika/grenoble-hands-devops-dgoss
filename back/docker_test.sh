#!/bin/bash
source ../utils/test_image.sh

start_container "-e SPRING_PROFILES_ACTIVE=memory zenika/cities-weather-api"

# Check if server running correctly
run_test_case "curl --silent --request GET http://localhost:8080/actuator/health | grep UP" 10
# Check java version
run_test_case "java --version 2>&1 | grep 11.0.11"

clean_container
