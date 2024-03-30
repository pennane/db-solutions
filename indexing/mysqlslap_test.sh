#!/bin/bash

combinations=(
  "1 10"
  "5 20"
  "10 5"
  "20 1"
)


for combo in "${combinations[@]}"; do
  read -r concurrency iterations <<< "$combo"
    echo "Running mysqlslap with concurrency=$concurrency and iterations=$iterations"
    mysqlslap --user=test --concurrency="$concurrency" --iterations="$iterations" --create-schema=bigcompany --query=queries.sql --port=3306
  done

echo "Done"
