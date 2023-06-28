#!/bin/bash
c_id=unset
# Run a test case with a retry number if test is failing
# Example: To run a local curl with 5 attempts
# run_test_case curl -v --request GET http://localhost:8080/actuator/health 5 
run_test_case () {
    if [ -z $2 ]; then
        attempts=1
    else
        attempts=$2
    fi

    failure=1
    for i in $(seq 1 $attempts) ; do
        docker exec $c_id sh -c "$1"
        if [ $? == 0 ]; then
            echo "Test Case: $1 ... Passed"
            failure=0
            break 
        fi
        sleep 1
    done

    if [ $failure == 1 ]; then
        echo "Test Case: $1 ... Failed!"
    fi
}

start_container () {
    echo "Starting container..."
    c_id=$(docker run -d $1)
}

clean_container () {
    echo "Cleaning up..."
    docker stop -t 0 $c_id
    docker rm -f $c_id
    echo "Environment cleaned up!"
}