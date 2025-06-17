FROM postgres:latest

ENV POSTGRES_USER=student
ENV POSTGRES_PASSWORD=student
ENV POSTGRES_DB=mydb

EXPOSE 5433

docker build -t my_postgres .
docker run -d --name orders_pg -p 5433:5432 my_postgres