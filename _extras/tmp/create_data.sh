#!/bin/bash

for entry in ./*
do
  echo "$entry"
  curl -X POST -H "Content-Type: application/json" -d @"$entry" http://localhost:8082/api/v1/phrase
done
