#!/usr/bin/env bash
docker run -p 5432:5432 -e POSTGRES_USER=bk -e PGUSER=bk -e POSTGRES_PASSWORD=bk postgres:latest