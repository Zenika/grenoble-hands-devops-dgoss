#!/bin/bash
source ../utils/test_image.sh

start_container zenika/cities-weather-front

# Check if serving correct frontend page
run_test_case "curl --silent --request GET http://localhost:80 | grep GrenobleHandsOn" 5
# Check nginx version
run_test_case "nginx -version 2>&1 | grep 1.24.0"
# Check no node_modules
run_test_case "! [ -d /usr/share/nginx/html/node_modules ]"

clean_container
