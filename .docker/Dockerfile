FROM postgres
ENV POSTGRES_PASSWORD="postgres"
ADD init.sh /
EXPOSE 5432
RUN cat /init.sh >> /docker-entrypoint-initdb.d/postgres.sh

#https://medium.com/better-programming/customize-your-mysql-database-in-docker-723ffd59d8fb
