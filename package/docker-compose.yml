version : '2.1'
services:
  mysqldb:
    image: yuxiqian/eyulingo-mysql:latest

    environment:
      MYSQL_ROOT_PASSWORD: 
      MYSQL_DATABASE: bookie
      MYSQL_USER: docker
      MYSQL_PASSWORD: 123456docker
    ports:
      - "3306:3306"
    container_name: mysqldb
    healthcheck:
      test: ["CMD", "mysqladmin" ,"ping", "-h", "localhost"]
      timeout: 20s
      retries: 10

  server:
    image: yuxiqian/eyulingo-server:latest
    ports:
      - "8080:8080"
    links:
      - mysqldb
    depends_on:
      mysqldb:
        condition: service_healthy
    container_name: server
    restart: always

  admin-panel:
    image: yuxiqian/eyulingo-admin:latest
    ports:
      - "8081:8081"
    links:
      - mysqldb
    depends_on:
      mysqldb:
        condition: service_healthy
    container_name: admin-panel
    restart: always

  dist-panel:
    image: yuxiqian/eyulingo-dist:latest
    ports:
      - "8082:8082"
    links:
      - mysqldb
    depends_on:
      mysqldb:
        condition: service_healthy
    container_name: dist-panel
    restart: always